(function(angular) {
  angular.module("myApp.controllers", []);
  angular.module("myApp.services", []);
  var app = angular.module("myApp", ["ngRoute", "ngResource", "myApp.controllers", "myApp.services"]);

  app.filter('urlencode', function() {
    return function(input) {
      return window.encodeURIComponent(input);
    }
  });

  app.config(function($routeProvider) {
    $routeProvider
      .when('/Test/:link*', {templateUrl: 'templates/test.html', controller: 'TestController'})
      .otherwise({redirectTo:'/'})
  });

}(angular));
