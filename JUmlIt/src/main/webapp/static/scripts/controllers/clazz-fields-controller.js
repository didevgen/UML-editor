/**
 * Created by maxim on 28.11.15.
 */

'use strict';
angular.module('jumlitApp').controller('ClazzFieldsCtrl', function($scope, Enums, ClazzServices) {

    $scope.accessModifiers = Enums.accessModifiers;
    $scope.status = 'viewing';

    $scope.showEditForm = function() {
        $scope.status = 'adding';
        $scope.field = {
            isStatic: 'false',
            accessModifier: Enums.accessModifiers.PUBLIC,
            name: '',
            type: ''
        };
    };

    $scope.hideEditForm = function() {
        $scope.status = 'viewing';
    };

    $scope.addField = function(field) {
        ClazzServices.addField($scope.clazz, field)
            .then(function() {
                $scope.status = 'viewing';
            });
    };

    $scope.deleteField = function(field) {
        ClazzServices.removeField($scope.clazz, field);
    };

    $scope.editField = function(field) {
        $scope.status = 'editing';
        $scope.field = field;
    };

    $scope.updateField = function(field) {
        ClazzServices.updateField($scope.clazz, field)
            .then(function() {
                $scope.status = 'viewing';
            });
    };
})
