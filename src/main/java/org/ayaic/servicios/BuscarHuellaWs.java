
package org.ayaic.servicios;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
/**
 *
 * @author insanedevil
 */
@ServerEndpoint(value="/buscarHuella")
public class BuscarHuellaWs {
    
    private static final Set<Session> conectados = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void open(Session session) {
        conectados.add(session);
    }

    @OnClose
    public void close(Session session) {
        conectados.remove(session);
    }
    
    @OnMessage
    public void mensaje(String mensaje,Session session) throws IOException{
        String usuario = (String) session.getUserProperties().get("usuario");
        if(usuario == null){
            session.getUserProperties().put("usuario",mensaje);
            //session.getBasicRemote().sendText(buildJsonData("Sistema", "ahora estas conectado como : "+mensaje));
        }else{
            Iterator<Session> iterador = conectados.iterator();
            while (iterador.hasNext()) iterador.next().getBasicRemote().sendText(buildJsonData(usuario,mensaje));
        }

    }
    
    private String buildJsonData(String usuario, String mensaje){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("usuario",usuario)
                .add("mensaje",mensaje)
                .build();
        StringWriter stringWriter = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringWriter)) {jsonWriter.write(jsonObject);}
        return stringWriter.toString();
    }

    
}
