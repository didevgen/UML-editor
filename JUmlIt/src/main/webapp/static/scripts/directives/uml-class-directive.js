'use strict';
angular.module('jumlitApp').directive('umlClass', function ($rootScope, Enums, $timeout, Cells) {
    return {
        transclude: true,
        scope: {
            graph: '=',
            paper: '=',
            clazz: '='
        },
        templateUrl: 'templates/uml-class.html',
        link: function ($scope, element) {
            $scope.cell = Cells.create($scope.clazz.classType,
                $scope.clazz.position);
            $scope.graph.addCell($scope.cell);
            $scope.cell.set('clazz', $scope.clazz);

            $scope.$on("$destroy", function () {
                element.remove();
            });

            var listeners = [];
            var ignoreNextUpdate = false;

            $scope.accessModifiers = Enums.accessModifiers;
            $scope.classTypes = Enums.classTypes;

            $rootScope.$on(Enums.events.CLASS_DESELECTED, function () {
                $scope.$apply(function () {
                    $scope.selected = false;
                });
            });

            $rootScope.$on(Enums.events.CLASS_UPDATED, function (event, clazz) {
                updateFromEvent(clazz);
            });

            $rootScope.$on(Enums.events.CLASS_SELECTED, function (event, clazz) {
                if ($scope.clazz.classId !== clazz.classId) {
                    $scope.$apply(function () {
                        $scope.selected = false;
                    });
                }
            });

            $rootScope.$on(Enums.events.SOCKET_CLASS_REMOVED, function (event, clazz) {
                if ($scope.clazz.classId !== clazz.classId) {
                    return;
                }

                // will trigger other events
                $scope.cell.remove();
            });

            $rootScope.$on(Enums.events.SOCKET_CLASS_UPDATED, function (event, clazz) {
                $scope.$apply(updateFromEvent.bind(null, clazz));
                ignoreNextUpdate = true;
                updatePosition();
            });

            // new id came
            $scope.$on(Enums.events.CLASS_UPDATED, function (event, clazz) {
                if (!$scope.clazz.classId) {
                    $scope.cell.set('classId', clazz.classId);
                    $scope.clazz.classId = clazz.classId;
                }
            });


            element.find('.action-delete').on('click', function () {
                $scope.cell.remove();
            });

            $scope.cell.on('change', function () {
                updateBox();
                updateClazz();
                updateCell();
            });
            $scope.paper.on('cell:pointerclick', function (cellView) {
                $scope.selected = cellView.model.id === $scope.cell.id;
                if ($scope.selected) {
                    $rootScope.$emit(Enums.events.CLASS_SELECTED, $scope.clazz);
                }
            });

            updateBox();
            updateClazz();
            $timeout(function () {
                updateCell();
                $scope.cell.on('remove', removeClazz);
                $scope.cell.on('change:position', _.debounce(notifyUpdate, 500));
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
                if (ignoreNextUpdate) {
                    ignoreNextUpdate = false;
                    return;
                }
                $scope.$emit(Enums.events.CLASS_UPDATED, $scope.clazz);
            }

            function updateFromEvent(clazz) {
                if ($scope.clazz.classId !== clazz.classId) {
                    return;
                }

                angular.extend($scope.clazz, clazz);
            }

            // connection

            element.find('.action-link').on('mousedown', function (event) {
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

            function updatePosition() {
                $scope.cell.set('position', $scope.clazz.position);
            }
        }
    };
});
