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
        link: function($scope, element) {
            var cell = $scope.cell;
            $scope.class = $scope.class || {
                fields: [],
                methods: []
            };

            $scope.$on('cellSelected', function(event, id) {
                $scope.$apply(function() {
                    $scope.selected = id === $scope.cell.id;
                });
            });
            $scope.$on('cellDeselected', function() {
                $scope.$apply(function() {
                    $scope.selected = false;
                });
            });

            element.find('input,select').on('mousedown click', function(evt) {
                evt.stopPropagation();
            });
            element.find('.delete').on('click', _.bind(cell.remove, cell));

            cell.on('change', updateBox, this);
            cell.on('remove', function() {element.remove();});
            updateBox();

            function updateBox() {
                $scope.class = {
                    name: cell.get('name'),
                    methods: cell.get('methods'),
                    fields: cell.get('fields')
                };
                var bbox = cell.getBBox();
                cell.set('height', element.height());
                element.css({
                    width: bbox.width,
                    left: bbox.x,
                    top: bbox.y,
                    transform: 'rotate(' + (cell.get('angle') || 0) + 'deg)'
                });
            }
        }
    };
});
