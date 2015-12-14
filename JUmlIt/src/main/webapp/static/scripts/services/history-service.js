'use strict';
angular.module('jumlitApp').service('HistoryServices', function (Utils, Session, $rootScope, Enums) {
    return {
        getSession: function (diagramId, sessionId) {
            return Utils.getRequest('/diagram/' + diagramId + '/history/' + sessionId).then(function (session) {
                return session;
            });
        },
        getHistory: function(diagramId) {
            return Utils.getRequest('/diagram/' + diagramId + '/history').then(function (history) {
                return history;
            });
        }
    };
});
