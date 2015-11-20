'use strict';
/* globals localStorage:true */
angular.module('jumlitApp').factory('Session', function() {
    var Session = JSON.parse(localStorage.getItem('jumlit-session')) || {};

    Session.save = function () {
        localStorage.setItem('jumlit-session', JSON.stringify(this));
    };

    Session.clear = function() {
        localStorage.setItem('jumlit-session', '');
    };

    return Session;
});
