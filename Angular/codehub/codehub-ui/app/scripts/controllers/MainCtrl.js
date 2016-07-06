'use strict';

/**
 * @ngdoc function
 * @name codehubApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codehubApp
 */
angular.module('codehubApp')
  .controller('MainCtrl', ['SubmissionSrvc', 'pageSizeVal', 'statusOptionVal', MainCtrl]);

/**
 * Controller for MainCtrl
 * @param {service} service      HTTP service to get the submission details
 * @param {intgere} pageSize     Default number of records to be shown in a page
 * @param {object} statusOptions Differnt status options
 */
function MainCtrl(service, pageSize, statusOptions) {

  var vm = this;

  vm.statistics = {};
  vm.statusOptions = {};
  vm.submissions = {};
  vm.pageSize = 0;
  vm.pagination = {
    page: 0,
    pageSize: 0,
    total: 0
  };

  vm.onPageChange = onPageChange;
  vm.loadPage = loadPage;
  vm.search = search;

  /**
   * Performs all the oprations which are required on page load
   */
  function initialize() {
    vm.statusOptions = statusOptions;
    vm.pageSize = pageSize;
    loadStaistics();
    //Load first page
    loadPage(1);
  }
    /**
     * Loads the submission staistics
     */
  function loadStaistics(){
    service.getStaistics().then(function(response) {
      vm.statistics = response;
    }, function(error) {
      //TODO Need to handle it gracefully
    });
  }

  /**
   * This method is called when page change is requested as part of pagination
   * @param  {String} msg      Message with page change
   * @param  {[type]} page     Request page
   * @param  {[type]} pageSize Number of records in a page
   * @param  {[type]} total    Total records
   */
  function onPageChange(msg, page, pageSize, total) {
    loadPage(page);
  }

  /**
   * Triggred in case of search, filtering and page size chang
   */
  function search() {
    loadPage(1);
  }

  /**
   * Loads page details for the provided page number
   * @param  {[type]} pageNumber Page number to be loaded
   */
  function loadPage(pageNumber) {
    //Creating offset
    var offset = ((pageNumber - 1) * vm.pageSize) + 1;
    //Details for search
    var details = {
      offset: offset,
      size: vm.pageSize,
      filter: vm.filter
    };
    //Getting the data
    service.getPage(details).then(function(response) {
      //Updating the page details
      vm.submissions = response.data;
      vm.pagination.page = response.page;
      vm.pagination.pageSize = response.limit;
      vm.pagination.total = response.total;
    }, function() {
      // TODO Need to handle it gracefully
    });
  }

  initialize();
}
