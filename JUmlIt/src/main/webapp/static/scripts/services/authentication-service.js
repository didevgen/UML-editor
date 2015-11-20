'use strict';
angular.module('jumlitApp').service('Authentication', function(Session, Utils, $q) {
    var AuthService = {};

    AuthService.login = function(credentials) {
        if (!credentials) {
            return $q.reject('No credentials supplied');
        }
        return Utils.postRequest('account/login', credentials).then(function (data) {
             Session.user = data.user;
             Session.token = data.token
             Session.authenticated = true;
        });
    };

    AuthService.register = function(data) {
        return Utils.postRequest('account/register', data)
    }

    return AuthService;
});
