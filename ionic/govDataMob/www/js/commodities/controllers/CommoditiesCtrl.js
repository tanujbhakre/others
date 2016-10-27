angular.module('dataGov')
.controller('CommoditiesCtrl', ['$scope','CommoditiesDataSrvc','CommoditiesModalSrvc','PageSizeVal',CommoditiesCtrl]);

function CommoditiesCtrl($scope,service,modalService,pageSize){
  var vm=this;

  vm.dataLoaded=false;
  vm.data=[];
  vm.responseCount=0;
  vm.searchDetails={};

  vm.loadMore=loadMore;
  vm.openSearchDetails=openSearchDetails;


  function initialize(){
    vm.searchDetails.offset=0;
    vm.searchDetails.limit=pageSize;
    loadData();
  }

  function loadData(){
    service.search(vm.searchDetails).then(function(data){
      vm.responseCount=data.length;
      vm.data=data;
      vm.dataLoaded=true;
    },showAlert);
  }

  function loadMore(){
    //Note  It is called offset it is actually a page for the api we are using
    vm.searchDetails.offset=vm.searchDetails.offset+1;
    service.search(vm.searchDetails).then(function(data){
      vm.responseCount=data.length;
      vm.data=vm.data.concat(data);
      $scope.$broadcast('scroll.infiniteScrollComplete');
    },showAlert);
  }

  function openSearchDetails(){
    modalService.getSearchDetails(vm.searchDetails).then(function (details){
      if(details){
        vm.searchDetails=details;
        search();
      }
    });
  }

 function search() {
   vm.dataLoaded=false;
   vm.searchDetails.offset=0;
   vm.searchDetails.limit=pageSize;
   loadData();
 };

 //Logic related to alert starts
  function showAlert(error) {
    var alertPopup = $ionicPopup.alert({
      title: 'Error',
      template: 'Unable to perform operation'
    });

    alertPopup.then(function(res) {
      console.log('Error',error);
    });
}
 //Logic related to alert ends

 //Logic for infinite scrolling
 $scope.$on('$stateChangeSuccess', function() {
   vm.loadMore();
 });

  initialize();
}
