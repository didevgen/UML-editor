/**
 * Created by maxim on 06.12.15.
 */

angular.module('jumlitApp').controller('RelationshipSettingsCtrl', function($scope, Enums, $rootScope,
        $timeout, ClazzServices) {
    $scope.relationshipTypes = Enums.relationshipTypes;
    $scope.multiplicityTypes = Enums.multiplicityTypes;
    $scope.relationship = null;
    $scope.trackUpdates = false;

    var typesWithName = [
        Enums.relationshipTypes.ASSOCIATION,
        Enums.relationshipTypes.AGGREGATION
    ];

    $rootScope.$on(Enums.events.RELATIONSHIP_SELECTED, function(event, relationship) {
        $scope.$apply(silentUpdate.bind(null, function() {
            $scope.relationship = relationship;
        }));
    });

    $rootScope.$on(Enums.events.RELATIONSHIP_DESELECTED, function() {
        $scope.trackUpdates = false;
        $timeout(function() {
            $scope.relationship = null;
        });
    });

    $rootScope.$on(Enums.events.RELATIONSHIP_UPDATED, function(event, relationship) {
        updateFromEvent(relationship);
    });

    $rootScope.$on(Enums.events.SOCKET_RELATIONSHIP_UPDATED, function(event, relationship) {
        updateFromEvent(relationship);
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

    $scope.reverse = function() {
        var temp = $scope.relationship.primaryMember;
        $scope.relationship.primaryMember = $scope.relationship.secondaryMember;
        $scope.relationship.secondaryMember = temp;
        notifyUpdate();
        $rootScope.$emit(Enums.events.RELATIONSHIP_REVERSED, $scope.relationship);
    }

    var debouncedUpdateRel = _.debounce(ClazzServices.updateRelationship, 500);
    function notifyUpdate() {
        if (!$scope.relationship || !$scope.trackUpdates) {
            return;
        }
        debouncedUpdateRel($scope.relationship);
    }

    function silentUpdate(func) {
        $scope.trackUpdates = false;
        func();
        $scope.$$postDigest(function() {
            $scope.trackUpdates = true;
        });
    }

    function updateFromEvent(relationship) {
        if (!$scope.relationship || $scope.relationship.id !== relationship.id) {
            return;
        }
        $timeout(function() {
            $scope.relationship = relationship;
        });
    }
});
