/**
 * Created by maxim on 06.12.15.
 */

'use strict';
angular.module('jumlitApp').directive('umlRelationship', function ($timeout, Enums, $rootScope, Links) {

    return {
        priority: 15,
        restrict: 'E',
        scope: {
            relationship: '=',
            graph: '=',
            paper: '='
        },
        link: function ($scope, element) {

            $scope.hovered = false;
            $scope.cell = null;
            $scope.updateLabelsStrategy = null;

            var elementHovered = false;

            $scope.$on('$destroy', function() {
                element.remove();
            });

            $rootScope.$on(Enums.events.RELATIONSHIP_UPDATED, function (event, relationship) {
                if (!relationship || (relationship.id !== $scope.relationship.id)) {
                    return;
                }
                angular.extend($scope.relationship, relationship);
                updateLink(relationship);
            });

            $scope.paper.on('link:options', function (evt, cellView) {
                if(cellView.model.id !== $scope.cell.id) {
                    return;
                }
                $rootScope.$emit(Enums.events.RELATIONSHIP_SELECTED, $scope.relationship);
            });

            $timeout(function () {
                init();
                updateLink();
            });

            function init() {
                $scope.cell = $scope.relationship.cell;
                if (!$scope.cell) {
                    $scope.cell = Links.create($scope.relationship.type, source, target);
                    $scope.graph.addCell($scope.cell);
                }

                var source = $scope.cell.get('source');
                if (!source.on) {
                    source = $scope.graph.getElements().find(function (cell) {
                        return cell.get('classId') === $scope.relationship.primaryMember.classId;
                    });
                    $scope.cell.set('source', source);
                }
                var target = $scope.cell.get('target');
                if (!target.on) {
                    target = $scope.graph.getElements().find(function (cell) {
                        return cell.get('classId') === $scope.relationship.secondaryMember.classId;
                    });
                    $scope.cell.set('target', target);
                }

                $scope.cell.on('remove', removeRelationship);
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
        }
    }
});
