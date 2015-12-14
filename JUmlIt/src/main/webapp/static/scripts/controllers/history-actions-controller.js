'use strict';
angular.module('jumlitApp').controller('HistoryActionsCtrl', function ($scope, $uibModal, $state, diagram, DiagramServices
    //,session
) {
    $scope.diagram = diagram;
    //$scope.session = session;
    $scope.session = {
        diagramId: 3,
        session: {
            sessionId: 4,
            userId: 3,
            userName: "Max Selekh",
            time_start: "2015-12-12 15:23:23",
            time_end: "2015-12-12 15:23:23",
            changes: [{
                    actionId: 2342,
                    time: "2015-12-12 15:22:22",
                    details: {
                        action: "added",
                        object: "class",
                        name: "Clazz"
                    }
                },
                {
                    actionId: 2342,
                    time: "2015-12-12 15:23:22",
                    details: {
                        action: "deleted",
                        object: "class",
                        name: "Clasdasdasdasdasd"
                    }
                },
                {
                    actionId: 2342,
                    time: "2015-12-12 15:25:22",
                    details: {
                        action: "changed",
                        object: "class",
                        name: "asdasdsa"
                    }
                }]
        }
    };
});
