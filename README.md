WeatherHQ
==============
Spring Boot web application with backend integration to OpenWeatherMap RESTful API

Technologies used
--------------
* Java 8
* Spring Boot 1.4
* Angular 1.5 (Angular components)
* Maven
* Mockito
* AssertJ
* Jasmine
* Karma
* Node.js (npm)
* Git
* Bootstrap

Running instructions
--------------------
The project can be run from within an IDE (such as IntelliJ) or directly from the command-line, like so:
* ./mvnw spring-boot:run -Drun.arguments="--open.weather.map.api.key=$INSERT_OPEN_WEATHER_MAP_API_KEY_HERE"

* Once the embedded Tomcat has started the web application can be viewed by visiting the following URL in the browser:
http://localhost:8080


To run the frontend (AngularJs) unit tests run the following commands:
* npm install
* karma start

To do
--------------
* Increase unit test coverage
* Add unit tests for UI-Routing
* Add integration tests
* Validate accuracy of OpenWeatherMap sunrise and sunset times
* Finish AngularJS frontend 'weatherDetail' component by adding support for the input of city code via the UI (form).