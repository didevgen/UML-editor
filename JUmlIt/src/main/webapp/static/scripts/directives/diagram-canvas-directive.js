'use strict';
angular.module('jumlitApp').directive('diagramCanvas', function($q, Cells, $compile, $rootScope, Enums,
            Clazz, $timeout, ClazzServices) {
    return {
        restrict: 'E',
        templateUrl: 'templates/diagram-canvas.html',
        scope: {
            classes: '='
        },
        link: function($scope, element, attrs) {
            var graph = new joint.dia.Graph;
            var paper = new joint.dia.Paper({
                el: $('#diagram-canvas'),
                model: graph,
                gridSize: 1,
                height: '',
                width: "100%"
            });
            $('#diagram-canvas svg').removeAttr('height');

            $scope.dropped = null;
            $scope.graph = graph;
            $scope.classTypes = Enums.classTypes;
            $scope.classes.forEach(addClassToGraph);

            paper.on('blank:pointerclick', function() {
                var previousSelected = graph.get('selected');
                if (previousSelected) {
                    previousSelected.set('selected', false);
                }
                graph.set('selected', null);
                $rootScope.$emit(Enums.events.CLASS_DESELECTED);
            });
            paper.on('cell:pointerclick', function(cellView) {
                graph.set('selected', cellView.model);
                $scope.$broadcast(Enums.events.CELL_SELECTED, cellView.model.id);
            });

            $scope.onDrop = function(event) {
                var clazz = new Clazz({
                    position: {
                        x: event.clientX - 150,
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

            function addClassToGraph(clazz) {
                var cellModel = Cells.create('Class', clazz.position);
                clazz.cellModel = cellModel;
                $scope.classes.push(clazz);
                graph.addCell(clazz.cellModel);
            }

            $scope.$on(Enums.events.CLASS_REMOVED, function(event, clazz) {
                var index = _.findIndex($scope.classes, { classId: clazz.classId });
                $scope.classes.splice(index, 1);
            })

        }
    };

});
