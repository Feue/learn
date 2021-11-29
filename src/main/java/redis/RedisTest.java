package redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Feue
 * @create 2021-09-15 10:52
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        System.out.println("服务正在运行: "+jedis.ping());

        jedis.flushDB();
        // 存储数据到列表中
        jedis.lpush("list", "Feue");
        jedis.lpush("list", "Feue666");
        jedis.lpush("list", "Loser");

        List<String> list = jedis.lrange("list", 0, -1);
        for (String s: list) {
            System.out.println("列表项为: "+s);
        }

        Set<String> set = jedis.keys("*");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }


    }
}
