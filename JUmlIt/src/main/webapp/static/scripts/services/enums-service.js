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
        classTypes: {
            CLASS: 'Class',
            ABSTRACT_CLASS: 'Abstract class',
            INTERFACE: 'Interface'
        },
        relationshipTypes: {
            DEPENDENCY: 'dependency',
            ASSOCIATION: 'association',
            AGGREGATION: 'aggregation',
            COMPOSITION: 'composition',
            GENERALIZATION: 'generalization',
            REALIZATION: 'realization'
        },
        multiplicityTypes: {
            NONE: '',
            NO_OR_ONE: '0..1',
            EXACTLY_ONE: '1',
            ZERO_OR_MORE: '0..*',
            ONE_OR_MORE: '1..*'
        },
        events: {
            CLASS_ADDED: 'classAdded',
            CLASS_UPDATED: 'classUpdated',
            CLASS_SELECTED: 'classSelected',
            CLASS_REMOVED: 'classRemoved',
            CLASS_DESELECTED: 'classDeselected',
            RELATIONSHIP_CREATED: 'relationshipCreated',
            RELATIONSHIP_SELECTED: 'relationshipSelected',
            RELATIONSHIP_DESELECTED: 'relationshipDeselected',
            RELATIONSHIP_UPDATED: 'relationshipUpdated',
            RELATIONSHIP_REMOVED: 'relationshipRemoved',
            CELL_SELECTED: 'cellSelected',
            CELL_DESELECTED: 'cellDeselected',
            CELL_LINK_STARTED: 'cellLinkStarted',
            DIAGRAM_ADDED: 'diagramAdded',
            DIAGRAM_REMOVED: 'diagramRemoved',
            DIAGRAM_UPDATED: 'diagramUpdated',
        },
        linkLabels: {
            SOURCE: 'source',
            SOURCE_BELOW: 'sourceBelow',
            TARGET: 'target',
            TARGET_BELOW: 'targetBelow',
            CENTER: 'CENTER'
        }
    };

});
