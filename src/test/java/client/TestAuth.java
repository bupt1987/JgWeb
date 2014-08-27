package client;

import com.zhaidaosi.game.jgframework.common.http.BaseHttp;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import model.AuthResult;

import java.util.HashMap;

public class TestAuth {

    public static AuthResult auth(String username, String password) throws Exception {
        String address, sercret;
        AuthResult re = null;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("password", password);
        String res = BaseHttp.post("http://127.0.0.1:18080/login", params);
        OutMessage om = OutMessage.getMessage(res);
        if (om.getCode() == 0) {
            address = (String) om.getResultValue("address");
            sercret = (String) om.getResultValue("sercret");
            re = new AuthResult(address, sercret);
        } else {
            return re;
        }
        return re;
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        for (int i = 0; i < 100; i++) {
            AuthThread t = new AuthThread("test" + i, "123456");
            t.start();
            Thread.sleep(100);
        }
    }

}

class AuthThread extends Thread {

    String username;
    String password;

    public AuthThread(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("password", password);
        for (int i = 0; i < 10000; i++) {
            try {
                BaseHttp.post("http://127.0.0.1:18080/login", params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time : " + endTime + " | run time :" + (endTime - startTime));
    }

}
