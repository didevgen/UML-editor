/**
 * Created by maxim on 06.12.15.
 */

angular.module('jumlitApp').factory('Relationship', function(Enums) {
    function Relationship(data) {
        angular.extend(this, {
            type: Enums.relationshipTypes.ASSOCIATION,
            name: '',
            primaryToSecondaryMultiplicity: '',
            secondaryToPrimaryMultiplicity: '',
            primaryToSecondaryProps: '',
            secondaryToPrimaryProps: ''
        });
        angular.extend(this, data);
    }
    return Relationship;
});
