'use strict';
angular.module('jumlitApp').controller('DiagramCtrl', function ($scope, $rootScope, diagram, DiagramServices, Session, Enums,
        ClazzServices, $timeout, DiagramUpdates, PngExport, CodeGeneration, DiagramUpdatesListener) {

    Session.diagram = diagram;

    DiagramUpdatesListener.subscribe(diagram.diagramId);
    $scope.$on('$destroy', function() {
        DiagramUpdatesListener.unsubscribe();
    });

    $scope.diagram = diagram;

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
    });

    $scope.generateCode = function() {
        CodeGeneration.generate($scope.diagram.diagramId);
    }

    $scope.exportPng = function() {
        PngExport.export($scope.diagram.name);
    }

    $rootScope.$on(Enums.events.SOCKET_CLASS_ADDED, function(event, clazz) {
        $scope.$apply(function() {
            $scope.diagram.classes.push(clazz);
        });
    });

    $rootScope.$on(Enums.events.SOCKET_RELATIONSHIP_CREATED, function(event, relationship) {
        $scope.$apply(function() {
            $scope.diagram.relationships.push(relationship);
            console.log(relationship);
        });
    });
});
