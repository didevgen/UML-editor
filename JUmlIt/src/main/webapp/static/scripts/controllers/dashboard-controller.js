'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state, $timeout, Session,
                                                                  DiagramServices, Enums, $rootScope) {

    $scope.user = Session.user;

    $scope.timers = [];

    $scope.pageSize = 8;

    $scope.ownDiagrams = {
        list: [],
        pageNum: 1,
        page: []
    };

    $scope.collabDiagrams = {
        list: [],
        pageNum: 1,
        page: []
    };

    $rootScope.$on(Enums.events.DIAGRAM_ADDED, function (event, diagram) {
        $scope.ownDiagrams.list.push(diagram);
    });

    $rootScope.$on(Enums.events.DIAGRAM_UPDATED, function (event, diagram) {
        var index = _.findIndex($scope.ownDiagrams.list, {diagramId: diagram.diagramId});
        $scope.ownDiagrams.list.splice(index, 1, diagram);
    });

    $rootScope.$on(Enums.events.DIAGRAM_REMOVED, function (event, diagram) {
        var index = _.findIndex($scope.ownDiagrams.list, {diagramId: diagram.diagramId});
        $scope.ownDiagrams.list.splice(index, 1);
    });

    DiagramServices.getDiagrams().then(function (data) {
        $scope.ownDiagrams.list = data.ownDiagrams;
        $scope.collabDiagrams.list = data.collabDiagrams;
    });

    $scope.fillCollabDiagramsPage = function () {
        var startIndex = ($scope.collabDiagrams.pageNum - 1) * $scope.pageSize;
        $scope.collabDiagrams.page = $scope.collabDiagrams.list.slice(startIndex,
            startIndex + $scope.pageSize);
    }

    $scope.fillOwnDiagramsPage = function () {
        var startIndex = ($scope.ownDiagrams.pageNum - 1) * $scope.pageSize;
        $scope.ownDiagrams.page.splice.apply($scope.ownDiagrams.page,
            [0, $scope.pageSize].concat($scope
                .ownDiagrams.list.slice(startIndex, startIndex + $scope.pageSize)));
    }

    $scope.$watchCollection('ownDiagrams.list', $scope.fillOwnDiagramsPage);
    $scope.$watchCollection('collabDiagrams.list', $scope.fillCollabDiagramsPage);

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
            }
        });
    };


    $scope.editDiagram = function (diagram) {
        $scope.openEditModal('EditDiagramModalController', diagram).result.then(function (result) {
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
            DiagramServices.removeDiagram(diagram);
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

    function findDiagramIndexById(arr, id) {
        return arr.indexOf(arr.find(function (obj) {
            return obj.diagramId == id;
        }));
    }

    function findIndexById(arr, id) {
        return arr.indexOf(arr.find(function (obj) {
            return obj.id == id;
        }));
    }

});
