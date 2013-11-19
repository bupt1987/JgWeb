package client;

import com.zhaidaosi.game.server.sdm.model.User;

public class TestJson {

	public static void main(String[] args) {
		User user = new User("test", "123456", 1L, 1L);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			System.out.println(user);
//			System.out.println(BaseJson.ObjectToJson(user));
		}
		System.out.println(System.currentTimeMillis() - startTime);
	}

}
