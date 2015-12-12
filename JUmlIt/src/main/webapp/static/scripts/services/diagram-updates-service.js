/**
 * Created by maxim on 11.12.15.
 */

angular.module('jumlitApp').service('DiagramUpdates', function($rootScope, $q) {
    var socket = new SockJS('http://localhost:8080/sigma/ws');
    var client = Stomp.over(socket);
    var connected = false;

    client.connect({}, function(frame) {
        connected = true;
    });

    function ensureConnected() {
        if (connected) {
            return $q.when();
        }
        var deferred = $q.defer();
        var interval = setInterval(function() {
            if (connected) {
                clearInterval(interval);
                deferred.resolve();
            }
        });
        return deferred.promise;
    }

    return {
        subscribe: function(path, callback) {
            return ensureConnected().then(function() {
                return client.subscribe(path, function(data) {
                    callback(JSON.parse(data.body), data.headers);
                });
            });
        }
    };
});
