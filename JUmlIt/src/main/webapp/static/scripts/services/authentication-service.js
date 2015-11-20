'use strict';
angular.module('jumlitApp').service('Authentication', function(Session, Utils, $q, $state) {
    var AuthService = {};

    AuthService.login = function(credentials) {
        if (!credentials) {
            return $q.reject('No credentials supplied');
        }
        return Utils.postRequest('account/login', credentials).then(function (data) {
             Session.user = data.user;
             Session.token = data.token;
             Session.authenticated = true;
        });
    };

    AuthService.logout = function() {
        Session.user = null;
        Session.token = null;
        Session.authenticated = false;

        Session.clear();

        $state.go('landing.login');
    };

    AuthService.register = function(data) {
        return Utils.postRequest('account/register', data)
    }

    return AuthService;
});
