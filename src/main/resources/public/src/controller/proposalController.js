/**
 * Created by semianchuk on 08.10.16.
 */
angular.module('angularApp')
    .controller('proposalController', [ '$scope', '$http', 'states', 'gender', 'civilStatus', 'resultService', 'alertService', 
                                function ($scope, $http,   states,   gender,   civilStatus, ResultService, AlertService) {

        
        $scope.clearScreen = function(){
            $scope.cliente = {
                cpf: undefined,
                dependentes: undefined,
                estado: undefined,
                estadoCivil: undefined,
                genero: undefined,
                idade: undefined,
                nome: undefined,
                renda: undefined
            }

            $scope.requestOn = false;
        }

        //Inicializando objeto cliente
        $scope.clearScreen();

        //Importando os estados da constante 'states'
        $scope.estados = states;

        //Importando os generos da constante 'gender'
        $scope.generos = gender;

        //Importando os estados civis da constante 'civilStatus'
        $scope.estadosCivis = civilStatus;

        //Função para enviar a requisição para salvar a proposta
        $scope.saveClient = function(){
            $scope.requestOn = true;
            $http({
                method: 'post',
                data: $scope.cliente, 
                url: 'cliente/'
            }).then(function (response) {
                //Chamando o service de mostrar o resultado na tela
                ResultService.generateResult(response.data.cpf);
                $scope.clearScreen();
            },function (error){
                //Enviando para a tratativa de alertas
                AlertService.alertMessage(error.data.message);
            });
          
        }



    
    }]);