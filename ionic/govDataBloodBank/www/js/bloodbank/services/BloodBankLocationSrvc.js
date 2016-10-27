'use strict';

angular.module('govDataBloodBank').factory('BloodBankLocationSrvc', [BloodBankLocationSrvc]);

function BloodBankLocationSrvc() {
  var bloodBank=null;

  var services = {
    get: get,
    set: set
  };

  return services;

  function get(){
    return bloodBank;
  }

  function set(data){
    bloodBank=data;
  }

}
