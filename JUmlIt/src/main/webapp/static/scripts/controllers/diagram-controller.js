'use strict';
angular.module('jumlitApp').controller('DiagramCtrl', function ($scope, diagramModel) {
    $scope.diagramModel = diagramModel;
    $scope.showComments = false;
    $scope.showSettings = false;
    $scope.toggleComments = function() {
        $scope.showComments = !$scope.showComments;
    };

    $scope.$on('toggleComments', function() {
        $scope.toggleComments();
    });
    $scope.$on('cellSelected', function(event, cell) {
        $scope.showSettings = true;
        $scope.$broadcast('cellForEdit', cell);
    });
    $scope.$on('cellDeselected', function() {
        $scope.showSettings = false;
    });
});
