/**
 * Created by maxim on 28.11.15.
 */

'use strict';
angular.module('jumlitApp').controller('CellFieldsCtrl', function($scope, Enums) {

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
        $scope.cell.fields.push(field);
        $scope.status = 'viewing';
    };

    $scope.deleteField = function(field) {
    };

    $scope.editField = function(field) {
        $scope.status = 'editing';
        $scope.field = field;
    };

    $scope.saveField = function(field) {
        $scope.status = 'viewing';
    };
})
