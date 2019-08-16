
angular.module('angularApp')
.service('alertService', [ function () {
    //Service responsável por tratar a mensagem de alerta.
    return { 
        alertMessage: function(message) {
            //Montando componente do swal
            swal({
                title: "Alerta",
                text: message,
                icon: 'warning',
                button: {
                    className: "btn-success"
                }
            });
      
    }
}
}]);