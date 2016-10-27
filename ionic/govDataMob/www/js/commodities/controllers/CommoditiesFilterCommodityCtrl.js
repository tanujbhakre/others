angular.module('dataGov').controller('CommoditiesFilterCommodityCtrl', ['$scope', 'parameters','CommoditiesDataSrvc','CheckboxUtil', function($scope, parameters,service,checkboxUtil) {
    var vm = this;
    vm.dataLoaded=false;
    vm.filter = angular.copy(parameters);
    vm.filterOptions={};
    vm.filterOptions.commodity=[];
    function initialize(){
      loadCommodities();
    }

    function loadCommodities(){
      service.getCommodities().then(function(data){
          vm.dataLoaded=true;
          vm.filterOptions.commodities=checkboxUtil.convertToCheckBox(data,vm.filter.commodity);
      });
    }

    vm.save = function() {
      vm.filter.commodity=checkboxUtil.convertToArray(vm.filterOptions.commodities);
      $scope.closeModal(vm.filter);
    };

    vm.cancel = function() {
      $scope.closeModal(null);
    };

    setTimeout(initialize,500);
  }]);
