'use strict';
angular.module('jumlitApp').controller('DiagramToolsCtrl', function($scope) {
    $scope.groups = {
        general: true
    };
    $scope.figureTypes = [{
        text: 'Class',
        name: 'Class'

    }, {
        name: 'Interface',
        text: 'Interface'
    }, {
        name: 'Abstract',
        text: 'Abstract class'
    }];

    $scope.collapsed = false;
});
