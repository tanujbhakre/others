var sortOptions=[{
  text:"Minimum Price Low to High",
  value:{min_price:true}
},{
  text:"Minimum Price High to Low",
  value:{min_price:false}
},{
  text:"Maximum Price Low to High",
  value:{max_price:true}
},{
  text:"Maximum Price High to Low",
  value:{max_price:false}
},{
  text:"Modal Price Low to High",
  value:{modal_price:true}
},{
  text:"Modal Price High to Low",
  value:{modal_price:false}
},{
  text:"Item Name in Ascending Order",
  value:{commodity:true}
},{
  text:"Item Name in Descending Order",
  value:{commodity:false}
},{
  text:"State Name in Ascending Order",
  value:{state:true}
},{
  text:"State Name in Descending Order",
  value:{state:false}
}];

angular.module('dataGov').value('CommoditiesSortVal', sortOptions);
