'use strict';

/**
 * @ngdoc overview
 * @name paymentInfoUiApp
 * @description
 * # paymentInfoUiApp
 *
 * Main module of the application.
 */
angular
  .module('paymentInfoUiApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'objectTable'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/main', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/payment-info', {
        templateUrl: 'views/payment-info.html',
        controller: 'PaymentInfoCtrl',
        controllerAs: 'paymentInfo'
      })
      .otherwise({
        redirectTo: '/payment-info'
      });
  });
