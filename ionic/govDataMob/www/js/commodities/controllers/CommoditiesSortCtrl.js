angular.module('dataGov').controller('CommoditiesSortCtrl', ['$scope', 'parameters','CommoditiesValueSrvc',
 function($scope, parameters, service) {
    var vm = this;
    vm.sortOrder={};

    vm.options=[];

    function initialize(){
      vm.sortOrder=parameters;
      vm.options=service.getSortOptions();
    }

    vm.save = function() {
      $scope.closeModal(vm.sortOrder);
    };

    vm.cancel = function() {
      $scope.closeModal(null);
    };

    initialize();
  }]);
