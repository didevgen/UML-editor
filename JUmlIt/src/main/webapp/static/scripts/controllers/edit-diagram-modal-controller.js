'use strict';
angular.module('jumlitApp').controller('EditDiagramModalController', function ($scope, $uibModalInstance, diagram, Utils) {
    function getDiagramModel() {
        Utils.getRequest('diagram/' + diagram.diagramId).then(function(model) {
            $scope.diagramModel = model;
        });
    }
    getDiagramModel();

    $scope.save = function () {
        Utils.postRequest('diagram/update', $scope.diagramModel).then(function(model) {
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
