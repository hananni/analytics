
angular.module('angularApp')
    .config(['$locationProvider','$stateProvider', function($locationProvider,$stateProvider) {
        $locationProvider.html5Mode(true);

        $stateProvider
            .state('home', {
                url         : '/',
                templateUrl : 'public/templates/home.html',
                controller  : 'homeController'
            })            
            .state('proposal', {
                url         : '/app/solicitar',
                templateUrl : 'public/templates/proposal.html',
                controller  : 'proposalController'
            })            
            .state('result', {
                url         : '/app/resultados',
                templateUrl : 'public/templates/result.html',
                controller  : 'resultController'
            })
            .state('module', {
                url         : '/app/module',
                templateUrl : 'public/templates/newmodule.html'
            })
            .state('provider', {
                url         : '/app/provider',
                templateUrl : 'public/templates/provider.html',
                controller  : 'mainController'
            })
    }]);