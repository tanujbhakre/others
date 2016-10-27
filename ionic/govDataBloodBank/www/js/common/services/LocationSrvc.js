angular.module('govDataBloodBank').factory('LocationSrvc', ['$q','$cordovaGeolocation',
function($q,$cordovaGeolocation) {
  // all app modals here
  var service = {
    getCurrentPosition: getCurrentPosition
  };

  return service;

  function getCurrentPosition() {
    var deferred=$q.defer();
    console.log("getCurrentPosition called");
    var posOptions = {timeout: 10000, enableHighAccuracy: true};
    var promise=$cordovaGeolocation.getCurrentPosition(posOptions).then(
      function(position){
        var newPosition={};
        newPosition.latitude=position.coords.latitude;
        newPosition.longitude=position.coords.longitude;
        deferred.resolve(newPosition);
      },function(error){
        deferred.reject(error);
      }
    );
    return deferred.promise;
  }

}]);
