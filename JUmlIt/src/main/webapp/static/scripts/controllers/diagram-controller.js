'use strict';
angular.module('jumlitApp').controller('DiagramCtrl', function ($scope, $rootScope, diagram, DiagramServices, Session, Enums,
        ClazzServices) {

    Session.diagram = diagram;

    $scope.diagram = diagram;
    $scope.showComments = false;
    $scope.showSettings = false;

    $scope.toggleComments = function() {
        $scope.showComments = !$scope.showComments;
    };

    $scope.$on('toggleComments', function() {
        $scope.toggleComments();
    });

    $rootScope.$on(Enums.events.CLASS_SELECTED, function(event, clazz) {
        $scope.showSettings = true;
    });

    $rootScope.$on(Enums.events.CLASS_DESELECTED, function() {
        $scope.showSettings = false;
    });
    $scope.$on(Enums.events.CLASS_UPDATED, function(event, clazz) {
        ClazzServices.updateClass(clazz);
    });

    $scope.$on(Enums.events.CLASS_REMOVED, function(event, clazz) {
        ClazzServices.removeClass(clazz);
    });
});
