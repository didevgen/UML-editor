'use strict';
angular.module('jumlitApp').directive('umlClass', function($rootScope, Enums) {
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

            $rootScope.$on(Enums.events.CLASS_UPDATED, function(clazz) {
                if ($scope.clazz.classId !== clazz.id) {
                    return
                }
                angular.extend($scope.clazz, clazz);
            });


            element.find('.delete').on('click', function() {
                cell.remove();
            });

            cell.on('change', function() {
                updateBox();
                updateClazz();
            });
            cell.on('remove', removeClazz);
            cell.on('change', _.debounce(notifyUpdate, 500));

            updateBox();
            updateClazz();
            updateCell();

            function updateCell() {
                cell.set('height', element.height());
            }

            function updateClazz() {
                var bbox = cell.getBBox();
                angular.extend($scope.clazz.position, {
                    x: bbox.x,
                    y: bbox.y
                });
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

            function removeClazz() {
                element.remove();
                $scope.$emit(Enums.events.CLASS_REMOVED, $scope.clazz);
            }

            function notifyUpdate() {
                $scope.$emit(Enums.events.CLASS_UPDATED, $scope.clazz)
            }
        }
    };
});
