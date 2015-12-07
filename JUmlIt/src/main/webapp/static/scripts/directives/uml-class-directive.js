'use strict';
angular.module('jumlitApp').directive('umlClass', function($rootScope, Enums, $timeout) {
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
            var listeners = [];

            $scope.accessModifiers = Enums.accessModifiers;
            $scope.classTypes = Enums.classTypes;

            listeners.push($scope.$on(Enums.events.CELL_SELECTED, function(event, id) {
                $scope.selected = id === cell.id;
                if ($scope.selected) {
                    $rootScope.$emit(Enums.events.CLASS_SELECTED, $scope.clazz);
                }
            }));
            listeners.push($rootScope.$on(Enums.events.CLASS_DESELECTED, function() {
                $scope.$apply(function() {
                    $scope.selected = false;
                });
            }));

            listeners.push($rootScope.$on(Enums.events.CLASS_UPDATED, function(event, clazz) {
                if ($scope.clazz.classId !== clazz.classId) {
                    return;
                }
                angular.extend($scope.clazz, clazz);
            }));


            element.find('.action-delete').on('click', function() {
                cell.remove();
            });

            console.log(cell.resize);

            cell.on('change', function() {
                updateBox();
                updateClazz();
                updateCell();
            });
            cell.on('remove', removeClazz);
            cell.on('change', _.debounce(notifyUpdate, 500));

            $timeout(function() {
                updateBox();
                updateClazz();
                updateCell();
            });

            function updateCell() {
                var bbox = cell.getBBox();
                if (bbox.height !== element.height() || bbox.width !== element.width()) {
                    cell.resize(element.width(), element.height());
                }
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
                    left: bbox.x + 10,
                    top: bbox.y,
                    transform: 'rotate(' + (cell.get('angle') || 0) + 'deg)'
                });
            }

            function removeClazz() {
                listeners.forEach(function(unsubscribe) {
                    unsubscribe();
                });
                element.remove();
                $scope.$emit(Enums.events.CLASS_REMOVED, $scope.clazz);
            }

            function notifyUpdate() {
                $scope.$emit(Enums.events.CLASS_UPDATED, $scope.clazz);
            }

            // connection

            element.find('.action-link').on('mousedown', function(event) {
                console.log(event);
                $scope.$emit(Enums.events.CELL_DESELECTED);
                $scope.$emit(Enums.events.CELL_LINK_STARTED, {
                    clazz: $scope.clazz,
                    cell: cell,
                    position: {
                        x: event.clientX,
                        y: event.clientY
                    }
                });
            });
        }
    };
});
