'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state, $timeout, Session, Utils) {
    $scope.timers = [];
    $scope.ownDiagrams = [];
    $scope.collabDiagrams = [];

    function loadDetails() {
        Utils.postRequest('account/' + Session.user.userId + '/details').then(function(details) {
            $scope.ownDiagrams = details.ownDiagrams;
            $scope.collabDiagrams = details.collabDiagrams;
        });
    }

    $scope.user = Session.user;
     loadDetails();

    $scope.editDetails = function () {
        $state.go('account.user-info');
    };

    $scope.openDiagram = function () {
        $state.go("diagram");
    }

    $scope.createDiagram = function () {
        $scope.openEditModal('NewDiagramModalController', {}).result.then(function (result) {
            if (result.success) {
                loadDetails();
            }
        });
    };


    $scope.editDiagram = function (diagram) {
        $scope.openEditModal('EditDiagramModalController', diagram).result.then(function (result) {
            if (result.success) {
                loadDetails();
            }
        });
    };

    $scope.openEditModal = function (controller, diagram) {
        return $uibModal.open({
            templateUrl: 'modals/edit-diagram-modal.html',
            controller: controller,
            resolve: {
                diagram: function () {
                    return diagram;
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
            Utils.postRequest('diagram/' + id + '/delete').then(function() {
                loadDetails();
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
