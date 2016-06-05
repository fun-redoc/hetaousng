(function(angular) {
  var CustomerFactory = function($resource) {
    return $resource('/customers/:id', {
      id: '@id'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };
  
  CustomerFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("Customer", CustomerFactory);
}(angular));
