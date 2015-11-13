'use strict';
angular.module('jumlitApp').controller('DiagramToolsCtrl', function($scope) {
    $scope.groups = {
        general: true
    };
    $scope.figureTypes = [{
        name: 'Class'
    }, {
        name: 'Interface'
    }];
});
