'use strict';
angular.module('jumlitApp').controller('RegisterCtrl', function($scope, $http, Config, Utils, Auth, $state) {
    $scope.user = {
        email: '',
        password: '',
        fullname: '',
        repeatPassword: ''
    };

    $scope.attemptRegistration = function() {
        var data = {
            fullname: $scope.user.fullname,
            email: $scope.user.email,
            password: $scope.user.password
        };

        submitRegistration(data).then(function() {
            return Auth.login(data).then(function() {
                $state.go('dashboard');
            });
        });
    };

    function submitRegistration(data) {
        return Auth.register(data)
            .catch(function(error) {
                // TODO: handle error
                console.log(error);
            });
    }


});
