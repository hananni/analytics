angular.module('angularApp')
    .controller('resultController', [ '$scope',  'resultService',   function ($scope,  ResultService) {
        
        $scope.cpf = undefined;

        $scope.checkCPF = function(){
            ResultService.generateResult($scope.cpf);
        }

    }]);