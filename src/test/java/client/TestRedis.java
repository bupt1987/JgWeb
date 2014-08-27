package client;

import redis.clients.jedis.Jedis;

public class TestRedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            jedis.set("n" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Simple SET: " + ((end - start) / 1000.0) + " seconds");
        for (int i = 0; i < 100000; i++) {
            jedis.del("n" + i);
        }
        jedis.disconnect();
    }

}
