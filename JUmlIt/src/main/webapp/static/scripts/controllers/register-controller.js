'use strict';
angular.module('jumlitApp').controller('RegisterCtrl', function($scope, $http, Config, Utils, Auth, $state, $q) {
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

        submitRegistration(data)
            .then(function() {
                return Auth.login(data);
            })
            .then(function() {
                $state.go('dashboard');
            })
            .catch(function () {
            });
    }

    function submitRegistration(data) {
        return Auth.register(data)
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
