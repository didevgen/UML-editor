'use strict';
angular.module('jumlitApp').controller('EditDiagramModalController', function ($scope, $uibModalInstance, id, DiagramServices, Utils, UserServices) {
    $scope.modalTitle = "Edit diagram";
    DiagramServices.getDiagram(id).then(function (diagram) {
        $scope.diagram = diagram;
    });

    $scope.save = function () {
        DiagramServices.updateDiagram($scope.diagram)
            .then(function () {
                $uibModalInstance.close();
            })
            .catch(function (err) {
                $uibModalInstance.dismiss(err);
            });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };

    $scope.findUser = function (email) {
        return UserServices.findUsersByEmail(email);
    }
});
