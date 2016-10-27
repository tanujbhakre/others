'use strict';

angular.module('dataGov').factory('CommoditiesDataSrvc', [
  '$http',
  '$q',
  'QueryUtil',
  'ServiceValBaseURL',
  'ServiceValCommodityResource',
  'ServiceValAuth',
  CommoditiesSrvc
]);

function CommoditiesSrvc($http, $q,util,baseUrl,resource,auth) {

  var services = {
    search:search,
    getCommodities:getCommodities,
    getStates:getStates
  };

  return services;

  function search(queryDetails) {
    var deferred = $q.defer();
    var url = baseUrl;
    var parameters=util.getQuery(queryDetails);
    parameters["api-key"]=auth;
    parameters["resource_id"]=resource;

    $http.get(url,{params:parameters}).then(function(response) {
        deferred.resolve(response.data.records);
      },
      function(error) {
        deferred.reject(error);
      });
    return deferred.promise;

  }

  function getCommodities() {
    var deferred = $q.defer();
    var url = baseUrl;

    var parameters=util.getQuery({fields:["commodity"]});
    parameters["api-key"]=auth;
    parameters["resource_id"]=resource;
    parameters["limit"]=100;
    parameters["offset"]=0;
    var commodities=[];

    var populateDetails=function (){
      $http.get(url,{params:parameters}).then(function(response) {
        var records=response.data.records;

        for(var counter=0;counter<records.length;counter++){
          var value=records[counter].commodity;
          if(commodities.indexOf(value)===-1){
            commodities.push(value);
          }
        }
        if(response.data.count==0){
          deferred.resolve(commodities);
        }else{
          parameters["offset"]=parameters["offset"]+1;
          populateDetails();
        }
      },
      function(error) {
        deferred.reject(error);
      });
    }

    populateDetails();
    return deferred.promise;

  }

  function getStates() {
    var deferred = $q.defer();
    var url = baseUrl;

    var parameters=util.getQuery({fields:["state"]});
    parameters["api-key"]=auth;
    parameters["resource_id"]=resource;
    parameters["limit"]=100;
    parameters["offset"]=0;
    var states=[];

    var populateDetails=function (){
      $http.get(url,{params:parameters}).then(function(response) {
        var records=response.data.records;
        for(var counter=0;counter<records.length;counter++){
          var value=records[counter].state;
          if(states.indexOf(value)===-1){

            states.push(value);
          }
        }
        if(response.data.count==0){
          deferred.resolve(states);
        }else{
          parameters["offset"]=parameters["offset"]+1;
          populateDetails();
        }
      },
      function(error) {
        deferred.reject(error);
      });
    }
    populateDetails();
    return deferred.promise;

  }

}
