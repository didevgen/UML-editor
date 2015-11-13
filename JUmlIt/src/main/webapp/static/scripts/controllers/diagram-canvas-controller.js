'use strict';
angular.module('jumlitApp').controller('DiagramCanvasCtrl', function($scope, $q) {
    var graph = new joint.dia.Graph;

    var paper = new joint.dia.Paper({
        el: $('#diagram-canvas'),
        model: graph,
        gridSize: 1
    });

    var rect = new joint.shapes.basic.Rect({
        position: {
            x: 100,
            y: 30
        },
        size: {
            width: 100,
            height: 30
        },
        attrs: {
            rect: {
                fill: 'blue'
            },
            text: {
                text: 'my box',
                fill: 'white'
            }
        }
    });

    var rect2 = rect.clone();
    rect2.translate(300);

    var link = new joint.dia.Link({
        source: {
            id: rect.id
        },
        target: {
            id: rect2.id
        }
    });

    graph.addCells([rect, rect2, link]);

    $scope.addFigurePromise = function(event, data) {
        console.log(event, data);

        var rect = new joint.shapes.basic.Rect({
            position: {
                x: event.clientX - 50,
                y: event.clientY - 100 - 15
            },
            size: {
                width: 100,
                height: 30
            },
            attrs: {
                rect: {
                    fill: 'blue'
                },
                text: {
                    text: 'my box',
                    fill: 'white'
                }
            }
        });
        graph.addCell(rect);
        return $q.reject();
    };
});
