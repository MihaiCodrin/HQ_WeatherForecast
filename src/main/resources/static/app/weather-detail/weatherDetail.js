angular.module('WeatherApp').component('weatherDetail', {
    templateUrl: './app/weather-detail/weatherDetail.html',
    controller: function WeatherDetailController($scope, Weather) {
        Weather.findById(2643743)
            .success(function (data) {
            $scope.weather = data;
            $scope.valid = true;
            })
            .error(function () {
                $scope.valid = false;
            });
    }
});
