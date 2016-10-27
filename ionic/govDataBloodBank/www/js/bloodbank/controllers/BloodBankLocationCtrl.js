angular.module('govDataBloodBank').controller('BloodBankLocationCtrl',
['$scope', '$stateParams', 'uiGmapGoogleMapApi', 'BloodBankDataSrvc', 'BloodBankLocationSrvc', function($scope, $stateParams, mapApi, service, location) {
  var vm = this;

  vm.map = {};
  vm.marker = {};
  vm.bloodbank={};

  function initialize() {
    vm.bloodBankId = $stateParams.bloodBankId;

    initializeMarker();
    initializeMap();
    registerMap();
  }

  function initializeMarker() {
    vm.marker = {
      id: 0,
      options: {
        draggable: false
      }
    };
  }

  function initializeMap() {
    vm.map = {
      zoom: 15
    };
  }

  function registerMap() {
    mapApi.then(setPosition, function(error) {
      console.log("Error loading map", error);
    });
  }

  function setPosition(maps) {
    var bloodBank=location.get();
    vm.bloodbank=bloodBank;
    var position = {
      latitude: bloodBank.latitude,
      longitude: bloodBank.longitude
    }
    vm.map.center = position;
    vm.marker.coords = position;
  }

  initialize();

}]);
