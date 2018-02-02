package com.java.limit;

import com.free.framework.Application;
import com.free.framework.util.http.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * com.java.limit.TestLimit
 *
 * @author lipeng
 * @dateTime 2018/1/30 15:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestLimit {

    @Test
    public void sendRequest() {
        for (int i = 0; i < 150; i++) {
            new Thread(() -> System.out.println("===>" + HttpUtils.get("http://127.0.0.1:315/"))).start();
        }
    }
}
