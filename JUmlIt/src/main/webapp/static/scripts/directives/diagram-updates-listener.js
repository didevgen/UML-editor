/**
 * Created by maxim on 13.12.15.
 */

angular.module('jumlitApp').service('DiagramUpdatesListener', function (DiagramUpdates, $rootScope, Enums,
                                                                        Method, Field, Clazz, Relationship) {
    var subscriptions = [];

    function addSubscription(subscription) {
        subscriptions.push(subscription);
    }

    return {
        subscribe: function (diagramId) {
            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_added', function (clazz) {
                $rootScope.$emit(Enums.events.SOCKET_CLASS_ADDED, new Clazz(clazz));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_updated', function (clazz) {
                $rootScope.$emit(Enums.events.SOCKET_CLASS_UPDATED, new Clazz(clazz));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_removed', function (classId) {
                $rootScope.$emit(Enums.events.SOCKET_CLASS_REMOVED, new Clazz({classId: classId}));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_method_added', function (method) {
                $rootScope.$emit(Enums.events.SOCKET_METHOD_ADDED, new Method(method));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_method_updated', function (method) {
                $rootScope.$emit(Enums.events.SOCKET_METHOD_UPDATED, new Method(method));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_method_removed', function (methodId) {
                $rootScope.$emit(Enums.events.SOCKET_METHOD_REMOVED, new Method({methodId: methodId}));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_field_added', function (field) {
                $rootScope.$emit(Enums.events.SOCKET_FIELD_ADDED, new Field(field));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_field_updated', function (field) {
                $rootScope.$emit(Enums.events.SOCKET_FIELD_UPDATED, new Field(field));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/clazz_field_removed', function (fieldId) {
                $rootScope.$emit(Enums.events.SOCKET_FIELD_REMOVED, new Field({fieldId: fieldId}));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/relationship_added', function (relationship) {
                $rootScope.$emit(Enums.events.SOCKET_RELATIONSHIP_CREATED, new Relationship(relationship));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/relationship_updated', function (relationship) {
                $rootScope.$emit(Enums.events.SOCKET_RELATIONSHIP_UPDATED, new Relationship(relationship));
            }).then(addSubscription);

            DiagramUpdates.subscribe('/topic/diagram/' + diagramId + '/relationship_removed', function (id) {
                $rootScope.$emit(Enums.events.SOCKET_RELATIONSHIP_REMOVED, new Relationship({id: id}));
            }).then(addSubscription);
        },
        unsubscribe: function () {
            subscriptions.forEach(function (subscription) {
                subscription.unsubscribe();
            });
        }
    }
});
