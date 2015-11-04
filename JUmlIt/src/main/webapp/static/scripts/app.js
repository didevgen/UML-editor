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
        'ui.router'
    ])
    .config(function($stateProvider, $httpProvider) {
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'states/main.html',
                controller: 'MainCtrl'
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
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });
