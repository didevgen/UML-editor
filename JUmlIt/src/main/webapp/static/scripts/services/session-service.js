'use strict';
angular.module('jumlitApp').factory('Session', function(Utils) {
    var Session = {};
    Session.lastUpdated = new Date();

    function set(key, value) {
        Session.lastUpdated = new Date();
        Sesion.key = value;
    }

    function get(key) {
        return Session[key];
    }

    return {
        set: set,
        get: get,
        lastUpdated: function() {
            return Session.lastUpdated;
        }
    };
});
