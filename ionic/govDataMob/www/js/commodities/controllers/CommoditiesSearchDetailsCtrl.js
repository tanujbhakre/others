angular.module('dataGov')
.controller('CommoditiesSearchDetailsCtrl', ['$scope','parameters','CommoditiesModalSrvc','CommoditiesValueSrvc',CommoditiesSearchDetailsCtrl]);

function CommoditiesSearchDetailsCtrl($scope,parameters,modalService,valueServive){
  var vm=this;

  vm.searchDetails={};

  vm.openLocationFilter=openLocationFilter;
  vm.openCommodityFilter=openCommodityFilter;
  vm.openSortOrder=openSortOrder;
  vm.clearDetails=clearDetails;
  vm.getSortText=getSortText;


  function initialize(){
    initializeSearchDetails(parameters);
  }

  function initializeSearchDetails(parameters){
    if(!parameters.filter){
      vm.searchDetails.filter={};
    }else{
      vm.searchDetails.filter=parameters.filter;
    }

    if(parameters.sort){
      vm.searchDetails.sort=parameters.sort;
    }
  }


//Logic related to filter popup begins
  function openCommodityFilter(){
    modalService.getCommodityFilter(vm.searchDetails.filter).then(function (filters){
      if(filters){
        vm.searchDetails.filter=filters;
      }
    });
  }

  function openLocationFilter(){
    modalService.getLocationFilter(vm.searchDetails.filter).then(function (filters){
      if(filters){
        vm.searchDetails.filter=filters;
      }
    });
  }

  function openSortOrder(){
    modalService.getSortOrder(vm.searchDetails.sort).then(function (sortOrder){
      if(sortOrder){
        vm.searchDetails.sort=sortOrder;
      }
    });
  }

  function getSortText(){
    return valueServive.getSortText(vm.searchDetails.sort);
  }

  vm.save = function() {
    $scope.closeModal(vm.searchDetails);
  };

  vm.cancel = function() {
    $scope.closeModal(null);
  };


 function clearDetails(){
   vm.searchDetails={};
   vm.searchDetails.filter={}
 }
  initialize();
}
