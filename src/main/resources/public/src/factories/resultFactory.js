
angular.module('angularApp')
.factory('resultFactory', function($http){
    return {
        //Factory responsável pela busca da análise na API e retornar uma promisse com as informações da busca.
        getResult: function(cpf) {
            return $http({
                    method: 'get',
                    url: `analise/cliente/${cpf}`
                }).then(function(res){ 
                    return(res.data);
                });  
        }
    };
});