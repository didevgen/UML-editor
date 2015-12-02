/**
 * Created by maxim on 30.11.15.
 */

'use strict';
angular.module('jumlitApp').factory('Field', function(Enums) {
    function Field(data) {

        angular.extend(this, {
            name: '',
            type: '',
            accessModifier: Enums.accessModifiers.DEFAULT,
            isStatic: false
        });

        angular.extend(this, data);
    }

    return Field;
});
