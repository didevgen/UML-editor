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

        Authentication.register(data)
            .then(function(response) {
                return Authentication.login($scope.user.email, $scope.user.password);
            })
            .then(function() {
                $state.go('account.dashboard');
            })
            .catch(function (error) {
                $scope.alerts.push({
                    type: 'danger',
                    msg: error
                });
            });
    }

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

});
