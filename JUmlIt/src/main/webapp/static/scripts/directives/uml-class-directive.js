'use strict';
angular.module('jumlitApp').directive('umlClass', function($rootScope, Enums, $timeout, Cells) {
    return {
        transclude: true,
        scope: {
            graph: '=',
            paper: '=',
            clazz: '='
        },
        templateUrl: 'templates/uml-class.html',
        link: function($scope, element) {
            $scope.cell = Cells.create($scope.clazz.classType,
                    $scope.clazz.position);
            $scope.graph.addCell($scope.cell);
            $scope.cell.set('clazz', $scope.clazz);

            $scope.$on("$destroy", function() {
                element.remove();
            });

            var listeners = [];

            $scope.accessModifiers = Enums.accessModifiers;
            $scope.classTypes = Enums.classTypes;

            $rootScope.$on(Enums.events.CLASS_DESELECTED, function() {
                $scope.$apply(function() {
                    $scope.selected = false;
                });
            });

            $rootScope.$on(Enums.events.CLASS_UPDATED, function(event, clazz) {
                if ($scope.clazz.classId !== clazz.classId) {
                    return;
                }

                angular.extend($scope.clazz, clazz);
            });

            // new id came
            $rootScope.$on(Enums.events.CLASS_SELECTED, function(event, clazz) {
                if ($scope.clazz.classId !== clazz.classId) {
                    $scope.$apply(function() {
                        $scope.selected = false;
                    });
                }
            });

            $scope.$on(Enums.events.CLASS_UPDATED, function(event, clazz) {
                if(!$scope.clazz.classId) {
                    $scope.cell.set('classId', clazz.classId);
                    $scope.clazz.classId = clazz.classId;
                }
            });


            element.find('.action-delete').on('click', function() {
                $scope.cell.remove();
            });

            $scope.cell.on('change', function() {
                updateBox();
                updateClazz();
                updateCell();
            });
            $scope.paper.on('cell:pointerclick', function(cellView) {
                $scope.selected = cellView.model.id === $scope.cell.id;
                if ($scope.selected) {
                    $rootScope.$emit(Enums.events.CLASS_SELECTED, $scope.clazz);
                }
            });

            updateBox();
            $timeout(function() {
                updateClazz();
                updateCell();

                $scope.cell.on('remove', removeClazz);
                $scope.cell.on('change', _.debounce(filterCellEvents(notifyUpdate), 500));
                $scope.$emit(Enums.events.CLASS_INITIALIZED);
            });

            function updateCell() {
                var bbox = $scope.cell.getBBox();
                if (bbox.height !== element.height() || bbox.width !== element.width()) {
                    $scope.cell.resize(element.width(), element.height());
                }
            }

            function updateClazz() {
                var bbox = $scope.cell.getBBox();
                angular.extend($scope.clazz.position, {
                    x: bbox.x,
                    y: bbox.y
                });
            }

            function updateBox() {
                var bbox = $scope.cell.getBBox();
                element.css({
                    left: bbox.x,
                    top: bbox.y,
                    transform: 'rotate(' + ($scope.cell.get('angle') || 0) + 'deg)'
                });
            }

            function removeClazz() {
                $scope.$emit(Enums.events.CLASS_REMOVED, $scope.clazz);
            }

            function notifyUpdate() {
                $scope.$emit(Enums.events.CLASS_UPDATED, $scope.clazz);
            }

            function filterCellEvents(callback) {
                var previousPosition = null;
                return function() {
                    var position = $scope.cell.get('position');
                    if (!position) {
                        return;
                    }
                    if (!previousPosition) {
                        previousPosition = position;
                        return;
                    }
                    if (positionsEqual(position, previousPosition)) {
                        return;
                    }
                    previousPosition = position;
                    callback();
                }

                function positionsEqual(p1, p2) {
                    return p1.x === p2.y && p1.y === p2.y;
                }

            }

            // connection

            element.find('.action-link').on('mousedown', function(event) {
                $scope.$emit(Enums.events.CELL_DESELECTED);
                $scope.$emit(Enums.events.CELL_LINK_STARTED, {
                    clazz: $scope.clazz,
                    cell: $scope.cell,
                    position: {
                        x: event.clientX,
                        y: event.clientY
                    }
                });
            });
        }
    };
});
