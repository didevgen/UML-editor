'use strict';
angular.module('jumlitApp').controller('DiagramCanvasCtrl', function($scope, $q) {
    var graph = new joint.dia.Graph;

    var paper = new joint.dia.Paper({
        el: $('#diagram-canvas'),
        model: graph,
        gridSize: 1
    });

    $scope.dropped = null;
    $scope.onDrop = function(event, data) {
        var position = {
            x: event.clientX,
            y: event.clientY - 100
        };

        var cell;
        switch ($scope.dropped.name) {
            case 'Class':
                cell = new joint.shapes.uml.Class({
                    position: position,
                    size: {
                        width: 220,
                        height: 100
                    },
                    name: 'Class',
                    attrs: {
                        '.uml-class-name-rect': {
                            fill: '#ff8450',
                            stroke: '#fff',
                            'stroke-width': 0.5
                        },
                        '.uml-class-attrs-rect, .uml-class-methods-rect': {
                            fill: '#fe976a',
                            stroke: '#fff',
                            'stroke-width': 0.5
                        },
                        '.uml-class-attrs-text': {
                            ref: '.uml-class-attrs-rect',
                            'ref-y': 0.5,
                            'y-alignment': 'middle'
                        },
                        '.uml-class-methods-text': {
                            ref: '.uml-class-methods-rect',
                            'ref-y': 0.5,
                            'y-alignment': 'middle'
                        }
                    }
                })
                break;
            case 'Interface':
                cell = new joint.shapes.uml.Interface({
                    name: 'Interface',
                    position: position,
                    size: {
                        width: 240,
                        height: 100
                    },
                    attrs: {
                        '.uml-class-name-rect': {
                            fill: '#feb662',
                            stroke: '#ffffff',
                            'stroke-width': 0.5
                        },
                        '.uml-class-attrs-rect, .uml-class-methods-rect': {
                            fill: '#fdc886',
                            stroke: '#fff',
                            'stroke-width': 0.5
                        },
                        '.uml-class-attrs-text': {
                            ref: '.uml-class-attrs-rect',
                            'ref-y': 0.5,
                            'y-alignment': 'middle'
                        },
                        '.uml-class-methods-text': {
                            ref: '.uml-class-methods-rect',
                            'ref-y': 0.5,
                            'y-alignment': 'middle'
                        }

                    }
                });
                break;
            case 'Abstract':
                cell = new joint.shapes.uml.Abstract({
                    position: position,
                    size: {
                        width: 260,
                        height: 100
                    },
                    name: 'Abstract',
                    attrs: {
                        '.uml-class-name-rect': {
                            fill: '#68ddd5',
                            stroke: '#ffffff',
                            'stroke-width': 0.5
                        },
                        '.uml-class-attrs-rect, .uml-class-methods-rect': {
                            fill: '#9687fe',
                            stroke: '#fff',
                            'stroke-width': 0.5
                        },
                        '.uml-class-methods-text, .uml-class-attrs-text': {
                            fill: '#fff'
                        }
                    }
                });
                break;
        }

        graph.addCell(cell);
    };
});
