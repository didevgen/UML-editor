'use strict';
angular.module('jumlitApp').controller('RegisterCtrl', function($scope, $http, Config, Utils, Authentication, $state, $q) {
    $scope.user = {
        email: '',
        password: '',
        fullname: '',
        repeatPassword: ''
    };

    $scope.alerts = [];

    $scope.attemptRegistration = function() {
        var data = {
            fullname: $scope.user.fullname,
            email: $scope.user.email,
            password: $scope.user.password
        };

        $scope.alerts = [];

        submitRegistration(data)
            .then(function(response) {
                return Authentication.login(response.user);
            })
            .then(function() {
                $state.go('account.dashboard');
            })
            .catch(function (error) {
                $scope.alerts.push({
                    type: 'danger',
                    msg: 'Login error! Something happened on our servers.'
                });
            });
    }

    function submitRegistration(data) {
        return Authentication.register(data)
            .catch(function(error) {
                // TODO: handle error
                $scope.alerts.push({
                    type: 'danger',
                    msg: 'Registration error! Something happened on our servers.'
                });
                return $q.reject();
            });
    }

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

});
