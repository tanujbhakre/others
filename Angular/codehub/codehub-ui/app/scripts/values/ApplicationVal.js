'use strict';

/*
 * Context path of the application
 */
angular.module('codehubApp').value('contextPathVal', 'http://localhost:8080/codehub-api');
//Pointing to API on AWS
//angular.module('codehubApp').value('contextPathVal', 'http://codehub-env.us-west-2.elasticbeanstalk.com');
/*
 * Number of rows to be shown on the Grid at a time
 */
angular.module('codehubApp').value('pageSizeVal', 10);

/**
 * Status options to be shown for filtering
 * @type {Array}
 */
var statusOptions=[{name:"Accepted",value:"Accepted"},
{name:"Skipped",value:"Skipped"},
{name:"Memory limit exceeded",value:"Memory limit exceeded"},
{name:"Time limit exceeded",value:"Time limit exceeded"} ,
{name:"Runtime error",value:"Runtime error"},
{name:"Compilation error",value:"Compilation error"},
{name:"Wrong answer",value:"Wrong answer"},
{name:"All",value:""}];

angular.module('codehubApp').value('statusOptionVal', statusOptions);
