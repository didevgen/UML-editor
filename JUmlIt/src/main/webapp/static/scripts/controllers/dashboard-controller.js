'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state, $timeout, Session, Utils) {
    $scope.timers = [];
    $scope.ownDiagrams = [];
    $scope.collabDiagrams = [];

    $scope.pageSize = 8;
    $scope.maxPages = 4;

    $scope.ownDiagramsPages = 0;
    $scope.ownDiagramsPageNum = 0;
    $scope.ownDiagramsPage = [];

    $scope.collabDiagramsPages = 0;
    $scope.collabDiagramsPageNum = 0;
    $scope.collabDiagramsPage = [];

    function fillCollabDiagramsPage() {
        $scope.collabDiagramsPage = $scope.collabDiagrams.slice($scope.collabDiagramsPageNum * $scope.pageSize, $scope.pageSize);
    }

    function fillOwnDiagramsPage() {
        $scope.ownDiagramsPage = $scope.ownDiagrams.slice($scope.ownDiagramsPageNum * $scope.pageSize, $scope.pageSize);
    }

    $scope.$watch('ownDiagrams', function () {
        $scope.ownDiagramsPages = Math.floor($scope.ownDiagrams.length - 1 / $scope.diagramsOnPage);
        fillOwnDiagramsPage();
    });
    $scope.$watch('collabDiagrams', function () {
        $scope.collabDiagramsPages = Math.floor($scope.collabDiagrams.length - 1 / $scope.diagramsOnPage);
        fillCollabDiagramsPage();
    });
    $scope.$watch('ownDiagramsPageNum', function() {
        fillOwnDiagramsPage();
    });
    $scope.$watch('collabDiagramsPageNum', function() {
        fillCollabDiagramsPage();
    });

    function refreshDiagrams() {
        Utils.postRequest('account/' + Session.user.userId + '/details').then(function (details) {
            $scope.ownDiagrams = details.ownDiagrams;
            $scope.collabDiagrams = details.collabDiagrams;
        });
    }

    $scope.user = Session.user;
    refreshDiagrams();

    $scope.editDetails = function () {
        $state.go('account.user-info');
    };

    $scope.openDiagram = function (diagram) {
        $state.go("diagram", {
            diagramId: diagram.diagramId
        });
    }

    $scope.createDiagram = function () {
        $scope.openEditModal('NewDiagramModalController', {}).result.then(function (result) {
            if (result.success) {
                refreshDiagrams();
            }
        });
    };


    $scope.editDiagram = function (diagram) {
        $scope.openEditModal('EditDiagramModalController', diagram).result.then(function (result) {
            if (result.success) {
                refreshDiagrams();
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

    $scope.deleteDiagram = function (diagram) {
        diagram.deleted = true;
        document.getElementById("deleteContainer" + diagram.diagramId).addEventListener('mouseout', function () {
            var timerId = findIndexById($scope.timers, diagram.diagramId);
           if (timerId < 0 && diagram.deleted) {
                $scope.timers.push({
                    timer: $timeout(function () {
                        $scope.confirmDelete(diagram);
                        $scope.timers.splice(timerId, 1);
                    }, 5000),
                    id: diagram.diagramId
                });
            }
        });
        document.getElementById("deleteContainer" + diagram.diagramId).addEventListener('mouseover', function () {
            var timerId = findIndexById($scope.timers, diagram.diagramId);
            if (timerId >= 0) {
                $timeout.cancel($scope.timers[timerId].timer);
                $scope.timers.splice(timerId, 1);
            }
        });
    };

    $scope.confirmDelete = function (diagram) {
        document.getElementById("deleteContainer" + diagram.diagramId).parentNode.className =
            document.getElementById("deleteContainer" + diagram.diagramId).parentNode.className + " deleting";
        $timeout(function () {
            Utils.postRequest('diagram/' + diagram.diagramId + '/remove').then(function () {
                refreshDiagrams();
            });
        }, 150);
    };

    $scope.cancelDeleting = function (diagram) {
        diagram.deleted = false;
        var timerId = findIndexById($scope.timers, diagram.diagramId);
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
