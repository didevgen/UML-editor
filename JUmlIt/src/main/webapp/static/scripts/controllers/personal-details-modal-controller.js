'use strict';
angular.module('jumlitApp').controller('PersonalDetailsModalCtrl', function ($scope, $uibModalInstance) {
    $scope.personal = '';

    $scope.save = function () {
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
});
