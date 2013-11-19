package client;

import java.util.HashMap;

import com.zhaidaosi.game.jgframework.common.http.BaseHttp;

public class TestRegist {

	public static void main(String[] args) throws Exception {
		for (int i = 1001; i <= 10000; i++) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("username", "test"+i);
			params.put("password", "123456");
			params.put("nickname", "测试"+i+"号");
			BaseHttp.post("http://127.0.0.1:18080/regist", params);
			Thread.sleep(10);
		}
		
	}
	
}
