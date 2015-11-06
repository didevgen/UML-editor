'use strict';
angular.module('jumlitApp').service('Auth', function(Session, Utils) {
    var AuthService = {};

    AuthService.login = function(credentials) {
        return Utils.postRequest('account/login', credentials).then(function (data) {
            Session.set('user', data);
            Session.set('authenticated', true);
        });
    };

    AuthService.register = function(data) {
        return Utils.postRequest('account/register', data)
    }

    return AuthService;
});
