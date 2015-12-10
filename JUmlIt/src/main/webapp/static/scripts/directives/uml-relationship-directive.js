/**
 * Created by maxim on 06.12.15.
 */

'use strict';
angular.module('jumlitApp').directive('umlRelationship', function ($timeout, Enums, $rootScope, Links) {

    return {
        restrict: 'E',
        scope: {
            relationship: '=',
            graph: '=',
            paper: '='
        },
        templateUrl: 'templates/uml-relationship.html',
        link: function ($scope, element) {

            $scope.hovered = false;
            $scope.cell = null;
            $scope.updateLabelsStrategy = null;

            var elementHovered = false;

            function init() {
                $scope.cell = $scope.relationship.cell || Links.create($scope.relationship.type, source, target);

                var source = $scope.cell.get('source') || $scope.graph.getElements().find(function (cell) {
                        return cell.get('classId') === $scope.relationship.primaryMemberId;
                    });
                var target = $scope.cell.get('target') || $scope.graph.getElements().find(function (cell) {
                        return cell.get('classId') === $scope.relationship.secondaryMemberId;
                    });

                $scope.cell.on('change', updateBox);
                target.on('change:position', updateBox);
                source.on('change:position', updateBox);
                console.log(target, source);
            }

            init();

            $rootScope.$on(Enums.events.RELATIONSHIP_UPDATED, function (event, relationship) {
                angular.extend($scope.relationship, relationship);
                updateLink(relationship);
            });

            function updateLink() {
                Links.setType($scope.cell, $scope.relationship.type);

                Links.setLabel($scope.cell, Enums.linkLabels.SOURCE, $scope.relationship.secondaryToPrimaryMultiplicity);
                Links.setLabel($scope.cell, Enums.linkLabels.SOURCE_BELOW, $scope.relationship.secondaryToPrimaryProps);
                Links.setLabel($scope.cell, Enums.linkLabels.CENTER, $scope.relationship.name);
                Links.setLabel($scope.cell, Enums.linkLabels.TARGET, $scope.relationship.primaryToSecondaryMultiplicity);
                Links.setLabel($scope.cell, Enums.linkLabels.TARGET_BELOW, $scope.relationship.primaryToSecondaryProps)


            }

            element.on('mouseover', function () {
                $timeout(function () {
                    $scope.hovered = true;
                    elementHovered = true;
                });
            });
            element.on('mouseout', function () {
                $timeout(function () {
                    $scope.hovered = false;
                    elementHovered = false;
                });
            });
            element.find('.action-settings').on('click', function () {
                $rootScope.$emit(Enums.events.RELATIONSHIP_SELECTED, $scope.relationship);
            });

            function toggleHovered(value, cellView) {
                var cell = cellView.model;
                if (!cell.isLink() || cell.id !== $scope.cell.id) {
                    return;
                }


                $timeout(function () {
                    if (elementHovered) {
                        return;
                    }
                    $scope.hovered = value;
                });
            }

            $scope.paper.on('cell:mouseover', toggleHovered.bind(null, true));
            $scope.paper.on('cell:mouseout', toggleHovered.bind(null, false));

            $timeout(function () {
                updateBox();
            });

            function updateBox() {
                var elementBox = {
                        width: element.outerWidth(),
                        height: element.outerHeight()
                    },
                // we get the bounding box of the linkView without the transformations
                    linkView = $scope.paper.findViewByModel($scope.cell),
                    bbox = g.rect(V(linkView.el).bbox(true)),
                    position = {
                        left: bbox.width / 2 + bbox.x - elementBox.width / 2,
                        top: bbox.y + bbox.height / 2 - elementBox.height / 2
                    };

                element.css({
                    left: position.left,
                    top: position.top,
                    transform: 'rotate(' + ($scope.cell.get('angle') || 0) + 'deg)'
                });
            }
        }
    }
});
