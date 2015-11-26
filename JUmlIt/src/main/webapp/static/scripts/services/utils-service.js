'use strict';
angular.module('jumlitApp').service('Utils', function($q, Config, Session) {
    function ajaxRequest(type, url, data) {
        var deferred = $q.defer();
        var requestData = {
            token: Session.token,
            object: data
        };
        $.ajax({
            type: type,
            url: Config.API_PATH + url,
            data: requestData,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            success: function(data) {
                if (data.success) {
                    deferred.resolve(data.object);
                } else {
                    deferred.reject(data.message);
                }
            },
            error: function(error) {
                if (error.status === 200) {
                    deferred.resolve({ok: true});
                    return;
                }
                deferred.reject(error);
            }
        });
        return deferred.promise;
    }
    return {
        getRequest: function(url, data) {
            return ajaxRequest('GET', url, data);
        },
        postRequest: function(url, data) {
            return ajaxRequest('POST', url, data);
        }
    };
});
