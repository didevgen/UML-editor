'use strict';
angular.module('jumlitApp').controller('DiagramCtrl', function ($scope, $rootScope, diagram, DiagramServices, Session, Enums,
        ClazzServices, $timeout, DiagramUpdates) {

    Session.diagram = diagram;

    var subscription;

    DiagramUpdates.subscribe('/topic/diagram/' + diagram.diagramId, function(newDiagram, headers) {
        if (+headers.fromUserId !== +Session.user.userId) {
            $scope.diagram = newDiagram;
        }
    }).then(function(subscriptionObj) {
        subscription = subscriptionObj;
    });

    $scope.$on('$destroy', function() {
        subscription.unsubscribe();
    });

    $scope.diagram = diagram;
    $scope.diagram.relationships = $scope.diagram.relationships || [];

    $scope.showComments = false;
    $scope.showSettings = false;
    $scope.showRelationshipSettings = false;

    $scope.toggleComments = function() {
        $scope.showComments = !$scope.showComments;
    };

    $scope.$on('toggleComments', function() {
        $scope.toggleComments();
    });

    $rootScope.$on(Enums.events.CLASS_SELECTED, function(event, clazz) {
        $scope.showRelationshipSettings = false;
        $scope.showSettings = true;
    });

    $rootScope.$on(Enums.events.RELATIONSHIP_SELECTED, function(event, relationship) {
        $timeout(function() {
            $scope.showSettings = false;
            $scope.showRelationshipSettings = true;
        });
    });

    $rootScope.$on(Enums.events.RELATIONSHIP_DESELECTED, function() {
        $timeout(function() {
            $scope.showRelationshipSettings = false;
        });
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

    $scope.$on(Enums.events.RELATIONSHIP_REMOVED, function(event, relationship) {
        ClazzServices.removeRelationship(relationship);
    })
});
