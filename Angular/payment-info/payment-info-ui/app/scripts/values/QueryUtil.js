'use strict';

/**
 * @ngdoc function
 * @name commoditiesApp.service:QueryUtil
 * @description
 * # QueryUtil
 * Service to create queries out of objects for search
 */
angular.module('paymentInfoUiApp').factory('QueryUtil', [QueryUtil]);

function QueryUtil() {

  var services = {
    getQuery: getQuery
  };

  return services;

  //Sample data
  /*var details={
    filter:{name:"aaaa",place:"bbbbb",thing:"cccc"},
    sort:{aaa:false,fff:true},
    offset:3,
    size:3
  };*/

  /**
   * Creates query object as per API
   * @param  {object} details Contains information about pagination, sortinga nd filtering
   * @return {object}         Query to be passed to API to get correct response
   */
  function getQuery(details) {
    var query = {};
    populateFilters(details.filter, query);
    populateSort(details.sort, query);
    populatePage(details, query);
    return query;
  }

  /**
   * Populates the query with filter data
   * @param  {object} filter Filter add dadta to Query
   * @param  {object} query  Query to which filter details are to be populated
   */
  function populateFilters(filter, query) {
    var filterQuery = new Array();
      angular.forEach(filter, function(fieldValue, field) {
          query["filter[" + field + "]"] = fieldValue;
      });
    return filterQuery;
  }

  /**
   * Populated the page related information
   * @param  {object} details Details containing pagination related information
   * @param  {object} query   Query to be populated with paging related information
   * @return {[type]}         [description]
   */
  function populatePage(details, query) {
    if (!isNaN(details.offset) && !isNaN(details.size)) {
      query["page[offset]"] = details.offset;
      query["page[size]"] = details.size;
    }
    return query;
  }

  /**
   * Populates sorting related information to the Query
   * @param  {object} sort  Sorting information
   * @param  {object} query Query object to which sorting details are to be populated
   */
  function populateSort(sort, query) {
    var sortQuery = "";
      angular.forEach(sort, function(isAscending,field) {
          if (sortQuery !== "") {
            sortQuery += ",";
          }
          if (!isAscending) {
            sortQuery += "-";
          }
          sortQuery += field;
      });
      if(sortQuery!==""){
        query["sort"] = sortQuery;
      }
    return query;
  }
}
