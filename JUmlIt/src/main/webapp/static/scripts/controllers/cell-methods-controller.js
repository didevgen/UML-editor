/**
 * Created by maxim on 28.11.15.
 */

'use strict';
angular.module('jumlitApp').controller('CellMethodsCtrl', function($scope) {
    $scope.status = 'viewing';
    $scope.newArg = {};

    $scope.showEditForm = function() {
        $scope.status = 'adding';
        $scope.method = {
            name: '',
            returnType: '',
            args: []
        };
    };

    $scope.hideEditForm = function() {
        $scope.status = 'viewing';
        $scope.method = {
            name: '',
            returnType: '',
            args: []
        };
    };
    $scope.addArgument = function() {
        $scope.method.args.push($scope.newArg);
        $scope.newArg = {};
    }

    $scope.addMethod = function(method) {
        $scope.cell.methods.push(method);
    };

    $scope.removeMethod = function(method) {
        $scope.cell.methods.push(method);
    };
})
