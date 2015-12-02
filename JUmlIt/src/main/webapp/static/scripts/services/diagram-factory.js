/**
 * Created by maxim on 29.11.15.
 */

'use strict';
angular.module('jumlitApp').factory('Diagram', function() {
    function Diagram(options) {

        options.classes = options.classes || [];

        angular.extend(this, {
            name: '',
            description: '',
            collaborators: [],
            classes: []
        });

        angular.extend(this, options);

    }
    return Diagram;
});
