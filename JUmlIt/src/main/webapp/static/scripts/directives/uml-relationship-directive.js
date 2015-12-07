/**
 * Created by maxim on 06.12.15.
 */

'use strict';
angular.module('jumlitApp').directive('umlRelationship', function($timeout, Enums, $rootScope) {
    return {
        restrict: 'E',
        scope: {
            cell: '=cellModel',
            relationship: '=',
            graph: '=',
            paper: '='
        },
        templateUrl: 'templates/uml-relationship.html',
        link: function($scope, element) {
            $scope.hovered = false;
            var elementHovered = false;

            $scope.cell.on('change', updateBox);
            $scope.cell.get('target').on('change:position', updateBox);
            $scope.cell.get('source').on('change:position', updateBox);

            element.on('mouseover', function() {
                $timeout(function() {
                    $scope.hovered = true;
                    elementHovered = true;
                });
            });
            element.on('mouseout', function() {
                $timeout(function() {
                    $scope.hovered = false;
                    elementHovered = false;
                });
            });
            element.find('.action-settings').on('click', function() {
                $rootScope.$emit(Enums.events.RELATIONSHIP_SELECTED, $scope.relationship);
            });

            function toggleHovered(value, cellView) {
                var cell = cellView.model;
                if(!cell.isLink() || cell.id !== $scope.cell.id) {
                    return;
                }


                $timeout(function() {
                    if(elementHovered) {
                        return;
                    }
                    $scope.hovered = value;
                });
            }

            $scope.paper.on('cell:mouseover', toggleHovered.bind(null, true));
            $scope.paper.on('cell:mouseout', toggleHovered.bind(null, false));

            $timeout(function() {
                updateBox();
            });

            function updateBox() {
                var elementBox = {
                        width: element.outerWidth(),
                        height: element.outerHeight()
                    },
                    // we get the bounding box of the linkView without the transformations
                    linkView = $scope.cell.findView($scope.paper),
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
