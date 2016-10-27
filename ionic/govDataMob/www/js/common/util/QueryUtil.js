'use strict';

/**
 * @ngdoc function
 * @name dataGov.service:QueryUtil
 * @description
 * # QueryUtil
 * Service to create queries out of objects for search
 */
angular.module('dataGov').factory('QueryUtil', [QueryUtil]);

function QueryUtil() {

  var services = {
    getQuery: getQuery
  };

  return services;

  //Sample data
  /*var details={
    filter:{name:"[aaaa,gdgdg]",place:"bbbbb",thing:"cccc"},
    sort:{aaa:false,fff:true},
    fields:[name,place],
    offset:3,
    limit:3
  };*/

  /**
   * Creates query object as per API
   * @param  {object} details Contains information about pagination, sortinga nd filtering
   * @return {object}         Query to be passed to API to get correct response
   */
  function getQuery(details) {
    var query = {};
    if(details){
      populateFilters(details.filter, query);
      populateSort(details.sort, query);
      populateFields(details.fields, query);
      populatePage(details, query);
    }
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
        if(fieldValue && fieldValue.length>0){
          query["filters[" + field + "]"] = fieldValue.toString();
        }
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
    if (!isNaN(details.offset) && !isNaN(details.limit)) {
      query["offset"] = details.offset;
      query["limit"] = details.limit;
    }
    return query;
  }

  /**
   * Populates sorting related information to the Query
   * @param  {object} sort  Sorting information
   * @param  {object} query Query object to which sorting details are to be populated
   */
  function populateSort(sort, query) {
      angular.forEach(sort, function(isAscending,field) {
          var order = "";
          if (!isAscending) {
            order= "desc";
          }else{
            order= "asc";
          }
          query["sort["+field+"]"] = order;
      });
    return query;
  }

    function populateFields(fields, query){
      if(fields && fields.length>0){
        query["fields"] = fields.join();
      }
      return query;
    }
}
