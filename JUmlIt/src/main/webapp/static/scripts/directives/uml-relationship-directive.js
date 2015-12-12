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
        templateUrl: 'templates/uml-relationship.html',
        link: function ($scope, element) {

            $scope.hovered = false;
            $scope.cell = null;
            $scope.updateLabelsStrategy = null;

            var elementHovered = false;


            $rootScope.$on(Enums.events.RELATIONSHIP_UPDATED, function (event, relationship) {
                if (!relationship || (relationship.id !== $scope.relationship.id)) {
                    return;
                }
                angular.extend($scope.relationship, relationship);
                updateLink(relationship);
            });

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

            $scope.paper.on('cell:mouseover', toggleHovered.bind(null, true));
            $scope.paper.on('cell:mouseout', toggleHovered.bind(null, false));

            $timeout(function () {
                init();
                updateBox();
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

                $scope.cell.on('change', updateBox);
                target.on('change:position', updateBox);
                source.on('change:position', updateBox);
            }

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

            function updateLink() {
                Links.setType($scope.cell, $scope.relationship.type);

                Links.setLabel($scope.cell, Enums.linkLabels.SOURCE, $scope.relationship.secondaryToPrimaryMultiplicity);
                Links.setLabel($scope.cell, Enums.linkLabels.SOURCE_BELOW, $scope.relationship.secondaryToPrimaryProps);
                Links.setLabel($scope.cell, Enums.linkLabels.CENTER, $scope.relationship.name);
                Links.setLabel($scope.cell, Enums.linkLabels.TARGET, $scope.relationship.primaryToSecondaryMultiplicity);
                Links.setLabel($scope.cell, Enums.linkLabels.TARGET_BELOW, $scope.relationship.primaryToSecondaryProps)

            }
        }
    }
});
