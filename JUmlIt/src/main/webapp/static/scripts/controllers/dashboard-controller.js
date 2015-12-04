'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state, $timeout, Session, Utils) {
    $scope.timers = [];
    $scope.ownDiagrams = [];
    $scope.collabDiagrams = [];

    $scope.pageSize = 6;
    $scope.visiblePages = 6;

    $scope.ownDiagramsBounary = false;
    $scope.collabDiagramsBounary = false;

    $scope.ownDiagramsPages = 1;
    $scope.ownDiagramsPageNum = 1;
    $scope.ownDiagramsPage = [];

    $scope.collabDiagramsPages = 1;
    $scope.collabDiagramsPageNum = 1;
    $scope.collabDiagramsPage = [];

    $scope.setOwnPage = function (page) {
        $scope.ownDiagramsPageNum = page;
    }

    $scope.setCollabPage = function (page) {
        $scope.collabDiagramsPageNum = page;
    }

    function fillCollabDiagramsPage() {
        $scope.collabDiagramsPage = $scope.collabDiagrams.slice(($scope.collabDiagramsPageNum - 1) * $scope.pageSize, ($scope.collabDiagramsPageNum - 1) * $scope.pageSize + $scope.pageSize);
    }

    function fillOwnDiagramsPage() {
        //TODO fix refreshing
        $scope.ownDiagramsPage = $scope.ownDiagrams.slice(($scope.ownDiagramsPageNum - 1) * $scope.pageSize, ($scope.ownDiagramsPageNum - 1) * $scope.pageSize + $scope.pageSize);
    }

    $scope.$watch('ownDiagrams', function () {
        if ($scope.ownDiagrams.length) {
            $scope.ownDiagramsPages = Math.ceil($scope.ownDiagrams.length / $scope.pageSize);
            if ($scope.ownDiagramsPages > $scope.visiblePages) {
                $scope.ownDiagramsBounary = true;
            }
            else {
                $scope.ownDiagramsBounary = false;
            }
            fillOwnDiagramsPage();
        }
    }, true);
    $scope.$watch('collabDiagrams', function () {
        if ($scope.collabDiagrams.length) {
            $scope.collabDiagramsPages = Math.ceil($scope.collabDiagrams.length / $scope.pageSize);
            if ($scope.collabDiagramsPages > $scope.visiblePages) {
                $scope.collabDiagramsBounary = true;
            }
            else {
                $scope.collabDiagramsBounary = false;
            }
            fillCollabDiagramsPage();
        }
    }, true);
    $scope.$watch('ownDiagramsPageNum', function () {
        fillOwnDiagramsPage();
    });
    $scope.$watch('collabDiagramsPageNum', function () {
        fillCollabDiagramsPage();
    });

    function refreshDiagrams() {
        console.log(Session.user);
        Utils.postRequest('account/' + Session.user.userId + '/details')
            .then(function (details) {
                $scope.ownDiagrams = details.ownDiagrams;
                $scope.collabDiagrams = details.collabDiagrams;
            })
            .catch(function (err) {
                console.log(err);
            });
    }

    $scope.user = Session.user;
    refreshDiagrams();

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
            .then(function (result) {
                if (result) {
                    $scope.ownDiagrams.push(result);
                }
            }, function (msg) {
                if (msg) {
                    console.log(msg);
                }
            });
    };

    $scope.editDiagram = function (id) {
        $scope.openEditModal('EditDiagramModalController', id).result.then(function (result) {
            if (result) {
                angular.extend($scope.ownDiagrams[findDiagramIndexById($scope.ownDiagrams, result.diagram.diagramId)], result.diagram);
            }
        }, function (msg) {
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
        $scope.ownDiagrams[findDiagramIndexById($scope.ownDiagrams, id)].deleted = true;
        document.getElementById("deleteContainer" + id).addEventListener('mouseout', function () {
            var timerId = findIndexById($scope.timers, id);
            if (timerId < 0 && $scope.ownDiagrams[findDiagramIndexById($scope.ownDiagrams, id)].deleted) {
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
        document.getElementById("deleteContainer" + id).parentNode.className =
            document.getElementById("deleteContainer" + id).parentNode.className + " deleting";
        $timeout(function () {
            Utils.postRequest('diagram/' + id + '/remove')
                .then(function (result) {
                    if (result.ok) {
                        $scope.ownDiagrams = $scope.ownDiagrams.filter(function (obj) {
                            return obj.diagramId !== id;
                        });
                    } else {
                        throw result;
                    }
                }).catch(function (err) {
                    console.log(err);
                });
        }, 150);
    };

    $scope.cancelDeleting = function (id) {
        $scope.ownDiagrams[findDiagramIndexById($scope.ownDiagrams, id)].deleted = false;
        var timerId = findIndexById($scope.timers, id);
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
