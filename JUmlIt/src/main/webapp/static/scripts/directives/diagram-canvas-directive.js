'use strict';
angular.module('jumlitApp').directive('diagramCanvas', function($q, Cells, $compile, $rootScope, Enums,
            Clazz, $timeout, ClazzServices, Relationship) {
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
                height: 3000,
                width: 3000
            });

            $scope.dropped = null;
            $scope.graph = graph;
            $scope.paper = paper;
            $scope.classTypes = Enums.classTypes;
            $scope.classes.forEach(initClass);

            function deselect() {
                var previousSelected = graph.get('selected');
                if (previousSelected) {
                    previousSelected.set('selected', false);
                }
                graph.set('selected', null);
            }

            $scope.$on(Enums.events.CELL_DESELECTED, function () {
                deselect();
                $rootScope.$emit(Enums.events.CLASS_DESELECTED);
            });
            $rootScope.$on(Enums.events.RELATIONSHIP_SELECTED, function() {
                $rootScope.$emit(Enums.events.CLASS_DESELECTED);
            });

            paper.on('blank:pointerclick', function() {
                deselect();
                $rootScope.$emit(Enums.events.CLASS_DESELECTED);
                $rootScope.$emit(Enums.events.RELATIONSHIP_DESELECTED);
            });
            paper.on('cell:pointerclick', function(cellView) {
                graph.set('selected', cellView.model);
                $scope.$broadcast(Enums.events.CELL_SELECTED, cellView.model.id);
            });

            $scope.onDrop = function(event) {
                var clazz = new Clazz({
                    position: {
                        x: event.clientX - 250,
                        y: event.clientY - 100
                    },
                    classType: $scope.dropped
                });
                addClassToGraph(clazz);
                clazz.temp = true;
                ClazzServices.createClass(clazz).then(function(fetchedClazz) {
                    fetchedClazz.cellModel = clazz.cellModel;
                    var index = _.findIndex($scope.classes, {isTemp: true});
                    delete clazz.temp;
                    $scope.classes.splice(index, 1, fetchedClazz);
                });
            };

            function initClass(clazz) {
                $scope.classes.splice(_.findIndex($scope.classes, {classId: clazz.classId}));
                addClassToGraph(clazz);
            }

            function addClassToGraph(clazz) {
                var cellModel = Cells.create('Class', clazz.position);
                clazz.cellModel = cellModel;
                cellModel.set('clazz', clazz);
                $scope.classes.push(clazz);
                graph.addCell(clazz.cellModel);
            }

            $scope.$on(Enums.events.CLASS_REMOVED, function(event, clazz) {
                var index = _.findIndex($scope.classes, { classId: clazz.classId });
                $scope.classes.splice(index, 1);
            })


            // linking classes
            var source;
            var isLinking = false;
            var previousPosition;
            var tempLink;

            function removeOffset(position) {
                var offset = element.offset();
                position.x -= offset.left;
                position.y -= offset.top;
                return position;
            }

            $scope.$on(Enums.events.CELL_LINK_STARTED, function(event, data) {
                source = data;
                isLinking = true;
                previousPosition = removeOffset(data.position);
                tempLink = new joint.dia.Link({
                    source: data.cell,
                    target: previousPosition
                });
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
                        primaryMemberId: source.clazz,
                        secondaryMemberId: elementBelow.get('clazz'),
                        cellModel: tempLink
                    });

                    $scope.$apply(function() {
                        console.log(relationship);
                        $scope.relationships.push(relationship);
                    });

                    isLinking = false;
                    source = null;
                } else {
                    tempLink.remove();
                }
            });
        }
    };

});
