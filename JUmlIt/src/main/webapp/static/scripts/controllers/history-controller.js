'use strict';
angular.module('jumlitApp').controller('HistoryCtrl', function ($scope, $uibModal, $state, diagram) {
    //$scope.diagram = diagram;
    DiagramServices.getDiagram(6).then(function (diagram) {
        $scope.diagram = diagram;
    });
    $scope.history = {
        saves: []
    };
});
