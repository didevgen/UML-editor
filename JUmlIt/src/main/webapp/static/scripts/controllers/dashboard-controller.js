'use strict';
angular.module('jumlitApp').controller('DashboardCtrl', function($scope, $uibModal, $state) {
    $scope.openDiagram = function() {
angular.module('jumlitApp').controller('DashboardCtrl', function ($scope, $uibModal, $state) {
    /*$scope.editDetails = function() {
        $uibModal.open({
            templateUrl: 'modals/open-diagram-modal.html',
            controller: 'PersonalDetailsModalCtrl'
        });
    };*/

    $scope.editDetails = function () {
        $state.go('account.user-info');
    };

    $scope.openDiagram = function () {
        $state.go('diagram');
    };
});
