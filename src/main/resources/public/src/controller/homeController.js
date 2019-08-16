
angular.module('angularApp')
    .controller('homeController', [ '$scope', '$state', function ($scope, $state) {
        
        $scope.redirect = function (state){
            $state.go(state);
        }
       
    }]);