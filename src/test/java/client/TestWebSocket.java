package client;

import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.session.SessionManager;
import model.AuthResult;
import model.MyWebSocketClient;
import org.java_websocket.drafts.Draft_17;

import java.net.URI;

public class TestWebSocket {

    private final URI uri;

    private String sercret;

    public TestWebSocket(URI uri, String sercret) {
        this.uri = uri;
        this.sercret = sercret;
    }

    public void run() throws Exception {
        long startTime = System.currentTimeMillis();
        MyWebSocketClient ch = new MyWebSocketClient(uri, new Draft_17());
        if (ch.connectBlocking()) {
            InMessage msg = new InMessage("init");
            msg.putMember(SessionManager.SECRET, sercret);
            ch.send(msg.toString());

            System.out.println(ch.getMessage());

            msg = new InMessage("test.test");
            msg.putMember("msg", "test");
            for (int i = 0; i < 100000; i++) {
                ch.send(msg.toString());
            }

            ch.closeBlocking();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time : " + endTime + " | run time :" + (endTime - startTime));
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        for (int i = 1000; i < 1100; i++) {
            WebSocketThread t = new WebSocketThread("test" + i, "123456");
            t.start();
            Thread.sleep(10);
        }
    }
}

class WebSocketThread extends Thread {

    String username;
    String password;

    public WebSocketThread(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        try {
            AuthResult ar = TestAuth.auth(username, password);
            if (ar != null) {
                new TestWebSocket(new URI(ar.address), ar.sercret).run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
