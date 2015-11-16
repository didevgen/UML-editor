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
        'ui.router',
        'ui.bootstrap'
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
            .state('diagram', {
                url: '/diagram',
                templateUrl: 'states/diagram.html',
                controller: 'DiagramCtrl',
                data: {
                    authenticated: false
                }
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $urlRouterProvider.when('/', function($state) {
            $state.go('account.dashboard');
        });
    }).run(function($rootScope, $state, Authorization) {
        /*$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
            Authorization.authorize(event, toState.data);
        });*/
        $state.go('account.dashboard');
    });
