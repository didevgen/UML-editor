'use strict';
angular.module('jumlitApp').controller('NewDiagramModalController', function ($scope, $uibModalInstance, Utils, Diagram,
    DiagramServices, UserServices) {

    $scope.modalTitle = "Crate new diagram";
    $scope.diagram = new Diagram();

    $scope.save = function () {
        DiagramServices.createDiagram($scope.diagram)
            .then(function () {
                $uibModalInstance.close();
            })
            .catch(function (err) {
                $uibModalInstance.dismiss(err)
            });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
    $scope.findUser = function (email) {
        return UserServices.findUsersByEmail(email);
    }
});
