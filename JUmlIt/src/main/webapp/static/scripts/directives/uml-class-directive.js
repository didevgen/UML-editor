'use strict';
angular.module('jumlitApp').directive('umlClass', function($rootScope, Enums, $timeout, Cells) {
    return {
        priority: 20,
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
            $scope.cell.set('classId', $scope.clazz.classId);

            var listeners = [];

            $scope.accessModifiers = Enums.accessModifiers;
            $scope.classTypes = Enums.classTypes;

            listeners.push($rootScope.$on(Enums.events.CLASS_DESELECTED, function() {
                $scope.$apply(function() {
                    $scope.selected = false;
                });
            }));

            listeners.push($rootScope.$on(Enums.events.CLASS_UPDATED, function(event, clazz) {
                if(!$scope.clazz.classId) {
                    $scope.cell.set('classId', clazz.classId);
                } else if ($scope.clazz.classId !== clazz.classId) {
                    return;
                }

                angular.extend($scope.clazz, clazz);
            }));


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
                $scope.cell.on('change', _.debounce(notifyUpdate, 500));
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
