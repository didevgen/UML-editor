'use strict';
angular.module('jumlitApp').controller('EditDiagramModalController', function ($scope, $uibModalInstance, id, Utils) {
    (function () {
        Utils.getRequest('diagram/' + id).then(function (model) {
            $scope.diagramModel = model;
        });
    })();

    $scope.save = function () {
        Utils.postRequest('diagram/update', $scope.diagramModel)
            .then(function (model) {
                if (model.ok) {
                    return Utils.getRequest('diagram/' + id);
                } else {
                    throw model;
                }
            })
            .then(function (model) {
                $uibModalInstance.close(model);
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
