'use strict';
angular.module('jumlitApp').directive('umlClass', function($rootScope, Enums, ClazzServices) {
    return {
        transclude: true,
        scope: {
            cell: '=cellModel',
            graph: '=',
            clazz: '='
        },
        templateUrl: 'templates/uml-class.html',
        link: function($scope, element) {
            var cell = $scope.cell;

            $scope.$on(Enums.events.CELL_SELECTED, function(event, id) {
                $scope.selected = id === cell.id;
                if ($scope.selected) {
                    $rootScope.$emit(Enums.events.CLASS_SELECTED, $scope.clazz);
                }
            });
            $rootScope.$on(Enums.events.CLASS_DESELECTED, function() {
                $scope.$apply(function() {
                    $scope.selected = false;
                });
            });

            element.find('input,select').on('mousedown click', function(evt) {
                evt.stopPropagation();
            });
            element.find('.delete').on('click', _.bind(cell.remove, cell));

            cell.on('change', function() {
                updateBox();
                updateClazz();
            });

            cell.on('remove', function() {
                element.remove();
                ClazzServices.removeClass($scope.clazz);
            });

            updateBox();
            updateClazz();
            updateCell();

            function updateCell() {
                cell.set('height', element.height());
            }

            function updateClazz() {
                var bbox = cell.getBBox();
                $scope.clazz.position = {
                    x: bbox.x,
                    y: bbox.y
                };
            }

            function updateBox() {
                var bbox = cell.getBBox();
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
