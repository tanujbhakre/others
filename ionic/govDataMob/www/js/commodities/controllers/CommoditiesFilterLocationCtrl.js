angular.module('dataGov').controller('CommoditiesFilterLocationCtrl', ['$scope', 'parameters','CommoditiesDataSrvc','CheckboxUtil', function($scope, parameters,service,checkboxUtil) {
    var vm = this;
    vm.dataLoaded=false;
    vm.filter = angular.copy(parameters);
    vm.filterOptions={};
    vm.filterOptions.states=[];
    function initialize(){
      loadStates();
    }

    function loadStates(){
      service.getStates().then(function(data){
        vm.dataLoaded=true;
          vm.filterOptions.states=checkboxUtil.convertToCheckBox(data,vm.filter.state);
      });
    }

    vm.save = function() {
      vm.filter.state=checkboxUtil.convertToArray(vm.filterOptions.states);
      $scope.closeModal(vm.filter);
    };

    vm.cancel = function() {
      $scope.closeModal(null);
    };

    setTimeout(initialize,500);

  }]);
