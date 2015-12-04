'use strict';
angular.module('jumlitApp').controller('NewDiagramModalController', function ($scope, $uibModalInstance, Utils) {

    $scope.diagramModel = {
        diagram: {
            name: "",
            description: ""
        },
        collaborators: []
    };

    $scope.save = function () {
        Utils.postRequest('diagram/create', $scope.diagramModel)
            .then(function (res) {
                $uibModalInstance.close(res.diagram);
            })
            .catch(function (err) {
                $uibModalInstance.dismiss(err);
            });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
    $scope.findUser = function (email) {
        return Utils.postRequest('account/email/' + email);
    }
});
