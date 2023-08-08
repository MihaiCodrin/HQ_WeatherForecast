describe('components.location', function() {
    var $controller, Weather, $q, $httpBackend;
    var API = 'http://localhost:8000/weather/current/';

    var RESPONSE_SUCCESS = {
        id: 2643743,
        cityName: 'London',
        weatherDescription: 'light rain',
        temperatureInFahrenheit: 58.79999923706055,
        dateToday: "19-09-2016",
        timeAtSunrise: "2:43am",
        timeAtSunset: "3:02pm",
        temperatureInCelsius: 14.888888465033638
    };

    // Add mocked Error response
    var RESPONSE_ERROR = {
        'weather': 'Not found.'
    };

    // Load ui.router and our components.profile module which we'll create next
    beforeEach(angular.mock.module('ui.router'));
    beforeEach(angular.mock.module('api.weather'));
    beforeEach(angular.mock.module('components.location'));

    // Inject Weather factory, $q, and $httpBackend for testing HTTP requests
    beforeEach(inject(function(_$controller_, _Weather_, _$q_, _$httpBackend_) {
        $controller = _$controller_;
        Weather = _Weather_;
        $q = _$q_;
        $httpBackend = _$httpBackend_;
    }));

    describe('LocationController', function() {
        var LocationController, singleLocation;

        beforeEach(function() {

            // Define singleUser and add resolvedUser as a dependency to our controller
            singleLocation = {
                id: 123,
                cityName: 'London'
            };

            // Create an instance of our controller
            LocationController = $controller('LocationController', { resolvedLocation: singleLocation, Weather: Weather });
        });

        // Verify our controller exists
        it('should be defined', function() {
            expect(LocationController).toBeDefined();
        });

    });

    describe('LocationController with a valid resolved location and a valid Weather factory', function() {
        var LocationController, singleLocation;

        beforeEach(function() {

            // Define singleUser and add resolvedUser as a dependency to our controller
            singleLocation = {
                id: 123,
                cityName: 'London'
            };

            spyOn(Weather, "findById").and.callThrough();

            // Create an instance of our controller
            LocationController = $controller('LocationController', { resolvedLocation: singleLocation, Weather: Weather });
        });

        // Verify our controller exists
        it('should be defined', function() {
            expect(LocationController).toBeDefined();
        });

        it('should set the view model location object to the resolvedLocation', function() {
            expect(LocationController.location).toEqual(singleLocation);
        });

        it('should call Weather.findById and return a Weather object', function() {
            expect(LocationController.location.weather).toBeUndefined();
            expect(LocationController.location.id).toEqual(123);
            expect(LocationController.location.cityName).toEqual('London');
            $httpBackend.whenGET(API + singleLocation.id).respond(200, $q.when(RESPONSE_SUCCESS));
            $httpBackend.flush();
            expect(LocationController.location.weatherDescription).toEqual(RESPONSE_SUCCESS.weatherDescription);
        });

        it('should call Weather.findById with nonexistent id and default to a weather not available message', function() {
            expect(LocationController.location.weatherDescription).toBeUndefined();

            // Declare the endpoint we expect our service to hit and provide it with our mocked return values
            $httpBackend.whenGET(API + singleLocation.id).respond(404, $q.reject(RESPONSE_ERROR));
            $httpBackend.flush();

            // Add expectation that our weather description will be set to a default 'not available' message
            expect(LocationController.location.weatherDescription).toBeDefined();
            expect(LocationController.location.weatherDescription).toEqual('Weather data not available');
        });
    });
});