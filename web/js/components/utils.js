'use strict';

var module = angular.module('sparks.utils', []);

module.service('Utils', function() {
    this.convertDate = function(date) {
        return new Date(date);
    };

    this.tomorrow = function () {
        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        return tomorrow;
    };

    this.dateFormats = ['dd-MM-yyyy', 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
});
