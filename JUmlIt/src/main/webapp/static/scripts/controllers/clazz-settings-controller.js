'use strict';
angular.module('jumlitApp').controller('ClazzSettingsCtrl', function ($scope, Utils, Enums, $rootScope) {
    $scope.accessModifiers = Enums.accessModifiers;

    $rootScope.$on(Enums.events.CLASS_SELECTED, function (event, clazz) {
        $scope.$apply(function () {
            $scope.clazz = clazz;
            console.log(clazz);
        });
    });

    $rootScope.$on(Enums.events.CLASS_UPDATED, function (clazz) {
        if (clazz.classId !== $scope.clazz.classId) {
            return;
        }
        $scope.clazz = clazz;
    });

    $scope.$watch('clazz.name', function() {

    });

});