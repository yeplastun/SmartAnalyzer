angular
.module('app')
.config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', '$breadcrumbProvider', function($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, $breadcrumbProvider) {
  $stateProvider
  .state('app.tables', {
    url: "/tables",
    abstract: true,
    template: '<ui-view></ui-view>',
    ncyBreadcrumb: {
      label: 'Tables'
    }
  })
  .state('app.tables.instruments', {
    url: '/instruments',
    templateUrl: 'views/tables/instruments.html',
    ncyBreadcrumb: {
      label: 'Instruments'
    },
    resolve: {
      loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load controllers
        return $ocLazyLoad.load({
          files: ['js/controllers/tables.js']
        });
      }]
    }
  })
  .state('app.tables.counterparties', {
    url: '/counterparties',
    templateUrl: 'views/tables/counterparties.html',
    ncyBreadcrumb: {
      label: 'Counterparties'
    },
    resolve: {
      loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load controllers
        return $ocLazyLoad.load({
          files: ['js/controllers/tables.js']
        });
      }]
    }
  })
  .state('app.tables.deals', {
    url: '/deals',
    templateUrl: 'views/tables/deals.html',
    ncyBreadcrumb: {
      label: 'Deals'
    },
    resolve: {
      loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load controllers
        return $ocLazyLoad.load({
          files: ['js/controllers/tables.js']
        });
      }]
    }
  })
  .state('app.charts', {
    url: "/charts",
    abstract: true,
    template: '<ui-view></ui-view>',
    ncyBreadcrumb: {
      label: 'Charts'
    }
  })
  .state('app.charts.bar', {
    url: '/bar',
    templateUrl: 'views/charts/bar.html',
    ncyBreadcrumb: {
      label: 'Bar'
    },
    resolve: {
      // Plugins loaded before
      // loadPlugin: ['$ocLazyLoad', function ($ocLazyLoad) {
      //     return $ocLazyLoad.load([
      //         {
      //             serial: true,
      //             files: ['js/libs/Chart.min.js', 'js/libs/angular-chart.min.js']
      //         }
      //     ]);
      // }],
      loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load files for an existing module
        return $ocLazyLoad.load({
          files: ['js/controllers/charts.js']
        });
      }]
    }
  })
}]);