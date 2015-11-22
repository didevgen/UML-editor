'use strict';
angular.module('jumlitApp').controller('NewDiagramModalController', function ($scope, $uibModalInstance, Utils) {
    $scope.diagram = {};
    $scope.diagram.title = "";
    $scope.diagram.description = "";
    $scope.diagram.collaborators = [];
    $scope.addCollaborator = function () {
        $scope.diagram.collaborators.push({
            email: $scope.newCollaborator.email,
            name: "NewCollaborator" + $scope.diagram.collaborators.length
        })
        $scope.newCollaborator.email = "";
    }
    $scope.save = function () {
        var diagram = {
            name: $scope.diagram.title,
            collaborators: []
        };
        Utils.postRequest('diagram/create', $scope.diagram);
        $uibModalInstance.close($scope.diagram);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
    $scope.findUser = function(email) {
        var res = [];
        for (var i = 0 ; i < 10; i++) {
            res.push(email + i);
        }
        return res
    }
});
