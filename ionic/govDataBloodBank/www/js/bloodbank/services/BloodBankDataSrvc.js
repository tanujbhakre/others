'use strict';

angular.module('govDataBloodBank').factory('BloodBankDataSrvc', [
  '$http',
  '$q',
  'QueryUtil',
  'ServiceValBaseURL',
  'ServiceValBloodBankResource',
  'ServiceValAuth',
  CommoditiesSrvc
]);

function CommoditiesSrvc($http, $q,util,baseUrl,resource,auth) {

  var services = {
    search:search
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

}
