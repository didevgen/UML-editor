/**
 * Created by maxim on 06.12.15.
 */

angular.module('jumlitApp').controller('RelationshipSettingsCtrl', function($scope, Enums, $rootScope,
        $timeout) {
    $scope.relationshipTypes = Enums.relationshipTypes;
    $scope.multiplicityTypes = Enums.multiplicityTypes;
    $scope.relationship = null;

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
});
