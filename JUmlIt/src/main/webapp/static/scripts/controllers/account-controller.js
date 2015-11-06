'use strict';
angular.module('jumlitApp').controller('AccountCtrl', function(Session, $scope) {
    $scope.$watch(function() {
        return Session.lastUpdated();
    }, function() {
        $scope.user = Session.get('user');
    });
});
