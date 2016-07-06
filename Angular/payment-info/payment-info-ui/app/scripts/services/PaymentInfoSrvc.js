angular.module('paymentInfoUiApp').factory('PaymentInfoSrvc', [
  '$http',
  '$q',
  'contextPathVal',
  'QueryUtil',
  PaymentInfoSrvc
]);

function PaymentInfoSrvc($http, $q, contextPath,util) {

  var services = {
    getAll: getAll,
    getPage: getPage
  };

  return services;

  /**
   * Get all the Payment Information
   * @return {object} All the payment information
   */
  function getAll() {
    var deferred = $q.defer();
    var url = contextPath+'/payment/options';
    $http.get(url).then(function(response) {
        //console.log("PaymentInfoSrvc::getAllState()::Data Received from end point",response.data.records);
        convertData(response);
        deferred.resolve(response);
      },
      function(error) {
        console.error("PaymentInfoSrvc::getAllState()::Error Occured", error);
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

    var url =  contextPath+'/payment/options';

    $http.get(url,{params:query}).then(function(response) {
        //console.log("PaymentInfoSrvc::getPage()::Data Received from end point",response);
        convertData(response);
        deferred.resolve(response);
      },
      function(error) {
        console.error("PaymentInfoSrvc::getPage()::Error Occured", error);
        deferred.reject(error);
      });
    return deferred.promise;
  }

  /**
   * Converts the Payment information currency details to array for each record
   * @param  {object} response Payment Information response 
   */
  function convertData(response) {
    var data = response.data.data;
    angular.forEach(data, function(value, key) {
      if (value.currencies) {
        value.currencies = JSON.parse(value.currencies).currencies.split(',');
      }
    });
  }

}
