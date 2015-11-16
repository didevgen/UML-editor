'use strict';
angular.module('jumlitApp').controller('EditDiagramModalController', function ($scope, $uibModalInstance) {
    $scope.diagram = {};
    $scope.diagram.title = "Sample project";
    $scope.diagram.collaborators = [
        {
            email: "email1@email.com",
            name: "Name1"
        },
        {
            email: "email2@email.com",
            name: "Name2"
        }
    ]
    $scope.addCollaborator = function () {
        $scope.diagram.collaborators.push({
            email: $scope.newCollaborator.email,
            name: "NewCollaborator" + $scope.diagram.collaborators.length
        })
        $scope.newCollaborator.email = "";
    }
    $scope.save = function () {
        $uibModalInstance.close($scope.diagram);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
});
