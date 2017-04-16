'use strict';

angular.module('sparks.edit', [])

    .config(function ($routeProvider) {
        $routeProvider.when('/edit/:param', {
            templateUrl: 'templates/views/edit.html',
            controller: 'editController'
        })
    })

    .controller('editController', function($scope, $http, $routeParams, Utils) {
        $scope.dateControl = {
            visible: false
        };

        $scope.format = Utils.dateFormats[0];

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.dateControl.visible = true;
        };

        $http.get('/api/appointment/' + $routeParams.param).success(function (data) {
            var appointment = data;
            appointment.date = Utils.convertDate(appointment.date);
            $scope.appointment = appointment;
        }).error(function (data, status) {
            console.log('Error' + data + ", status" + status);
        });

        $scope.edit = function() {
            console.log($scope.appointment);
            $http.post('/api/appointment', $scope.appointment).success(function (data) {
                $scope.edited = true;
            }).error(function (data, status) {
                $scope.edited = false;
                console.log('Error' + data + ", status" + status);
            })
        };
    });