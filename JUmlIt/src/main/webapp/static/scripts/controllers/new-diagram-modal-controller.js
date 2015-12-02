'use strict';
angular.module('jumlitApp').controller('NewDiagramModalController', function ($scope, $uibModalInstance, Utils, Diagram,
                                                                              DiagramServices, UserServices) {

    $scope.diagram = new Diagram();

    $scope.save = function () {
        DiagramServices.createDiagram($scope.diagram)
            .then(function () {
                $uibModalInstance.close({success: true});
            });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
    $scope.findUser = function (email) {
        return UserServices.findUsersByEmail(email);
    }
});
