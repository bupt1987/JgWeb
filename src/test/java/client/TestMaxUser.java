package client;

import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.session.SessionManager;
import model.AuthResult;
import model.MyWebSocketClient;
import org.java_websocket.drafts.Draft_17;

import java.net.URI;

public class TestMaxUser {

    private final URI uri;

    private String sercret;

    public TestMaxUser(URI uri, String sercret) {
        this.uri = uri;
        this.sercret = sercret;
    }

    public void run() throws Exception {
        long startTime = System.currentTimeMillis();
        MyWebSocketClient ch = new MyWebSocketClient(uri, new Draft_17());
        if (ch.connectBlocking()) {
            InMessage msg = new InMessage("test.test");
            msg.putMember(SessionManager.SECRET, sercret);
            ch.send(msg.toString());

//            System.out.println(ch.getMessage());

            msg = new InMessage("test.test");
            msg.putMember("msg", "test");
            for (int i = 0; i < 10000; i++) {
                Thread.sleep(500000);
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
        for (int i = 1; i <= 60000; i++) {
            MaxUserThread t = new MaxUserThread("test" + i, "123456");
            t.start();
            Thread.sleep(10);
        }
    }

}

class MaxUserThread extends Thread {

    String username;
    String password;

    public MaxUserThread(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println(username);
    }

    @Override
    public void run() {
        try {
            AuthResult ar = TestAuth.auth(username, password);
            if (ar != null) {
                new TestMaxUser(new URI(ar.address), ar.secret).run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
