
angular.module('angularApp')
    .service('resultService', ['resultFactory', 'alertService' , function (ResultFactory, AlertService) {

        //Service responsável por buscar o resultado da análise e disparar a informação pro usuário.
        return { 
            generateResult: function(cpf) {
            //Buscando promisse da factory 
             ResultFactory.getResult(cpf).then(function(res) {
                
                //Desfragmentando as variáveis do response
                const {statusProposta, limiteInicio, limiteFim, motivoNegacao} = res;

                //Ícone do SWAL será successo se caso aprovado, e error caso negado
                var icon = statusProposta == "Aprovado" ? 'success' : 'error';

                //Caso seja aprovado, o texto do swal terá a informação do limite, senão irá imprimir o motivo da negação
                var text = statusProposta == "Aprovado" ? `O limite é de: ${limiteInicio} à ${limiteFim}` : `Motivo: ${motivoNegacao}`
              
                //Montando componente do swal
                swal({
                    title: statusProposta,
                    text: text,
                    icon: icon,
                    button: {
                        className: "btn-success"
                    }
                })
               
            }).catch(function(rej) {
                AlertService.alertMessage(rej.data.message)
            });
        }
    }
    }]);