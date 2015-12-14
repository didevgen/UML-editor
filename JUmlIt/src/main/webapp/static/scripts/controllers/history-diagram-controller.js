'use strict';
angular.module('jumlitApp').controller('HistoryDiagramCtrl', function ($scope, $uibModal, $state, diagram, HistoryServices) {

    /*$scope.history = {
        diagramId: diagram.diagramId,
        sessions: [
            {
                sessionId: 0,
                userId: 2,
                userName: "Max dfgdsfgdsfg",
                time_start: "2015-12-12 15:23:23",
                time_end: ""
            },
            {
                sessionId: 2,
                userId: 1,
                userName: "Max Selekh",
                time_start: "2015-12-12 15:24:23",
                time_end: "2015-12-12 15:28:23"
            },
            {
                sessionId: 3,
                userId: 0,
                userName: "Aaa",
                time_start: "2015-12-12 15:29:23",
                time_end: ""
            },
            {
                sessionId: 4,
                userId: 4,
                userName: "eeeeeeee",
                time_start: "2015-12-12 15:25:23",
                time_end: "2015-12-12 15:30:23"
            },
            {
                sessionId: 5,
                userId: 0,
                userName: "Aaa",
                time_start: "2015-12-12 15:24:25",
                time_end: "2015-12-12 15:32:23"
            }
        ],
        events: []
    };*/
    $scope.diagram = diagram;

    $scope.history = [];
    $scope.events = [];
    HistoryServices.getHistory(diagram.diagramId).then(function (res) {
        angular.extend($scope.history, res);
        console.log($scope.history);
        for (var i = 0; i < $scope.history.length; i++) {
            var session = $scope.history[i];
            console.log(session);
            $scope.events.push({
                userName: session.userName,
                time: session.timeStart,
                type: "start"
            });
            if (session.timeFinish) {
                $scope.events.push({
                    userName: session.userName,
                    time: session.timeFinish,
                    type: "end"
                });
            }
        }
        console.log($scope.events);
    });


});
