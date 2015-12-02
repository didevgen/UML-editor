/**
 * Created by maxim on 29.11.15.
 */

'use strict';
angular.module('jumlitApp').service('Enums', function() {

    return {
        accessModifiers: {
            PUBLIC: 'public',
            PRIVATE: 'private',
            PROTECTED: 'protected',
            DEFAULT: 'default'
        },
        events: {
            CLASS_ADDED: 'classAdded',
            CLASS_UPDATED: 'classUpdated',
            CLASS_SELECTED: 'classSelected',
            CLASS_REMOVED: 'classRemoved',
            CLASS_DESELECTED: 'classDeselected',
            CELL_REMOVED: 'cellRemoved',
            CELL_SELECTED: 'cellSelected',
            CELL_DESELECTED: 'cellDeselected',
            DIAGRAM_ADDED: 'diagramAdded',
            DIAGRAM_REMOVED: 'diagramRemoved',
            DIAGRAM_UPDATED: 'diagramUpdated',
        }
    };

});
