'use strict';
angular.module('jumlitApp').directive('diagramCanvas', function($q, Cells, $compile) {
    return {
        restrict: 'E',
        templateUrl: 'templates/diagram-canvas.html',
        scope: {

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

            paper.on('blank:pointerclick', function() {
                graph.get('selected').set('selected', false);
                graph.set('selected', null);
                $scope.$emit('cellDeselected');
                $scope.$broadcast('cellDeselected');
            });
            paper.on('cell:pointerclick', function(cellView) {
                graph.set('selected', cellView.model);
                $scope.$emit('cellSelected', cellView.model);
                $scope.$broadcast('cellSelected', cellView.model.id);
            });

            $scope.onDrop = function(event, data) {
                var position = {
                    x: event.clientX - 150,
                    y: event.clientY - 100
                };
                console.log(event, data, position);

                var type = $scope.dropped.name;
                var cellModel = Cells.create(type, position, $scope.dropped.text);

                var directiveScope = $scope.$new();
                directiveScope.cellModel = cellModel;
                directiveScope.graph = graph;

                graph.set('selected', cellModel);

                var cellView = $compile('<uml-class graph="graph" cell-model="cellModel">')(directiveScope);
                $('#diagram-canvas').append(cellView);
                graph.addCell(cellModel);
            };

        }
    };

});
