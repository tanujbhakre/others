angular.module('govDataBloodBank').controller('BloodBankCtrl',
['$scope','BloodBankDataSrvc','BloodBankLocationSrvc', function($scope,service,location) {
  var vm=this;

  vm.bloodbanks={};

  vm.setToLocation=setToLocation;

  function initialize(){
    service.search().then(
      function(records){
        vm.bloodbanks=records;
      }
    );
  }

  initialize();

  function setToLocation(bloodbank){
    location.set(bloodbank);
  }

}]);
