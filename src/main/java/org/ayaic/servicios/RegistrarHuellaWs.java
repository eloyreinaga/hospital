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
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author insane_devil
 */
@ServerEndpoint(value = "/registrarHuella")
public class RegistrarHuellaWs {

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
    public void mensaje(String idpersona, Session session) throws IOException {
        String usr = (String) session.getUserProperties().get("usuario");
        if (usr == null) {
            session.getUserProperties().put("usuario", idpersona);
            //session.getBasicRemote().sendText(buildJsonData("Sistema", "ahora estas conectado como : "+mensaje));
        } else {
            Iterator<Session> iterador = conectados.iterator();
            while (iterador.hasNext()) {
                iterador.next().getBasicRemote().sendText(buildJsonData(usr, idpersona));
            }
        }

    }

    private String buildJsonData(String usr, String idpersona) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("usuario", usr)
                .add("idpersona", idpersona)
                .build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }
}
