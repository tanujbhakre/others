'use strict';

angular.module('govDataBloodBank').factory('CheckboxUtil', [CheckboxUtil]);

function CheckboxUtil() {

  var services = {
    convertToCheckBox: convertToCheckBox,
    convertToArray:convertToArray
  };

  return services;

  function convertToCheckBox(array,preselected) {
    var checkbox=[];
    if(array){
      for(var counter=0;counter<array.length;counter++){
        var value=array[counter];
        var isChecked=false;
        if(preselected && preselected.length>0 && preselected.indexOf(value)>-1){
            isChecked=true;
        }
        checkbox.push({
          text:value,
          value:value,
          checked:isChecked
        });
      }
    }
    return checkbox;
  }

  function convertToArray(checkbox) {
    var array=[];
    if(checkbox){
      for(var counter=0;counter<checkbox.length;counter++){
        var value=checkbox[counter];
        if(value.checked){
          array.push(value.value);
        }
      }
    }
    return array;
  }
}
