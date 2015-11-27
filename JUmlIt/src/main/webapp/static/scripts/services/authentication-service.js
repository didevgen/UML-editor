'use strict';
angular.module('jumlitApp').service('Authentication', function(Session, Utils, $q, $state, Config) {
    var AuthService = {};

    function ajaxLogin(credentials) {
        var deferred = $q.defer();
        $.ajax(Config.API_PATH + 'login', {
            type: 'POST',
            data: credentials,
            success: function(data) {
                deferred.resolve(data);
            },
            error: function(err) {
                switch (err.status) {
                    case 200:
                        deferred.resolve({ok: true});
                        return;
                    case 401:
                        deferred.reject("unauthorized");
                        return;
                }

                var error = JSON.parse(err.responseText);
                deferred.reject(error.errorMessage);
            }
        });
        return deferred.promise;
    }

    function ajaxLogout() {
        var deferred = $q.defer();
        $.post(Config.API_PATH + 'logout').then(deferred.resolve, deferred.reject);
        return deferred.promise;
    }

    AuthService.authenticate = function() {
        return Utils.postRequest('account').then(function(data) {
            Session.user = data;
            Session.authenticated = true;
        })
    }

    AuthService.login = function(email, password) {
        var credentials = {
            email: email,
            password: password
        };
        if (!credentials) {
            return $q.reject('No credentials supplied');
        }
        return ajaxLogin(credentials)
            .then(AuthService.authenticate)
            .catch(function(error) {
                if(error === 'unauthorized') {
                    return $q.reject("Can't login, wrong credentials!");
                }
            });
    };

    AuthService.logout = function() {
        ajaxLogout().then(function() {
            Session.user = null;
            Session.authenticated = false;

            $state.go('landing.login');
        });
    };

    AuthService.register = function(data) {
        return Utils.postRequest('register', data)
    }

    return AuthService;
});
