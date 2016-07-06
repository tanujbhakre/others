'use strict';

/**
 * @ngdoc overview
 * @name codehubApp
 * @description
 * # codehubApp
 *
 * Main module of the application.
 */
angular
  .module('codehubApp', [
    'ngAnimate',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'bw.paging',
    'hljs'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'vm'
      })
      .otherwise({
        redirectTo: '/'
      });
  }).config(function (hljsServiceProvider) {
	hljsServiceProvider.setOptions({
		// replace tab with 2 spaces
		tabReplace: '  '
	});
});
