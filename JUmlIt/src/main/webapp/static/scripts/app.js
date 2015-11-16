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
                controller: 'LandingCtrl'
            })
            .state('landing.about', {
                url: '/about',
                templateUrl: 'states/landing.about.html',
                controller: 'AboutCtrl'
            })
            .state('landing.register', {
                url: '/register',
                templateUrl: 'states/landing.register.html',
                controller: 'RegisterCtrl',
            })
            .state('landing.login', {
                url: '/login',
                templateUrl: 'states/landing.login.html',
                controller: 'LoginCtrl',
            })
            .state('account', {
                url: '/account',
                templateUrl: 'states/account.html',
                controller: 'AccountCtrl'
            })
            .state('account.dashboard', {
                url: '/dashboard',
                templateUrl: 'states/account.dashboard.html',
                controller: 'DashboardCtrl'
            })
            .state('account.user-info', {
                url: '/user-info',
                templateUrl: 'states/account.edit-user.html',
                controller: 'UserInfoCtrl'
            })
            .state('diagram', {
                url: '/diagram',
                templateUrl: 'states/diagram.html',
                controller: 'DiagramCtrl'
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        function handleUrl(Session, $state) {
            if (Session.authenticated) {
                $state.go('account.dashboard');
            } else {
                $state.go('landing.login');
            }
        }

        $urlRouterProvider.when('', handleUrl);
        $urlRouterProvider.when('/landing', handleUrl);
    });
