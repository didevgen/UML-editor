'use strict';
angular.module('jumlitApp').service('Utils', function($q, Config, Session) {
    function ajaxRequest(type, url, data, options) {
        var deferred = $q.defer();
        data = data || {};

        var defaultOptions = {
            type: type,
            url: Config.API_PATH + url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            headers: {
                'Content-Type': 'application/json'
            },
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            success: function(data) {
                deferred.resolve(data);
            },
            error: function(err) {
                switch (err.status) {
                    case 200:
                        deferred.resolve({ok: true});
                        return;
                    case 401:
                        deferred.reject("unauthorized");
                        return;
                }

                var error;
                try {
                    error = JSON.parse(err.responseText);
                } catch(e) {
                    deferred.reject();
                    return;
                }
                deferred.reject(error.errorMessage);
            }
        };

        options = options || defaultOptions;
        console.log(options);

        $.ajax(options);
        return deferred.promise;
    }
    return {
        getRequest: function(url, data) {
            return ajaxRequest('GET', url, data);
        },
        postRequest: function(url, data, options) {
            return ajaxRequest('POST', url, data, options);
        }
    };
});
