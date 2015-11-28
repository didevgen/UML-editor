'use strict';
angular.module('jumlitApp').controller('CellSettingsCtrl', function($scope, Utils) {
    $scope.$on('cellForEdit', function(event, cell) {
        $scope.$apply(function() {
            $scope.cell = {
                name: cell.get('name'),
                methods: cell.get('methods') || [],
                fields: cell.get('fields') || []
            };
        });
    });

    $scope.$on('cellUpdated', function() {
        // fetch new cell data
    });

});