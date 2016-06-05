(function(angular) {
"use strict";
  var deleteCustomer = function($scope, $http) {
    console.log("delete customer", this, $http);
    $http({method:'DELETE', url: this._links.self.href})
      .success(function(data){
        // TODO notify user of success
        var idx = $scope.customers.indexOf(this);
        console.log("success deleting data",$scope.customers, idx, data);
        $scope.customers.splice(idx,1);
      }.bind(this).bind(this.bind(this)))
      .error(function(error) {
        // TODO notify user of error
        console.log("error", error);
        $scope.error = error;
      })
  }

  var updateCustomer = function($http) {
    console.log("update customer", this);
  }

  angular.module("myApp.controllers")
    .controller("AppController", function($scope, $http, $location) {
      console.log("AppController");
      $scope.customers = [];
      $http({method:'GET', url    : "/customers"})
        .success(function(data){
          angular.forEach(data._embedded.customers, 
                          function(customer){
                            customer.delete = deleteCustomer.bind(customer, $scope, $http);
                            customer.update = updateCustomer.bind(customer,$http);
                            $scope.customers.push(customer);
                          }); 
        }) 
        .error(function(error) { $scope.error = error})
    });
}(angular));
