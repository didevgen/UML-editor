'use strict';
angular.module('jumlitApp').controller('EditDiagramModalController', function ($scope, $uibModalInstance, diagram, DiagramServices, Utils, UserServices) {
    $scope.diagram = diagram;

    $scope.save = function () {
        DiagramServices.updateDiagram($scope.diagram)
            .then(function() {
                $uibModalInstance.close({success: true});
            });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };

    $scope.findUser = function(email) {
        return UserServices.findUsersByEmail(email);
    }
});
