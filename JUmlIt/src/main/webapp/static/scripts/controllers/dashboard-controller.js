'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state) {
    $scope.diagrams = [
        {
            title: "Sample project"
        },
        {
            title: "Sample project 1"
        },
        {
            title: "Sample project 2"
        }
    ]

    $scope.editDetails = function () {
        $state.go('account.user-info');
    };


    $scope.openDiagram = function (diag) {
        console.log(diag);
        $uibModal.open({
            templateUrl: 'modals/open-diagram-modal.html',
            controller: 'OpenDiagramModalCtrl'
        }).result.then(function (res) {
            if (res == "open") {
                $state.go("diagram");
            } else if (res == "settings") {
                $scope.openEditModal('EditDiagramModalController', diag).result.then(function (result) {
                    $scope.diagrams.push(result);
                });
            } else if (res == "delete") {
                $scope.diagrams = $scope.diagrams.filter(function (obj) {
                    return obj.title !== diag.title;
                });
            }
            console.log(res);
        });
    };

    $scope.createDiagram = function () {
        $scope.openEditModal('NewDiagramModalController', {}).result.then(function (result) {
            if (result.title) {
                $scope.diagrams.push(result);
            }
        });
    };

    $scope.openEditModal = function (controller, diag) {
        console.log(diag);
        return $uibModal.open({
            templateUrl: 'modals/edit-diagram-modal.html',
            controller: controller,
            resolve: {
                diagram: function () {
                    return diag;
                }
            }
        });
    }
});
