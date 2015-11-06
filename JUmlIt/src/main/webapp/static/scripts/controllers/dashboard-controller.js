'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function($scope, $uibModal) {
    $scope.editDetails = function() {
        $uibModal.open({
            templateUrl: 'modals/personal-details-modal.html',
            controller: 'PersonalDetailsModalCtrl'
        });
    };
});
