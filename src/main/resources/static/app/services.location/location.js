(function() {
    'use strict';

    // Creating the module and factory we referenced in the beforeEach blocks in our test file
    angular.module('api.location', [])
        .factory('Location', function() {

            var locations = [
                {
                    id: 123,
                    cityName: "London"
                }
            ];

            var Location = {};

            Location.findById = function() {
                return locations[0];
            };

            Location.all = function() {
                return locations;
            };

            return Location;
        });
})();