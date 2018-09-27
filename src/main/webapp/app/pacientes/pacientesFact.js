'use strict';
app.factory('buscarHuellaWS', ['$websocket','CONFIG','pacientesServ', function($websocket,CONFIG,pacientesServ) {
  var ws = $websocket("ws://" + "localhost:8080" +"/hospital/buscarHuella");
  let mensajes = [];
  let host = 0;
console.log('asd'+'<c:out value="${dato.id_usuario}"/>');
  ws.onOpen(function() {
    console.log('conectado');
    pacientesServ.getIp()
    .then((response)=>{
        host = response.data[0];
        ws.send(host);
      //  ws.send('8');
    });
  });

  ws.onClose(function(event) {
    console.log('desconectado', event);
  });  
  
   ws.onError(function(event) {
    console.log('connection Error', event);
  });
  
  ws.onMessage(function(event) {
    console.log('datos recibidos: ', event.data);        
    var res = JSON.parse(event.data);
    
    if(CONFIG.IP_USR === res.usuario){        
        getAllPacientesHcl(parseInt(res.mensaje));   
    }
    
  });
     
let getAllPacientesHcl = (hcl)=> {
    pacientesServ.getAllPacientesHcl(hcl)
    .then((response)=>{
        angular.copy(response.data , mensajes);
    },
    response=>console.log(`Error... ${response.status}`)
    );
};
  
  // setTimeout(function() {
  //   ws.close();
  // }, 500)

  return {
    mensajes: mensajes,
    status: function() {
      return ws.readyState;
    },
    send: function(message) {
      if (angular.isString(message)) {
        ws.send(message);
      }
      else if (angular.isObject(message)) {
        ws.send(JSON.stringify(message));
      }
    }

  };
  
}])        
