'use strict';
angular.module('jumlitApp').service('HistoryServices', function (Utils, Session, $rootScope, Enums) {
    return {
        getHistory: function(diagramId) {
            return Utils.getRequest('/diagram/' + diagramId + '/history').then(function (history) {
                return history;
            });
        }
    };
});
