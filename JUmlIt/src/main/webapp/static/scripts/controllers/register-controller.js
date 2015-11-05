'use strict';
angular.module('jumlitApp').controller('RegisterCtrl', function($scope, $http, Config, Utils) {
    $scope.user = {
        email: '',
        password: '',
        fullname: '',
        repeatPassword: ''
    };

    $scope.submitRegistration = function() {
        Utils.postRequest('account/register', {
            fullname: $scope.fullname,
            email: $scope.email,
            password: $scope.password
        }).then(function(data) {
            console.log(data);
        });
    };
});
