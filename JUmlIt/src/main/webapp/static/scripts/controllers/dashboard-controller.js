'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state) {
    $scope.diagrams = [
        {
            title: "Sample project",
            collaborators: []
        },
        {
            title: "Sample project 1",
            collaborators: []
        },
        {
            title: "Sample project 2",
            collaborators: []
        }
    ]

    $scope.editDetails = function () {
        $state.go('account.user-info');
    };


    $scope.editDiagram = function (diag) {
        $scope.openEditModal('EditDiagramModalController', diag).result.then(function (result) {
            for(var i = 0; i < $scope.diagrams.length; i++){
                if($scope.diagrams[i].title == diag.title)
                    {
                        $scope.diagrams[i] = result;
                    }
            }
        });
    };

    $scope.openDiagram = function () {
        $state.go("diagram");
    }

    $scope.deleteDiagram = function (diag) {
        $scope.diagrams = $scope.diagrams.filter(function (obj) {
            return obj.title !== diag.title;
        });
    }

    $scope.createDiagram = function () {
        $scope.openEditModal('NewDiagramModalController', {}).result.then(function (result) {
            if (result.title) {
                $scope.diagrams.push(result);
            }
        });
    };

    $scope.openEditModal = function (controller, diag) {
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
