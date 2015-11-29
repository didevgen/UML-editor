/**
 * Created by maxim on 28.11.15.
 */

'use strict';
angular.module('jumlitApp').controller('CellMethodsCtrl', function($scope, Enums) {

    $scope.accessModifiers = Enums.accessModifiers;
    $scope.status = 'viewing';
    $scope.newArg = {};

    $scope.showEditForm = function() {
        $scope.status = 'adding';
        $scope.method = {
            isStatic: false,
            accessModifier: Enums.accessModifiers.PUBLIC,
            name: '',
            returnType: '',
            args: []
        };
    };

    $scope.hideEditForm = function() {
        $scope.status = 'viewing';
    };
    $scope.addArgument = function() {
        $scope.method.args.push($scope.newArg);
        $scope.newArg = {};
    }

    $scope.addMethod = function(method) {
        $scope.cell.methods.push(method);
        $scope.status = "viewing";
    };

    $scope.removeMethod = function(method) {
        $scope.cell.methods.push(method);
    };

    $scope.editMethod = function(method) {
        $scope.method = method;
        $scope.status = 'editing';
    };

    $scope.deleteMethod = function(method) {

    };

    $scope.saveMethods = function(method) {
        $scope.status = 'viewing';
    };
})
