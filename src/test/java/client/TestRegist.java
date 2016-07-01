package client;

import com.zhaidaosi.game.jgframework.common.http.BaseHttp;

import java.util.HashMap;

public class TestRegist {

    public static void main(String[] args) throws Exception {
        for (int i = 10000; i <= 11000; i++) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("username", "test" + i);
            params.put("password", "123456");
            params.put("nickname", "测试" + i + "号");
            System.out.println(BaseHttp.post("http://127.0.0.1:18080/regist", params));
            Thread.sleep(10);
        }

    }

}
