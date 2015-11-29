'use strict';
angular.module('jumlitApp').controller('CellSettingsCtrl', function($scope, Utils, Enums) {
    $scope.accessModifiers = Enums.accessModifiers;

    $scope.$on('cellForEdit', function(event, cell) {
        $scope.$apply(function() {
            $scope.cell = {
                name: cell.get('name'),
                accessModifier: cell.get('accessModifier') || Enums.accessModifiers.DEFAULT,
                methods: cell.get('methods') || [],
                fields: cell.get('fields') || []
            };
        });
    });

    $scope.$on('cellUpdated', function() {
        // fetch new cell data
    });

});