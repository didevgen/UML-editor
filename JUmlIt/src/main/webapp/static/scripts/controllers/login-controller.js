'use strict';

angular.module('jumlitApp').controller('LoginCtrl', function($scope, Authentication, $q, $state) {
    $scope.user = {
        email: '',
        password: '',
    };

    $scope.alerts = [];

    $scope.attemptLogin = function() {
        var data = {
            email: $scope.user.email,
            password: $scope.user.password
        };

        $scope.alerts = [];

        submitLogin(data)
            .then(function() {
                $state.go('account.dashboard');
            })
            .catch(function(error) {
                $scope.alerts.push({
                    type: 'danger',
                    msg: 'Login error! Something happened on our servers.'
                });
            });
    }

    function submitLogin(data) {
        return Authentication.login(data);
    }

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

});
