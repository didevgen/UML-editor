'use strict';

angular.module('jumlitApp').controller('LoginCtrl', function($scope, Authentication, $q, $state) {
    $scope.user = {
        email: '',
        password: '',
    };

    $scope.alerts = [];

    $scope.attemptLogin = function() {
        $scope.alerts = [];

        Authentication.login($scope.user.email, $scope.user.password)
            .then(function() {
                $state.go('account.dashboard');
            })
            .catch(function(error) {
                $scope.alerts.push({
                    type: 'danger',
                    msg: error || 'Something happened on our servers'
                });
            });
    }

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

});
