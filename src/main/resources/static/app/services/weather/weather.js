(function() {
    'use strict';

    // Creating the module and factory we referenced in the beforeEach blocks in our test file
    angular.module('api.weather', [])
        .factory('Weather', function($http) {
            var API = 'http://localhost:8000/weather/current/';

            var Weather = {};

            Weather.findById = function(id) {
                return $http.get(API + id)
                    .then(function(res) {
                        return res.data;
                    })
                    .catch(function (res) {
                        return res.data;
                    });
            };
            return Weather;
        });
})();