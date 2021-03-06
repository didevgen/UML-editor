'use strict';
angular.module('jumlitApp').controller('ClazzSettingsCtrl', function ($scope, Utils, Enums, $rootScope, ClazzServices,
        $timeout) {
    $scope.accessModifiers = Enums.accessModifiers;
    $scope.classTypes = Enums.classTypes;
    $scope.clazz = null;
    $scope.trackUpdates = false;


    var debouncedUpdateClass = _.debounce(ClazzServices.updateClass, 500);
    var updateClazz = function() {
        if (!$scope.clazz || !$scope.trackUpdates) {
            return;
        }
        debouncedUpdateClass($scope.clazz);
    };

    $rootScope.$on(Enums.events.CLASS_SELECTED, function (event, clazz) {
        $scope.$apply(silentUpdate.bind(null, function() {
            $scope.clazz = clazz;
        }));
    });

    $rootScope.$on(Enums.events.CLASS_DESELECTED, function() {
        $scope.trackUpdates = false;
        $scope.clazz = null;
    });

    $rootScope.$on(Enums.events.CLASS_UPDATED, function (event, clazz) {
        updateFromEvent(clazz);
    });

    $rootScope.$on(Enums.events.CLASS_UPDATED, function(event, clazz) {
        updateFromEvent(clazz);
    });

    $scope.$watch('clazz.name', updateClazz);
    $scope.$watch('clazz.accessModifier', updateClazz);
    $scope.$watch('clazz.isStatic', updateClazz);
    $scope.$watch('clazz.classType', updateClazz);

    function silentUpdate(func) {
        $scope.trackUpdates = false;
        func();
        $scope.$$postDigest(function() {
            $scope.trackUpdates = true;
        });
    }

    function updateFromEvent(clazz) {
        if (!$scope.clazz || clazz.classId !== $scope.clazz.classId) {
            return;
        }
        silentUpdate(function() {
            $scope.clazz = clazz;
        });
    }
});