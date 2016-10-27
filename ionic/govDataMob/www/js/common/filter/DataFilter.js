angular.module('dataGov').filter('DataFilter', function() {
  return function(input,fields,data) {
      var out = [];
      if(fields && data){
        var filterOnFields=fields.split(',');
        angular.forEach(input, function(item) {
          for(var counter=0;counter<filterOnFields.length;counter++){
            var smallData=data.toLowerCase();
            var smallFieldData=item[filterOnFields[counter]].toLowerCase();
            if (smallFieldData.indexOf(smallData)>-1) {
              out.push(item);
              break;
            }
          }
        })
        return out;
      }else{
        return input;
      }
  }
});
