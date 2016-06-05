(function(angular) {
"use strict";
  var deleteCustomer = function(customer) {
    console.log("delete customer", customer);
  }

  angular.module("myApp.controllers")
    .controller("AppController", function($scope, $http, $location) {
      console.log("AppController");
      $scope.customers = [];
      $http({method:'GET', url    : "/customers"})
        .success(function(data){
          angular.forEach(data._embedded.customers, 
                          function(customer){
                            customer.delete = deleteCustomer.bind(customer);
                            $scope.customers.push(customer);
                          }); 
        }) 
        .error(function(error) { $scope.error = error})
    });
}(angular));
