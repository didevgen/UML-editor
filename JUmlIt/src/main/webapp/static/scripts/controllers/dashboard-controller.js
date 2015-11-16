'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state) {
    $scope.diagrams = [
        {title: "Sample project"}
    ]
    $scope.openDiagram = function () {
        $uibModal.open({
            templateUrl: 'modals/open-diagram-modal.html',
            controller: 'OpenDiagramModalCtrl'
        }).result.then(function (res) {
            if (res == "open") {
                $state.go("diagram");
            } else if (res == "settings") {
                $scope.openEditModal('EditDiagramModalController').result.then(function (result) {
                    console.log(result);
                });
            }
            console.log(res);
        });
    };
    $scope.newDiagram = function () {
        $scope.openEditModal('NewDiagramModalController').result.then(function (result) {
            console.log(result);
        });
    };

    $scope.openEditModal = function (controller) {
        return $uibModal.open({
            templateUrl: 'modals/edit-diagram-modal.html',
            controller: controller
        });
    }
});
