'use strict';
angular.module('jumlitApp').controller('DiagramCanvasCtrl', function($scope, $q, Cells, $compile) {
    var graph = new joint.dia.Graph;
    var paper = new joint.dia.Paper({
        el: $('#diagram-canvas'),
        model: graph,
        gridSize: 1
    });

    graph.on('blank:pointerclick', function() {
        graph.set('selected', null);
    })

    $scope.dropped = null;

    $scope.onDrop = function(event, data) {
        var position = {
            x: event.clientX,
            y: event.clientY - 100
        };

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
});
