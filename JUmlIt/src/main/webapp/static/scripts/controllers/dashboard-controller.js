'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function($scope, $uibModal, $state) {
    $scope.editDetails = function() {
        $uibModal.open({
            templateUrl: 'modals/personal-details-modal.html',
            controller: 'PersonalDetailsModalCtrl'
        });
    };

    $scope.openDiagram = function() {
        $state.go('diagram');
    };
});
