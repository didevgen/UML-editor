'use strict';
angular.module('jumlitApp').controller('CommentsCtrl', function($scope, DiagramUpdates, $timeout, $rootScope, Enums) {
    $scope.comment = '';

    $scope.sendComment = function() {
        DiagramUpdates.send('/diagram/' + $scope.diagram.diagramId + '/comment', $scope.comment);
        $scope.comment = '';
    }

    $scope.toggleComments = function() {
        $scope.$emit('toggleComments');
    };

    $rootScope.$on(Enums.events.SOCKET_DIAGRAM_COMMENT, function(event, comment) {
        $timeout(function() {
            console.log(comment);
            $scope.diagram.comments.push(comment);
        });
    });

    $rootScope.$on(Enums.events.COMMENTS_OPENED, function() {
        $timeout(function() {
            var objDiv = $(".comments-list-container")[0];
            objDiv.scrollTop = objDiv.scrollHeight;
        })
    });

});