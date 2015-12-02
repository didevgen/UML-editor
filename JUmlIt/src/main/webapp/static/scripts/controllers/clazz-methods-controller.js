/**
 * Created by maxim on 28.11.15.
 */

'use strict';
angular.module('jumlitApp').controller('ClazzMethodsCtrl', function ($scope, Enums, Utils, ClazzServices) {

    $scope.accessModifiers = Enums.accessModifiers;
    $scope.status = 'viewing';
    $scope.newArg = {};

    $scope.showEditForm = function () {
        $scope.status = 'adding';
        $scope.method = {
            isStatic: false,
            accessModifier: Enums.accessModifiers.PUBLIC,
            name: '',
            returnType: '',
            args: []
        };
    };

    $scope.hideEditForm = function () {
        $scope.status = 'viewing';
    };
    $scope.addArgument = function () {
        $scope.method.args.push($scope.newArg);
        $scope.newArg = {};
    }

    $scope.addMethod = function (method) {
        ClazzServices.addMethod($scope.clazz, method)
            .then(function() {
                $scope.status = "viewing";
            });
    };

    $scope.removeMethod = function (method) {
        ClazzServices.removeMethod($scope.clazz, method)
            .then(function() {
                $scope.status = "viewing";
            });
    };

    $scope.editMethod = function (method) {
        $scope.method = method;
        $scope.status = 'editing';
    };

    $scope.updateMethod = function (method) {
        ClazzServices.updateMethod($scope.clazz, method)
            .then(function() {
                $scope.status = 'viewing';
            });
    };
})
