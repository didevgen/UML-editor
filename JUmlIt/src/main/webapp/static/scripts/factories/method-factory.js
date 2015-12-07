/**
 * Created by maxim on 30.11.15.
 */

'use strict';
angular.module('jumlitApp').factory('Method', function(Enums) {
    function Method(data) {

        angular.extend(this, {
            name: '',
            accessModifier: Enums.accessModifiers.DEFAULT,
            isStatic: false,
            returnType: '',
            args: []
        });

        angular.extend(this, data);
    }

    return Method;
});
