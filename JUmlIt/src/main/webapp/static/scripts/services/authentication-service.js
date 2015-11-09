'use strict';
angular.module('jumlitApp').service('Authentication', function(Session, Utils) {
    var AuthService = {};

    AuthService.login = function(credentials) {
        return Utils.postRequest('account/login', credentials).then(function (data) {
            Session.user = data;
            Session.authenticated = true;
        });
    };

    AuthService.register = function(data) {
        return Utils.postRequest('account/register', data)
    }

    return AuthService;
});
