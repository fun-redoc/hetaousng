(function(angular) {
"use strict";
  var deletePerson = function($scope, $http) {
    console.log("delete person", this, $http);
    $http({method:'DELETE', url: this._links.self.href})
      .success(function(data){
        // TODO notify user of success
        var idx = $scope.persons.indexOf(this);
        console.log("success deleting data",$scope.persons, idx, data);
        $scope.persons.splice(idx,1);
      }.bind(this))
      .error(function(error) {
        // TODO notify user of error
        console.log("error", error);
        $scope.error = error;
      })
  }

  var updatePerson = function($http) {
    console.log("update person", this);
  }

  var addPerson = function($http) {
    console.log("add person", this.newPerson);
  }

  angular.module("myApp.controllers")
    .controller("TestController", function($scope, $routeParams, $http, $location) {
      var link = window.decodeURIComponent($routeParams.link);
      console.log("TestController", link);
      $scope.message = "TestController";
      $scope.projects = [];
      $http({method:'GET', url: link})
        .success(function(data) {
          angular.forEach(data._embedded.projects, 
                          function(project){
                            $scope.projects.push(project);
                          }); 
        })
        .error(function(error) {
          $scope.error = error;
        })
    })
    .controller("AppController", function($scope, $http, $location) {
      console.log("AppController");
      $scope.persons = [];
      $scope.addPerson = addPerson.bind($scope);
      $http({method:'GET', url    : "/api/persons"})
        .success(function(data){
          angular.forEach(data._embedded.persons, 
                          function(person){
                            person.delete = deletePerson.bind(person, $scope, $http);
                            person.update = updatePerson.bind(person,$http);
                            $scope.persons.push(person);
                          }); 
        }) 
        .error(function(error) { $scope.error = error})
 });
}(angular));
