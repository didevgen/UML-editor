'use strict';

/**
 * @ngdoc overview * @name jumlitApp * @description
 * # jumlitApp
 *
 * Main module of the application.
 */
angular
    .module('jumlitApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ngDragDrop',
        'ui.router',
        'ui.bootstrap',
        'ngTagsInput',
        'frapontillo.bootstrap-switch',
        'ngFileSaver'
    ])
    .config(function ($stateProvider, $httpProvider, $urlRouterProvider) {
        $stateProvider
            .state('landing', {
                url: '/landing',
                templateUrl: 'states/landing.html',
                controller: 'LandingCtrl',
                resolve: {
                    authorization: function (Authentication, $q) {
                        return Authentication.authenticate().catch(function () {
                            return $q.when();
                        });
                    }
                }

            })
            .state('landing.about', {
                url: '/about',
                templateUrl: 'states/landing.about.html',
                controller: 'AboutCtrl'
            })
            .state('landing.register', {
                url: '/register',
                templateUrl: 'states/landing.register.html',
                controller: 'RegisterCtrl'
            })
            .state('landing.login', {
                url: '/login',
                templateUrl: 'states/landing.login.html',
                controller: 'LoginCtrl'
            })
            .state('account', {
                url: '/account',
                templateUrl: 'states/account.html',
                controller: 'AccountCtrl',
                resolve: {
                    authorization: function (Authentication, $state, $q) {
                        return Authentication.authenticate().catch(function () {
                            $state.go('landing.login');
                            return $q.reject();
                        });
                    }
                }
            })
            .state('account.dashboard', {
                url: '/dashboard',
                templateUrl: 'states/account.dashboard.html',
                controller: 'DashboardCtrl',
            })
            .state('account.user-info', {
                url: '/user-info',
                templateUrl: 'states/account.edit-user.html',
                controller: 'UserInfoCtrl'
            })
            .state('account.notifications', {
                url: '/notifications',
                templateUrl: 'states/account.notifications.html',
                controller: 'NotificationsCtrl'
            })
            .state('diagram', {
                url: '/diagrams/:diagramId',
                templateUrl: 'states/diagram.html',
                controller: 'DiagramCtrl',
                resolve: {
                    authorization: function (Authentication) {
                        return Authentication.authenticate();
                    },
                    diagram: function (DiagramServices, $stateParams, $state, $q) {
                        return DiagramServices.getDiagram($stateParams.diagramId)
                            .catch(function (error) {
                                if (error.status === 403) {
                                    $state.go('forbidden');
                                }
                                return $q.reject();
                            });
                    }
                }
            })
            .state('forbidden', {
                templateUrl: 'states/forbidden.html'
            })
            .state('history', {
                url: '/history',
                templateUrl: 'states/history.html',
                controller: 'HistoryCtrl',
                resolve: {
                    authorization: function (Authentication) {
                        return Authentication.authenticate();
                    }
                }
            })
            .state('history.diagram', {
                url: '/:diagramId',
                templateUrl: 'states/history.diagram.html',
                controller: 'HistoryDiagramCtrl',
                resolve: {
                    diagram: function (DiagramServices, $stateParams) {
                            return DiagramServices.getDiagram($stateParams.diagramId);
                        } ,
                        history: function(HistoryServices, $stateParams) {
                            return HistoryServices.getHistory($scope.diagram.diagramId);
                        }
                }
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $urlRouterProvider.when('/', function ($state) {
            $state.go('account.dashboard');
        });
    }).run(function ($rootScope, $state, Authentication, $window, Session) {
        $state.go('account.dashboard');
    });
