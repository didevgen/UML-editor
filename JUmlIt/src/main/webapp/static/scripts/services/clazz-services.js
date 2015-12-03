/**
 * Created by maxim on 29.11.15.
 */

'use strict';
angular.module('jumlitApp').service('ClazzServices', function (Utils, $rootScope, Clazz, Enums, Session) {

    function notifyClassUpdated(clazz) {
        $rootScope.$emit(Enums.events.CLASS_UPDATED, clazz);
    }

    function prefixUrl(url) {
        return 'diagram/' + Session.diagram.diagramId + '/classes/' + url;
    }

    return {
        getClass: function (id) {
            return Utils.postRequest(prefixUrl(id))
                .then(function (data) {
                    return new Clazz(data);
                });
        },
        updateClass: function (clazz) {
            return Utils.postRequest(prefixUrl('update'), clazz)
                .then(function (data) {
                    return new Clazz(data);
                });
        },
        removeClass: function (clazz) {
            return Utils.postRequest(prefixUrl(clazz.classId + '/remove'));
        },
        createClass: function (clazz) {
            return Utils.postRequest(prefixUrl('add'), clazz)
                .then(function (data) {
                    return new Clazz(data);
                });
        },
        addMethod: function (clazz, method) {
            return Utils.postRequest(prefixUrl(clazz.classId + '/methods/add'), method)
                .then(function(method) {
                    clazz.methods.push(method);
                    notifyClassUpdated(clazz);
                });
        },
        updateMethod: function (clazz, method) {
            return Utils.postRequest(prefixUrl(clazz.classId + '/methods/update'), method)
                .then(function(method) {
                    clazz.methods.splice(_.findIndex(clazz.methods, { id: method.id }), 1, method);
                    notifyClassUpdated(clazz);
                });
        },
        removeMethod: function (clazz, method) {
            return Utils.postRequest(prefixUrl(clazz.classId + '/methods/' + method.id + '/remove'))
                .then(function() {
                    clazz.methods.splice(_.findIndex(clazz.methods, {id: method.id}), 1);
                    notifyClassUpdated(clazz);
                });
        },
        addField: function (clazz, field) {
            return Utils.postRequest(prefixUrl(clazz.classId + '/fields/add'), field)
                .then(function(field) {
                    clazz.fields.push(field);
                    notifyClassUpdated(clazz);
                });
        },
        updateField: function (clazz, field) {
            return Utils.postRequest(prefixUrl(clazz.classId + '/fields/update'), field)
                .then(function(field) {
                    clazz.fields.splice(_.findIndex(clazz.fields, {id: field.id}), 1, field);
                    notifyClassUpdated(clazz);
                });
        },
        removeField: function (clazz, field) {
            return Utils.postRequest(prefixUrl(clazz.classId + '/fields/' + field.id + '/remove'))
                .then(function() {
                    clazz.fields.splice(_.findIndex(clazz.fields, {id: field.id}), 1);
                    notifyClassUpdated(clazz);
                })
        }
    };
});
