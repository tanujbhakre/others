'use strict';

/**
 * @ngdoc function
 * @name paymentInfoUiApp.controller:PaymentInfoCtrl
 * @description
 * # AboutCtrl
 * Controller of the paymentInfoUiApp
 */
angular.module('paymentInfoUiApp')
  .controller('PaymentInfoCtrl', ['PaymentInfoSrvc','pageSizeVal', PaymentInfoCtrl]);

/**
 * Controller for Payment information view
 * @param {service} service Service for getting payment information
 * @param {integer} pageSize Number of records to be shown on the UI
 */
function PaymentInfoCtrl(service,pageSize) {
  var vm = this;
  //Setting page information
  vm.paymentInfor = {
    data:null,
    display:0,
    currentPage:0,
    total:0,
    pages:[]
  };

  var pageSize=3;

  /**
   * Constructor for the PaymentInfoCtrl
   */
  function initialize() {
    //Loading first page
    vm.loadPage(1);
  }

  /**
   * Function for loading page on the Payment Information Grid
   * @param  {integer} pageNumber Page which is to be loaded to the grid
   */
  vm.loadPage=function(pageNumber){
    //Checking if page is vallid
    if(vm.paymentInfor.pages.length){
        if(pageNumber==0 || pageNumber> vm.paymentInfor.pages.length) return;
    }
    //Crceating offset
    var offset =  ((pageNumber-1) * pageSize) + 1 ;
    //Details for search
    var details = {offset:offset,size:pageSize};
    //Getting the data
    service.getPage(details).then(function(response){
      vm.paymentInfor.data =  response.data.data;
      vm.paymentInfor.display =  response.data.limit;
      vm.paymentInfor.currentPage = response.data.page;
      vm.paymentInfor.total = response.data.total;
      //Checking if pages are to be set
      if(!vm.paymentInfor.pages.length){
        //setting pages
        setTotalPages();
      }

    },function(){
      //TODO Need to update UI for error
    });

  }

  /**
   * Sets the total number of pages
   */
  function setTotalPages(){
      var count = Math.floor(vm.paymentInfor.total/vm.paymentInfor.display);
      count+=(vm.paymentInfor.total%vm.paymentInfor.display>0)?1:0;
      for(var counter=0; counter<count; counter++ ){
        vm.paymentInfor.pages.push( counter);
      }
  }

  initialize();
}
