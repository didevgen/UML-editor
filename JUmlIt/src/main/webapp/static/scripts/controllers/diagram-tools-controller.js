'use strict';
angular.module('jumlitApp').controller('DiagramToolsCtrl', function($scope, Enums) {
    $scope.groups = {
        general: true
    };
    $scope.collapsed = false;

    $scope.classTypes = Enums.classTypes;
});
