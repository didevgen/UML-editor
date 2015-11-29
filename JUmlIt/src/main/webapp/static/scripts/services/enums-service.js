/**
 * Created by maxim on 29.11.15.
 */

'use strict';
angular.module('jumlitApp').service('Enums', function() {

    return {
        accessModifiers: {
            PUBLIC: {
                name: 'public'
            },
            PRIVATE: {
                name: 'private'
            },
            PROTECTED: {
                name: 'protected'
            },
            DEFAULT: {
                name: 'default'
            }
        }
    };
});
