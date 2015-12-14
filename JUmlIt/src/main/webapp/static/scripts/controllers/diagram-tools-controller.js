'use strict';
angular.module('jumlitApp').controller('DiagramToolsCtrl', function($scope, Enums, $rootScope, $timeout, $state) {
    $scope.groups = {
        general: true
    };
    $scope.collapsed = false;

    $scope.classTypes = Enums.classTypes;


    $scope.openHistory = function () {
        $state.go("history.diagram", {
            diagramId: $scope.diagram.diagramId
        });
    }

    $rootScope.$on(Enums.events.SOCKET_DIAGRAM_EVENT, function(event, diagramEvent) {
        $timeout(function() {
            $scope.diagram.history.push(diagramEvent);
        });
    });
});
