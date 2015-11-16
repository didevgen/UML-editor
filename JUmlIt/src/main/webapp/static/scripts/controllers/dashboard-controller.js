'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function($scope, $uibModal, $state) {
    $scope.openDiagram = function() {
        $uibModal.open({
            templateUrl: 'modals/open-diagram-modal.html',
            controller: 'PersonalDetailsModalCtrl'
        });
    };
});
