/**
 * Created by maxim on 29.11.15.
 */

'use strict';
angular.module('jumlitApp').factory('Clazz', function(Enums) {

    function Clazz(options) {

        angular.extend(this, {
            name: '',
            accessModifier: Enums.accessModifiers.PUBLIC,
            isStatic: false,
            fields: [],
            methods: []
        });

        angular.extend(this, options);

    }

    return Clazz;

});
