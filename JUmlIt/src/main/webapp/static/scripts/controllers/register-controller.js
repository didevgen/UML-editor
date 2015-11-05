'use strict';
angular.module('jumlitApp').controller('RegisterCtrl', function($scope, $http) {
    $scope.user = {
        email: '',
        password: '',
        fullname: '',
        repeatPassword: ''
    };

    $scope.submitValidation = function() {
        $http.post('account/register', $scope.user)
            .then(function(data) {
                console.log(data);
            });
    };
});
