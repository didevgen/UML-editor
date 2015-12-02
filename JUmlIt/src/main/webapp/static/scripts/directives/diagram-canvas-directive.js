'use strict';
angular.module('jumlitApp').directive('diagramCanvas', function($q, Cells, $compile, $rootScope, Enums, ClazzServices,
            Clazz, $timeout) {
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
            $scope.classes.forEach(addClass);

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

            $scope.onDrop = function(event, data) {
                var clazz = new Clazz({
                    position: {
                        x: event.clientX - 150,
                        y: event.clientY - 100
                    }
                });
                addClass(clazz);
                clazz.isTemp = true;
                ClazzServices.createClass(clazz).then(function(fetchedClazz) {
                    fetchedClazz.cellModel = clazz.cellModel;
                    var index = _.findIndex($scope.classes, {isTemp: true});
                    $scope.classes.splice(index, 1, fetchedClazz);
                });
            };

            function addClass(clazz) {
                var cellModel = Cells.create('Class', clazz.position);
                clazz.cellModel = cellModel;
                $scope.classes.push(clazz);
                graph.addCell(clazz.cellModel);
            }

        }
    };

});
