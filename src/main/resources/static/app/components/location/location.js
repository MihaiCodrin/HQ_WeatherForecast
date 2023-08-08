(function() {
    'use strict';

    // Define the component and controller we loaded in our test
    angular.module('components.location', [])
        .controller('LocationController', function(resolvedLocation, Weather) {
            var vm = this;
            vm.location = resolvedLocation;

            // Call our Weather service using our resolved location's city code
            Weather.findById(vm.location.id)
                .then(function(result) {
                    vm.location.weatherDescription = result.weatherDescription;
                })
                .catch(function(result) {
                    // Add the default weather not found text
                    vm.location.weatherDescription = 'Weather data not available';
                });
        })
        .config(function($stateProvider) {
            $stateProvider
                .state('location', {
                    url: '/location/:id',
                    templateUrl: 'components/location/location.html',
                    controller: 'LocationController as lc',
                    resolve: {
                        // Add resolvedUser with a call to Users using $stateParams
                        resolvedLocation: function(Location, $stateParams) {
                            return Location.findById($stateParams.id);
                        }
                    }
                });
        });
})();