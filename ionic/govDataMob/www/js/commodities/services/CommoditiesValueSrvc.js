angular.module('dataGov').factory('CommoditiesValueSrvc', ['CommoditiesSortVal',function(sortValues) {
  // all app modals here
  var service = {
    getSortOptions: getSortOptions,
    getSortText:getSortText
  };

  return service;

  function getSortOptions() {
    return sortValues;
  }

  function getSortText(sort){
    if(sort){
      var sortOptions=getSortOptions();
      for(var counter=0;counter<sortOptions.length;counter++){
        if(angular.equals(sortOptions[counter].value,sort)){
          return sortOptions[counter].text;
        }
      }
    }
    return "";
  }

}]);
