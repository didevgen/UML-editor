'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state, Session, Utils) {
    function loadDetails() {
        Utils.postRequest('account/' + Session.user.userId + '/details').then(function(details) {
            $scope.details = details;
        });
    }
    loadDetails();
    $scope.user = Session.user;

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

    $scope.createDiagram = function () {
        $scope.openEditModal('NewDiagramModalController', {}).result.then(function (result) {
            if (result.title) {
                result.id = $scope.diagrams[$scope.diagrams.length - 1] + 1;
                $scope.diagrams.push(result);
            }
        });
    };


    $scope.editDiagram = function (diag) {
        $scope.openEditModal('EditDiagramModalController', diag).result.then(function (result) {
            $scope.diagrams[findIndexById($scope.diagrams, diag.id)] = result;
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
    };

    $scope.deleteDiagram = function (id) {
        $scope.diagrams[findIndexById($scope.diagrams, id)].deleted = true;
        document.getElementById("deleteContainer" + id).addEventListener('mouseout', function () {
            var timerId = findIndexById($scope.timers, id);
            if (timerId < 0 && $scope.diagrams[findIndexById($scope.diagrams, id)].deleted) {
                $scope.timers.push({
                    timer: $timeout(function () {
                        $scope.confirmDelete(id);
                        $scope.timers.splice(timerId, 1);
                    }, 5000),
                    id: id
                });
            }
        });
        document.getElementById("deleteContainer" + id).addEventListener('mouseover', function () {
            var timerId = findIndexById($scope.timers, id);
            if (timerId >= 0) {
                $timeout.cancel($scope.timers[timerId].timer);
                $scope.timers.splice(timerId, 1);
            }
        });
    };

    $scope.confirmDelete = function (id) {
        document.getElementById("deleteContainer" + id).parentNode.className = document.getElementById("deleteContainer" + id).parentNode.className + " deleting";
        $timeout(function () {
            $scope.diagrams = $scope.diagrams.filter(function (obj) {
                return obj.id !== id;
            });
        }, 150);
    };

    $scope.cancelDeleting = function (id) {
        $scope.diagrams[findIndexById($scope.diagrams, id)].deleted = false;
        var timerId = findIndexById($scope.timers, id);
        if (timerId >= 0) {
            $timeout.cancel($scope.timers[timerId].timer);
            $scope.timers.splice(timerId, 1);
        }
    };

    function findIndexById(arr, id) {
        return arr.indexOf(arr.find(function (obj) {
            return obj.id == id;
        }));
    }

});
