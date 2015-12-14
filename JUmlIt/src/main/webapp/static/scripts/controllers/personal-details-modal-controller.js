'use strict';
angular.module('jumlitApp').controller('PersonalDetailsModalCtrl', function ($scope, $uibModalInstance, Session, $state, UserServices, Authentication) {
    $scope.user = angular.copy(Session.user);

    $scope.alerts = [];

    $scope.save = function () {
        var newUser = {
            userId: $scope.user.userId,
            email: $scope.user.email,
            fullname: $scope.user.fullname
        }

        UserServices.updateUser(newUser)
            .then(function () {
                Authentication.authenticate();
                $state.go($state.current, {}, {
                    reload: true
                });
                $uibModalInstance.close();
            })
            .catch(function (error) {
                $scope.alerts.push({
                    type: 'danger',
                    msg: error
                });
            });;
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };


    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };

});
