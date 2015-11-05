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
            .state('about', {
                url: '/about',
                templateUrl: 'states/about.html',
                controller: 'AboutCtrl'
            })
            .state('register', {
                url: '/register',
                templateUrl: 'states/register.html',
                controller: 'RegisterCtrl',
            })
            .state('login', {
                url: '/login',
                templateUrl: 'states/login.html',
                controller: 'LoginCtrl',
            })
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'states/dashboard.html',
                controller: 'DashboardCtrl'
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        function handleEmptyUrl(Session, $state) {
            if (Session.authenticated) {
                $state.go('dashboard');
            } else {
                $state.go('landing');
            }
        }

        $urlRouterProvider.when('', handleEmptyUrl);
    });
