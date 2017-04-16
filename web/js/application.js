'use strict';

var app = angular.module('sparks', ['ngRoute', 'ui.bootstrap', 'sparks.create', 'sparks.list', 'sparks.edit', 'sparks.modal', 'sparks.utils']);

app.config(function ($routeProvider) {
   $routeProvider.otherwise({
       redirectTo: '/'
   })
});

app.controller('navigationController', function($scope, $location) {
    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    }
});