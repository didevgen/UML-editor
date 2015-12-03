'use strict';
angular.module('jumlitApp').filter('formatField', function() {
    return function(field) {
        return field.type + ':' + field.name;
    };
}).filter('formatMethod', function() {
    return function(method) {
        return method.returnType + ':' + method.name;
    };
}).filter('formatClassType', function(Enums) {
    return function(classType) {
        switch (classType) {
            case Enums.classTypes.ABSTRACT_CLASS:
                return "<<abstract>>";
            case Enums.classTypes.INTERFACE:
                return "<<interface>>";
        }
    };
});
