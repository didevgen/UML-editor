'use strict';
angular.module('jumlitApp').controller('CommentsCtrl', function($scope) {
    $scope.toggleComments = function() {
        $scope.$emit('toggleComments');
    };
});