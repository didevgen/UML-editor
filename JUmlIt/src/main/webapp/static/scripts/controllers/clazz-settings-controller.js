'use strict';
angular.module('jumlitApp').controller('ClazzSettingsCtrl', function ($scope, Utils, Enums, $rootScope, ClazzServices) {
    $scope.accessModifiers = Enums.accessModifiers;
    $scope.classTypes = Enums.classTypes;

    var updateClazz = _.debounce(function() {
        if (!$scope.clazz) {
            return;
        }
        ClazzServices.updateClass($scope.clazz);
    }, 500);

    $rootScope.$on(Enums.events.CLASS_SELECTED, function (event, clazz) {
        $scope.$apply(function () {
            $scope.clazz = clazz;
            console.log(clazz);
        });
    });

    $rootScope.$on(Enums.events.CLASS_UPDATED, function (event, clazz) {
        if (!$scope.clazz || clazz.classId !== $scope.clazz.classId) {
            return;
        }
        $scope.clazz = clazz;
    });

    $scope.$watch('clazz.name', updateClazz);
    $scope.$watch('clazz.accessModifier', updateClazz);
    $scope.$watch('clazz.isStatic', updateClazz);
    $scope.$watch('clazz.classType', updateClazz);
});