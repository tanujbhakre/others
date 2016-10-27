angular.module('dataGov').factory('CommoditiesModalSrvc', ['ModalService', function(modalService) {
  // all app modals here
  var service = {
    getSearchDetails: openSearchDetails,
    getCommodityFilter: openCommodityFilterModal,
    getLocationFilter: openLocationFilterModal,
    getSortOrder: openSortModal
  };

  return service;

  function openSearchDetails(parameters) {
    return modalService.show('templates/commodities/commodities-search-details.html', 'CommoditiesSearchDetailsCtrl as vm', parameters);
  }

  function openCommodityFilterModal(parameters) {
    return modalService.show('templates/commodities/commodities-filter-commodity.html', 'CommoditiesFilterCommodityCtrl as vm', parameters);
  }

  function openLocationFilterModal(parameters) {
    return modalService.show('templates/commodities/commodities-filter-location.html', 'CommoditiesFilterLocationCtrl as vm', parameters);
  }

  function openSortModal(parameters) {
    return modalService.show('templates/commodities/commodities-sort.html', 'CommoditiesSortCtrl as vm', parameters);
  }

}]);
