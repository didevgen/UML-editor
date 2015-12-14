'use strict';
angular.module('jumlitApp')
    .filter('formatField', function () {
        return function (field) {
            return field.name + ': ' + field.type;
        };
    }).filter('formatMethod', function () {
        return function (method) {
            method.args = method.args || [];
            var args = _.map(method.args, function (arg) {
                return arg.type + ' ' + arg.name;
            }).join(', ');
            return method.name + '(' + args + ') : ' + method.returnType;
        };
    }).filter('formatClassType', function (Enums) {
        return function (classType) {
            switch (classType) {
                case Enums.classTypes.ABSTRACT_CLASS:
                    return "<<abstract>>";
                case Enums.classTypes.INTERFACE:
                    return "<<interface>>";
            }
        };
    }).filter('reverse', function() {
        return function(items) {
            return items.slice().reverse();
        };
    });;
