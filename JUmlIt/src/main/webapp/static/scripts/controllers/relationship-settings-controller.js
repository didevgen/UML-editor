/**
 * Created by maxim on 06.12.15.
 */

angular.module('jumlitApp').controller('RelationshipSettingsCtrl', function($scope, Enums, $rootScope,
        $timeout, ClazzServices) {
    $scope.relationshipTypes = Enums.relationshipTypes;
    $scope.multiplicityTypes = Enums.multiplicityTypes;
    $scope.relationship = null;

    var typesWithName = [
        Enums.relationshipTypes.ASSOCIATION,
        Enums.relationshipTypes.AGGREGATION
    ];

    $rootScope.$on(Enums.events.RELATIONSHIP_SELECTED, function(event, relationship) {
        $timeout(function() {
            $scope.relationship = relationship;
        });
    });

    $rootScope.$on(Enums.events.RELATIONSHIP_DESELECTED, function() {
        $timeout(function() {
            $scope.relationship = null;
        });
    });

    $scope.showNameInput = function() {
        if (!$scope.relationship) {
            return false;
        }
        return typesWithName.indexOf($scope.relationship.type) !== -1;
    };

    $scope.setType = function(type) {
        if (!$scope.relationship) {
            return;
        }

        $scope.relationship.type = type;

        if (typesWithName.indexOf($scope.relationship.type) === -1) {
            $scope.relationship.name = '';
        }
    }

    $scope.$watch('relationship.type', notifyUpdate);
    $scope.$watch('relationship.name', notifyUpdate);
    $scope.$watch('relationship.primaryToSecondaryMultiplicity', notifyUpdate);
    $scope.$watch('relationship.secondaryToPrimaryMultiplicity', notifyUpdate);

    function notifyUpdate() {
        ClazzServices.updateRelationship($scope.relationship);
    }
});
