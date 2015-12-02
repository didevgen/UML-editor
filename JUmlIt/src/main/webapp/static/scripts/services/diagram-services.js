/**
 * Created by maxim on 29.11.15.
 */

'use strict';
angular.module('jumlitApp').service('DiagramServices', function(Utils, Diagram, Session, $rootScope, Enums) {
    function buildModel(diagram) {
        var diagram = _.cloneDeep(diagram);
        //diagram.collaborators = _.map(diagram.collaborators, function(collaborator) {
        //    delete collaborator['@id'];
        //    return collaborator;
        //});
        return diagram;
    }
    return {
        getDiagrams: function() {
            return Utils.postRequest('account/' + Session.user.userId + '/details').then(function(details) {
                return {
                    ownDiagrams: _.map(details.ownDiagrams, function(data) {
                        return new Diagram(data);
                    }),
                    collabDiagrams:_.map(details.collabDiagrams, function(data) {
                        return new Diagram(data);
                    })
                };
            });
        },
        getDiagram: function (id) {
            return Utils.getRequest('diagram/' + id).then(function(data) {
                return new Diagram(data);
            });
        },
        updateDiagram: function (diagram) {
            return Utils.postRequest('diagram/update', buildModel(diagram))
                .then(function(data) {
                    $rootScope.$emit(Enums.events.DIAGRAM_UPDATED, new Diagram(data));
                });
        },
        createDiagram: function (diagram) {
            return Utils.postRequest('diagram/create', buildModel(diagram))
                .then(function(data) {
                    $rootScope.$emit(Enums.events.DIAGRAM_ADDED, new Diagram(data));
                })
        },
        removeDiagram: function (diagram) {
            return Utils.postRequest('diagram/' + diagram.diagramId + '/remove')
                .then(function() {
                    $rootScope.$emit(Enums.events.DIAGRAM_REMOVED, diagram);
                });
        }
    };
});
