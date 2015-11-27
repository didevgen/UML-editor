'use strict';
angular.module('jumlitApp').controller('NewDiagramModalController', function ($scope, $uibModalInstance, Utils) {
    $scope.diagramModel = {
        diagram: {
            title: "",
            diagram: ""
        },
        collaborators: []
    };

    $scope.save = function () {
        var diagram = {
            name: $scope.diagram.title,
            collaborators: []
        };
        Utils.postRequest('diagram/create', $scope.diagramModel).then(function() {
            $uibModalInstance.close({success: true});
        });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
    $scope.findUser = function(email) {
        return Utils.postRequest('account/email/'+email);
    }
});
