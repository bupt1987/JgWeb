package client;

import com.zhaidaosi.game.jgframework.common.BaseSocket;
import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.session.SessionManager;
import model.AuthResult;

import java.net.URI;

class TestSocket {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        System.out.println("start time : " + startTime);
        for (int i = 1; i <= 100; i++) {
            MyThread t = new MyThread("test" + i, "123456");
            t.start();
        }
    }

}

class MyThread extends Thread {

    String username;
    String password;

    public MyThread(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void run() {
        try {
            long startTime = System.currentTimeMillis();
            AuthResult ar = TestAuth.auth(username, password);
            if (ar != null) {
                InMessage msg = new InMessage("init");
                msg.putMember(SessionManager.SECRET, ar.sercret);
                URI uri = new URI(ar.address);
                BaseSocket serverSocket = BaseSocket.getNewInstance(uri.getHost(), uri.getPort());

                System.out.println(serverSocket.request(msg));

                msg = new InMessage("test.test");
                msg.putMember("msg", "test");
                for (int i = 0; i < 10000; i++) {
                    serverSocket.request(msg);
                    // for (int j = 0; j < 10; j++) {
                    // serverSocket.heartBeat();
                    // System.out.println("send heartBeat");
                    // Thread.sleep(100);
                    // }
                }
                serverSocket.close();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("end time : " + endTime + " | run time :" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
