'use strict';

/**
 * Service for getting the submission details
 * @param  {String} 'codehubApp' Application name
 */
angular.module('codehubApp').factory('SubmissionSrvc', [
  '$http',
  '$q',
  'contextPathVal',
  'QueryUtil',
  SubmissionSrvc
]);

/**
 * Function for SubmissionSrvc
 * @param {string} contextPath Context path for the application
 * @param {service} util        Utility method for creating query
 */
function SubmissionSrvc($http, $q, contextPath,util) {

  var services = {
    getStaistics: getStaistics,
    getPage: getPage
  };

  return services;

  /**
   * Gets the statistics for Submissions
   * @return {object} All the payment information
   */
  function getStaistics() {
    var deferred = $q.defer();
    var url = contextPath+'/submissions/statistics';
    $http.get(url).then(function(response) {
        //console.log("SubmissionSrvc::getStaistics()::Data Received from end point",response);
        deferred.resolve(response.data);
      },
      function(error) {
        console.error("SubmissionSrvc::getStaistics()::Error Occured", error);
        deferred.reject(error);
      });

    return deferred.promise;
  }

  /**
   * Gets the payment information of a specif page
   * @param  {object} Details of what is to be searched
   * @return {object}  Payment Information details in the requested page
   */
  function getPage(details) {
    var deferred = $q.defer();
    var query=util.getQuery(details);

    var url =  contextPath+'/submissions/search';

    $http.get(url,{params:query}).then(function(response) {
        //console.log("SubmissionSrvc::getPage()::Data Received from end point",response);
        convertData(response);
        deferred.resolve(response.data);
      },
      function(error) {
        console.error("SubmissionSrvc::getPage()::Error Occured", error);
        deferred.reject(error);
      });
    return deferred.promise;
  }

  /**
   * Converts the Submission metadata to JSON object
   * @param  {object} response Payment Information response
   */
  function convertData(response) {
    var data = response.data.data;
    angular.forEach(data, function(value, key) {
      if (value.metadata) {
        value.metadata = JSON.parse(value.metadata);
      }
    });
  }

}
