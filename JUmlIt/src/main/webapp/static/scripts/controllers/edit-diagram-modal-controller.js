'use strict';
angular.module('jumlitApp').controller('EditDiagramModalController', function ($scope, $uibModalInstance, diagram, Utils) {
    $scope.diagram = diagram;
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

    $scope.findUser = function(email) {
        return Utils.postRequest('account/email/'+email);
    }
});
