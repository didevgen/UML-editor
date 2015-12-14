'use strict';
angular.module('jumlitApp').directive('diagramCanvas', function($q, Cells, $compile, $rootScope, Enums,
            Clazz, $timeout, ClazzServices, Relationship, Links) {
    return {
        restrict: 'E',
        templateUrl: 'templates/diagram-canvas.html',
        scope: {
            classes: '=',
            relationships: '='
        },
        link: function($scope, element, attrs) {
            var graph = new joint.dia.Graph;
            var paper = new joint.dia.Paper({
                el: $('#diagram-canvas'),
                model: graph,
                gridSize: 1,
                height: 1500,
                width: 1500
            });

            $scope.dropped = null;
            $scope.graph = graph;
            $scope.paper = paper;
            $scope.classTypes = Enums.classTypes;

            var count = 0;
            $scope.$on(Enums.events.CLASS_INITIALIZED, function() {
                count++;
                if($scope.classes.length === count) {
                    $scope.$broadcast(Enums.events.CLASSES_INITIALIZED);
                }
            });

            $scope.$on(Enums.events.CELL_DESELECTED, function () {
                $rootScope.$emit(Enums.events.CLASS_DESELECTED);
            });
            $rootScope.$on(Enums.events.RELATIONSHIP_SELECTED, function() {
                $rootScope.$emit(Enums.events.CLASS_DESELECTED);
            });

            paper.on('blank:pointerclick', function() {
                $rootScope.$emit(Enums.events.CLASS_DESELECTED);
                $rootScope.$emit(Enums.events.RELATIONSHIP_DESELECTED);
            });

            $scope.onDrop = function(event) {
                var clazz = new Clazz({
                    name: 'Class' + (+$scope.classes.length + 1),
                    x: event.clientX - 250 + $('diagram-canvas').scrollLeft(),
                    y: event.clientY - 100 + $('diagram-canvas').scrollTop(),
                    classType: $scope.dropped
                });
                $scope.classes.push(clazz);
                ClazzServices.createClass(clazz).then(function(newClazz) {
                    $scope.$broadcast(Enums.events.CLASS_UPDATED, newClazz);
                });
            };

            $scope.$on(Enums.events.CLASS_REMOVED, function(event, clazz) {
                $scope.$apply(function() {
                    var index = _.findIndex($scope.classes, { classId: clazz.classId });
                    $scope.classes.splice(index, 1);
                });
                $scope.$emit(Enums.events.CLASS_DESELECTED);
            })

            $scope.$on(Enums.events.RELATIONSHIP_REMOVED, function(event, relationship) {
                $scope.$apply(function() {
                    var index = _.findIndex($scope.relationships, { id: relationship.id });
                    $scope.relationships.splice(index, 1);
                });
                $scope.$emit(Enums.events.RELATIONSHIP_DESELECTED);
            });


            // linking classes
            var source;
            var isLinking = false;
            var previousPosition;
            var tempLink;

            function removeOffset(position) {
                var offset = element.offset();
                position.x += $('diagram-canvas').scrollLeft() - offset.left;
                position.y += $('diagram-canvas').scrollTop() - offset.top;
                return position;
            }

            $scope.$on(Enums.events.CELL_LINK_STARTED, function(event, data) {
                source = data;
                isLinking = true;
                previousPosition = removeOffset(data.position);
                tempLink = Links.create(Enums.relationshipTypes.ASSOCIATION, data.cell, previousPosition);
                graph.addCell(tempLink);
            });

            element.on('mousemove', function(event) {
                if (!isLinking) {
                    return;
                }
                var position = removeOffset({
                    x: event.clientX,
                    y: event.clientY
                });
                tempLink.set('target', position);
            });

            element.on('mouseup', function(event) {
                if (!isLinking) {
                    return;
                }
                var position = removeOffset({
                    x: event.clientX,
                    y: event.clientY
                });

                var elementBelow = graph.get('cells').find(function(cell) {
                    if (cell instanceof joint.dia.Link) return false; // Not interested in links.
                    if (cell.id === source.cell.id) return false; // The same element as the source.
                    if (cell.getBBox().containsPoint(g.point(position.x, position.y))) {
                        return true;
                    }
                    return false;
                });

                if (elementBelow && !_.contains(graph.getNeighbors(elementBelow), source.cell)) {

                    tempLink.set('target', elementBelow);
                    var relationship = new Relationship({
                        primaryMember: source.clazz,
                        secondaryMember: elementBelow.get('clazz'),
                        cell: tempLink
                    });

                    $scope.$apply(function() {
                        $scope.relationships.push(relationship);
                    });

                    ClazzServices.createRelationship(relationship).then(function(fetchedRel) {
                        angular.extend(relationship, fetchedRel);
                    });

                } else {
                    tempLink.remove();
                }
                isLinking = false;
                source = null;
            });
        }
    };

});
