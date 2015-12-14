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

    $scope.toggleComments = function () {
        $scope.showComments = !$scope.showComments;
    };

    $scope.$on('toggleComments', function () {
        $scope.toggleComments();
    });

    $rootScope.$on(Enums.events.CLASS_SELECTED, function (event, clazz) {
        $scope.showRelationshipSettings = false;
        $scope.showSettings = true;
    });

    $rootScope.$on(Enums.events.RELATIONSHIP_SELECTED, function (event, relationship) {
        $timeout(function () {
            $scope.showSettings = false;
            $scope.showRelationshipSettings = true;
        });
    });

    $rootScope.$on(Enums.events.RELATIONSHIP_DESELECTED, function () {
        $timeout(function () {
            $scope.showRelationshipSettings = false;
        });
    });

    $rootScope.$on(Enums.events.CLASS_DESELECTED, function () {
        $scope.showSettings = false;
    });

    $scope.$on(Enums.events.CLASS_UPDATED, function(event, clazz) {
        ClazzServices.updateClass(clazz);
    });

    $scope.$on(Enums.events.CLASS_REMOVED, function (event, clazz) {
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
        });
    });

    $rootScope.$on(Enums.events.SOCKET_FIELD_ADDED, function(event, field) {
        console.log(field);
        var index = _.findIndex($scope.diagram.classes, {classId: field.classId});
        if (index === -1) {
            throw new Error('field of an unknown class');
        }
        $scope.diagram.classes[index].fields.push(field);
        $rootScope.$emit(Enums.events.CLASS_UPDATED, $scope.diagram.classes[index]);
    });

    $rootScope.$on(Enums.events.SOCKET_FIELD_UPDATED, function(event, field) {
        var index = _.findIndex($scope.diagram.classes, {classId: field.classId});
        if (index === -1) {
            throw new Error('field of an unknown class');
        }
        var fieldIndex = _.findIndex($scope.diagram.classes[index].fields, {id: field.id});
        $scope.diagram.classes[index].fields.splice(fieldIndex, 1, field);
        $rootScope.$emit(Enums.events.CLASS_UPDATED, $scope.diagram.classes[index]);
    });

    $rootScope.$on(Enums.events.SOCKET_FIELD_REMOVED, function(event, field) {
        var foundClazz;
        $scope.diagram.classes.every(function(clazz) {
            var fieldIndex = _.findIndex(clazz.fields, {id: field.id});
            if (fieldIndex === -1) {
                // continue iterating
                return true;
            }
            foundClazz = clazz;
            clazz.fields.splice(fieldIndex);
            return false;
        });
        if (foundClazz) {
            $rootScope.$emit(Enums.events.CLASS_UPDATED, foundClazz);
        }
    });

    $rootScope.$on(Enums.events.SOCKET_METHOD_ADDED, function(event, method) {
        var index = _.findIndex($scope.diagram.classes, {classId: method.classId});
        if (index === -1) {
            throw new Error('method of an unknown class');
        }
        $scope.diagram.classes[index].methods.push(method);
        $rootScope.$emit(Enums.events.CLASS_UPDATED, $scope.diagram.classes[index]);
    });

    $rootScope.$on(Enums.events.SOCKET_METHOD_UPDATED, function(event, method) {
        var index = _.findIndex($scope.diagram.classes, {classId: method.classId});
        if (index === -1) {
            throw new Error('method of an unknown class');
        }
        var fieldIndex = _.findIndex($scope.diagram.classes[index].methods, {id: method.id});
        $scope.diagram.classes[index].methods.splice(fieldIndex, 1, method);
        $rootScope.$emit(Enums.events.CLASS_UPDATED, $scope.diagram.classes[index]);
    });

    $rootScope.$on(Enums.events.SOCKET_METHOD_REMOVED, function(event, method) {
        var foundClazz;
        $scope.diagram.classes.every(function(clazz) {
            var methodIndex = _.findIndex(clazz.methods, {id: method.id});
            if (methodIndex === -1) {
                // continue iterating
                return true;
            }
            foundClazz = clazz;
            clazz.methods.splice(methodIndex);
            return false;
        });
        if (foundClazz) {
            $rootScope.$emit(Enums.events.CLASS_UPDATED, foundClazz);
        }
    });
    $scope.openHistory = function () {
        $state.go("history.diagram", {
            diagramId: $scope.diagram.diagramId
        });
    }
});
