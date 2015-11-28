/**
 * Created by maxim on 28.11.15.
 */

'use strict';
angular.module('jumlitApp').controller('CellFieldsCtrl', function($scope) {
    $scope.status = 'viewing';

    $scope.showEditForm = function() {
        $scope.status = 'adding';
        $scope.field = {
            isStatic: 'false',
            accessModifier: 'public',
            name: '',
            returnType: ''
        };
    };

    $scope.hideEditForm = function() {
        $scope.status = 'viewing';
        $scope.field = {
            isStatic: 'false',
            accessModifier: 'public',
            name: '',
            returnType: ''
        };
    };

    $scope.addField = function(field) {
        $scope.cell.fields.push(field);
    };

    $scope.deleteField = function() {
    };

    $scope.editField = function() {
    };
})
