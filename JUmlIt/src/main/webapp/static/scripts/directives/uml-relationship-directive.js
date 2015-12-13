/**
 * Created by maxim on 06.12.15.
 */

'use strict';
angular.module('jumlitApp').directive('umlRelationship', function ($timeout, Enums, $rootScope, Links,
        ClazzServices) {

    return {
        priority: 10,
        scope: {
            relationship: '=',
            graph: '=',
            paper: '=',
            classesPromise: '='
        },
        link: function ($scope, element) {

            $scope.hovered = false;
            $scope.cell = null;
            $scope.updateLabelsStrategy = null;
            var ignoreNextUpdate = false;

            $scope.$on('$destroy', function () {
                element.remove();
            });

            $rootScope.$on(Enums.events.RELATIONSHIP_UPDATED, function (event, relationship) {
                if (!relationship || (relationship.id !== $scope.relationship.id)) {
                    return;
                }
                angular.extend($scope.relationship, relationship);
                updateLink(relationship);
            });

            $rootScope.$on(Enums.events.RELATIONSHIP_REVERSED, function(event, relationship) {
                if (relationship.id !== $scope.relationship.id) {
                    return;
                }
                var temp = $scope.cell.get('target');
                ignoreNextUpdate = true;
                $scope.cell.set('target', $scope.cell.get('source'));
                ignoreNextUpdate = true;
                $scope.cell.set('source', temp);
            });

            $scope.paper.on('link:options', function (evt, cellView) {
                if (cellView.model.id !== $scope.cell.id) {
                    return;
                }
                $rootScope.$emit(Enums.events.RELATIONSHIP_SELECTED, $scope.relationship);
            });

            $scope.$on(Enums.events.CLASSES_INITIALIZED, function() {
                init();
                updateLink();
            })

            function init() {
                $scope.cell = $scope.relationship.cell;
                if (!$scope.cell) {
                    $scope.cell = Links.create($scope.relationship.type, source, target);
                    $scope.graph.addCell($scope.cell);
                }

                var source = $scope.cell.get('source');
                if (!source.on) {
                    source = $scope.graph.getElements().find(function (cell) {
                        return cell.get('clazz').classId === $scope.relationship.primaryMember.classId;
                    });
                    $scope.cell.set('source', source);
                }
                var target = $scope.cell.get('target');
                if (!target.on) {
                    target = $scope.graph.getElements().find(function (cell) {
                        return cell.get('clazz').classId === $scope.relationship.secondaryMember.classId;
                    });
                    $scope.cell.set('target', target);
                }

                $scope.cell.on('remove', removeRelationship);
                $scope.cell.on('change:source', updateSource)
                $scope.cell.on('change:target', updateTarget)
            }

            function updateLink() {
                Links.setType($scope.cell, $scope.relationship.type);

                Links.setLabel($scope.cell, Enums.linkLabels.SOURCE, $scope.relationship.secondaryToPrimaryMultiplicity);
                Links.setLabel($scope.cell, Enums.linkLabels.SOURCE_BELOW, $scope.relationship.secondaryToPrimaryProps);
                Links.setLabel($scope.cell, Enums.linkLabels.CENTER, $scope.relationship.name);
                Links.setLabel($scope.cell, Enums.linkLabels.TARGET, $scope.relationship.primaryToSecondaryMultiplicity);
                Links.setLabel($scope.cell, Enums.linkLabels.TARGET_BELOW, $scope.relationship.primaryToSecondaryProps)

            }

            function removeRelationship() {
                $scope.$emit(Enums.events.RELATIONSHIP_REMOVED, $scope.relationship);
            }

            function updateSource(link, newValue) {
                if (ignoreNextUpdate) {
                    ignoreNextUpdate = false;
                    return;
                }
                if (newValue.id) {
                    var source = $scope.graph.getElements().find(function(cell) {
                        return cell.id === newValue.id;
                    });
                    $scope.relationship.primaryMember = source.get('clazz');
                    ClazzServices.updateRelationship($scope.relationship);
                }
            }

            function updateTarget(link, newValue) {
                if (ignoreNextUpdate) {
                    ignoreNextUpdate = false;
                    return;
                }
                if (newValue.id) {
                    var target = $scope.graph.getElements().find(function(cell) {
                        return cell.id === newValue.id;
                    });
                    $scope.relationship.secondaryMember = target.get('clazz');
                    ClazzServices.updateRelationship($scope.relationship);
                }
            }
        }
    }
});
