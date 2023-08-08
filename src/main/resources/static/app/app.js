(function() {
    'use strict';

    angular.module('WeatherApp', [
        'ui.router'
    ])
        .config(function($urlRouterProvider) {
            $urlRouterProvider.otherwise("/");
        });
})();