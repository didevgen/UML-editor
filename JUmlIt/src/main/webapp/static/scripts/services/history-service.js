'use strict';
angular.module('jumlitApp').service('HistoryServices', function (Utils) {

    return {
        getHistory: function(diagramId) {
            return Utils.postRequest('diagram/' + diagramId + '/history', {}).then(function (history) {
            console.log(history);
                return history;
            });
        }
    };
});
