'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state, $timeout, Session,
    DiagramServices, Enums, $rootScope) {

    $scope.user = Session.user;

    $scope.timers = [];

    $scope.pageSize = 6;

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
        $scope.ownDiagrams.pageNum = Math.ceil($scope.ownDiagrams.list.length / $scope.pageSize);
    });

    $rootScope.$on(Enums.events.DIAGRAM_UPDATED, function (event, diagram) {
        var index = _.findIndex($scope.ownDiagrams.list, {
            diagramId: diagram.diagramId
        });
        angular.extend($scope.ownDiagrams.list[index], diagram);
    });

    $rootScope.$on(Enums.events.DIAGRAM_REMOVED, function (event, id) {
        var index = _.findIndex($scope.ownDiagrams.list, {
            diagramId: id
        });
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
        //TODO fix rendering
        /*$scope.ownDiagrams.page.splice.apply($scope.ownDiagrams.page, [0, $scope.pageSize].concat($scope
            .ownDiagrams.list.slice(startIndex, startIndex + $scope.pageSize)));*/
        $scope.ownDiagrams.page = $scope.ownDiagrams.list.slice(startIndex,
            startIndex + $scope.pageSize);
    }

    $scope.$watchCollection('ownDiagrams.list', $scope.fillOwnDiagramsPage);
    $scope.$watchCollection('collabDiagrams.list', $scope.fillCollabDiagramsPage);

    $scope.editDetails = function () {
        $state.go('account.user-info');
    };

    $scope.openDiagram = function (id) {
        $state.go("diagram", {
            diagramId: id
        });
    }

    $scope.createDiagram = function () {
        $scope.openEditModal('NewDiagramModalController', {}).result
            .catch(function (msg) {
                if (msg) {
                    console.log(msg);
                }
            });
    };

    $scope.editDiagram = function (id) {
        $scope.openEditModal('EditDiagramModalController', id).result
            .catch(function (msg) {
                if (msg) {
                    console.log(msg);
                }
            });
    };

    $scope.openEditModal = function (controller, id) {
        return $uibModal.open({
            templateUrl: 'modals/edit-diagram-modal.html',
            controller: controller,
            resolve: {
                id: function () {
                    return id;
                }
            }
        });
    };

    $scope.deleteDiagram = function (id) {
        $scope.ownDiagrams.list[_.findIndex($scope.ownDiagrams.list, {
            diagramId: id
        })].deleted = true;
        document.getElementById("deleteContainer" + id).addEventListener('mouseout', function () {
            var timerId = _.findIndex($scope.timers, {
                id: id
            });
            if (timerId < 0 && $scope.ownDiagrams.list[_.findIndex($scope.ownDiagrams.list, {
                    diagramId: id
                })].deleted) {
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
            var timerId = _.findIndex($scope.timers, {
                id: id
            });
            if (timerId >= 0) {
                $timeout.cancel($scope.timers[timerId].timer);
                $scope.timers.splice(timerId, 1);
            }
        });
    };

    $scope.confirmDelete = function (id) {
        document.getElementById("deleteContainer" + id).parentNode.className =
            document.getElementById("deleteContainer" + id).parentNode.className + " deleting";
        $timeout(function () {
            DiagramServices.removeDiagram(id);
        }, 150);
    };

    $scope.cancelDeleting = function (id) {
        $scope.ownDiagrams.list[_.findIndex($scope.ownDiagrams.list, {
            diagramId: id
        })].deleted = false;
        var timerId = _.findIndex($scope.timers, {
            id: id
        });
        if (timerId >= 0) {
            $timeout.cancel($scope.timers[timerId].timer);
            $scope.timers.splice(timerId, 1);
        }
    };

});
