'use strict';
angular.module('jumlitApp').controller('OpenDiagramModalCtrl', function ($scope, $uibModalInstance) {
    $scope.diagram = {};
    $scope.diagram.title = "Sample Project";

    $scope.open = function () {
        $uibModalInstance.close("open");
    };

    $scope.settings = function () {
        $uibModalInstance.close("settings");
    };
});
