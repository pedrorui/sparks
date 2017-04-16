'use strict';

angular.module('sparks.list', [])

.config(function ($routeProvider) {
    $routeProvider.when('/list', {
        templateUrl: 'templates/views/list.html',
        controller: 'listController'
    })
})

.controller('listController', function($scope, $http, $route, ModalService, Utils) {
    $http.get('/api/appointment').success(function (data) {
        $scope.appointments = data;
    }).error(function (data, status) {
        console.log('Error' + data + ", status" + status);
    });

    $scope.format = Utils.dateFormats[0];

    $scope.delete = function(appointmentId) {
        var modalOptions = {
            closeButtonText: 'Cancel',
            actionButtonText: 'Delete',
            headerText: 'Delete appointment',
            bodyText: 'Are you sure you want to delete this appointment?'
        };

        ModalService.showModal({}, modalOptions).then(function () {
            deleteAppointment(appointmentId);
            $route.reload();
        });
    };

    var deleteAppointment = function (appointmentId) {
        $http.delete('/api/appointment/' + appointmentId).success(function (data) {
            $scope.appointments = data;
        }).error(function (data, status) {
            console.log('Error' + data + ", status" + status);
        });
    };
});