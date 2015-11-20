'use strict';
angular.module('jumlitApp').directive('umlClass', function() {
    return {
        transclude: true,
        scope: {
            cell: '=cellModel',
            graph: '=',
            class: '='
        },
        templateUrl: 'templates/uml-class.html',
        link: function(scope, element) {
            var cell = scope.cell;
            scope.class = scope.class || {
                fields: [],
                methods: []
            };

            scope.$watch('graph', function() {
                var selected = scope.graph.get('selected').id;
                scope.selected =  selected ? selected.id === cell.id : false;
            });

            element.find('input,select').on('mousedown click', function(evt) {
                evt.stopPropagation();
            });
            element.find('.delete').on('click', _.bind(cell.remove, cell));

            cell.on('change', updateBox, this);
            cell.on('remove', function() {element.remove();});
            updateBox();

            function updateBox() {
                var bbox = cell.getBBox();
                cell.set('height', element.height());
                element.css({
                    width: bbox.width,
                    left: bbox.x,
                    top: bbox.y,
                    transform: 'rotate(' + (cell.get('angle') || 0) + 'deg)'
                });
            }

            scope.$watch('showField', function() {
                if(scope.showField) {
                    scope.closeMethod();
                }
            });
            scope.$watch('showMethod', function() {
                if(scope.showMethod) {
                    scope.closeField();
                }
            });

            scope.showField = false;
            scope.showMethod = false;
            scope.addField = function() {
                scope.class.fields.push(scope.field);
                scope.closeField();
            };
            scope.addMethod = function() {
                scope.class.fields.push(scope.method);
                scope.closeMethod();
            };
            scope.closeField = function() {
                scope.showField = false;
            }
            scope.showNewField = function() {
                scope.field = {
                    type: '',
                    name: '',
                    isStatic: false,
                    accessModifier: 'Default'
                };
                scope.showField = true;
            };
            scope.closeMethod = function() {
                scope.showMethod = false;
            }
            scope.showNewMethod = function() {
                scope.method = {
                    isStatic: false,
                    accessModifier: 'Default'
                };
                scope.showMethod = true;
            };
        }

    };
});
