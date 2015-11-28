'use strict';

/**
 * @ngdoc overview * @name jumlitApp
 * @description
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
        'ngTagsInput'
    ])
    .config(function($stateProvider, $httpProvider, $urlRouterProvider) {
        $stateProvider
            .state('landing', {
                url: '/landing',
                templateUrl: 'states/landing.html',
                controller: 'LandingCtrl',
                data: {
                    authenticated: false
                }
            })
            .state('landing.about', {
                url: '/about',
                templateUrl: 'states/landing.about.html',
                controller: 'AboutCtrl',
                data: {
                    authenticated: false
                }
            })
            .state('landing.register', {
                url: '/register',
                templateUrl: 'states/landing.register.html',
                controller: 'RegisterCtrl',
                data: {
                    authenticated: false
                }
            })
            .state('landing.login', {
                url: '/login',
                templateUrl: 'states/landing.login.html',
                controller: 'LoginCtrl',
                data: {
                    authenticated: false
                }
            })
            .state('account', {
                url: '/account',
                templateUrl: 'states/account.html',
                controller: 'AccountCtrl',
                data: {
                    authenticated: true
                }
            })
            .state('account.dashboard', {
                url: '/dashboard',
                templateUrl: 'states/account.dashboard.html',
                controller: 'DashboardCtrl',
                data: {
                    authenticated: true
                }
            })
            .state('account.user-info', {
                url: '/user-info',
                templateUrl: 'states/account.edit-user.html',
                controller: 'UserInfoCtrl'
            })
            .state('diagram', {
                url: '/diagrams/:diagramId',
                templateUrl: 'states/diagram.html',
                controller: 'DiagramCtrl',
                data: {
                    authenticated: false
                },
                resolve: {
                    diagramModel: function(Utils, $stateParams) {
                        return Utils.getRequest('diagram/' + $stateParams.diagramId);
                    }
                }
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $urlRouterProvider.when('/', function($state) {
            $state.go('account.dashboard');
        });
    }).run(function($rootScope, $state, Authorization, Authentication, $window, Session) {
        Authentication.authenticate().then(function() {
            $state.go('account.dashboard');
        }).catch(function(error) {
            $state.go('landing.login')
        });
        $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
            Authorization.authorize(event, toState.data);
        });
    });
