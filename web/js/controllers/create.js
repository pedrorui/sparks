'use strict';

angular.module('sparks.create', [])

.config(function ($routeProvider) {
    $routeProvider.when('/create', {
        templateUrl: 'templates/views/create.html',
        controller: 'createController'
    })
})

.controller('createController', function($scope, $http, Utils) {
    $scope.appointment = {
        title: '',
        description: '',
        date: Utils.tomorrow()
    };

    $scope.dateControl = {
        visible: false
    };

    $scope.format = Utils.dateFormats[0];

    $scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.dateControl.visible = true;
    };

    $scope.create = function() {
        console.log($scope.appointment);
        $http.post('/api/appointment', $scope.appointment).success(function (data) {
            $scope.added = true;
        }).error(function (data, status) {
            $scope.added = false;
            console.log('Error ' + data + ', status ' + status);
        })
    };
});