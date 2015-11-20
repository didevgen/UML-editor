'use strict';

angular.module('jumlitApp').controller('NavCtrl', function ($rootScope, Session, $scope, Authentication) {
    $scope.Session = Session;
    $scope.logout = function() {
        Authentication.logout();
    };
});
