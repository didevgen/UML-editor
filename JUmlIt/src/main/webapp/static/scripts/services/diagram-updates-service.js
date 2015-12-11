/**
 * Created by maxim on 11.12.15.
 */

angular.module('jumlitApp').service('DiagramUpdates', function($rootScope) {
    var socket = new SockJS('http://localhost:8080/sigma/ws');
    var client = Stomp.over(socket);
    var connected = false;

    client.connect({}, function(frame) {
        connected = true;
    });

    function ensureConnected(callback) {
        if (connected) {
            callback();
        }
        var interval = setInterval(function() {
            if (connected) {
                clearInterval(interval);
                callback();
            }
        });
    }

    return {
        subscribe: function(path, callback) {
            ensureConnected(function() {
                client.subscribe(path, function(data) {
                    callback(JSON.parse(data.body), data.headers);
                });
            });
        }
    };
});
