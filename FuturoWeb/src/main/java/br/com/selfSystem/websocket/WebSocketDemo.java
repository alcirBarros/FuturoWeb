package br.com.selfSystem.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@RequestScoped
@ServerEndpoint("/echo")
public class WebSocketDemo {

    private static Set userSessions = Collections.synchronizedSet(new HashSet());

    public WebSocketDemo() {
    }

    @OnOpen
    public void onOpen(Session session) {
        userSessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        userSessions.remove(session);
    }

    @OnError
    public void onError(Throwable exception, Session session) {
        userSessions.remove(session);
    }
    
    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        try {
//            if (session.isOpen()) {
//                session.getBasicRemote().sendText(msg, last);
//            }

            for (Object object : userSessions) {
                Session session_ = (Session) object;
                if (session.isOpen()) {
                    System.out.println("Enviando para " + session.getId());
                    session_.getAsyncRemote().sendText(msg);
                }
            }

        } catch (Exception e) {
            //   session.close();
        }
    }
}
