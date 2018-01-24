package com.java.redis;

import com.free.framework.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * com.java.redis.TestRedis
 *
 * @author lipeng
 * @dateTime 2018/1/24 22:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("cache", "redis");
        System.out.println(valueOperations.get("cache"));

        ValueOperations<String, Person> personValueOperations = redisTemplate.opsForValue();
        Person person = Person.builder()
                .id(10001)
                .name("测试小黄人")
                .age(22)
                .build();
        personValueOperations.set("person:10001", person);
        person = personValueOperations.get("person:10001");
        System.out.println(person.getId() + "===>" + person.getName() + "===>" + person.getAge());
    }
}
