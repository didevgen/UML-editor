'use strict';
angular.module('jumlitApp').filter('formatField', function() {
    return function(field) {
        return field.type + ':' + field.name;
    };
}).filter('formatMethod', function() {
    return function(method) {
        return method.returnType + ':' + method.name;
    };
});
