import com.free.framework.Application;
import com.free.framework.plateform.async.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * PACKAGE_NAME.TestAsync
 *
 * @author lipeng
 * @dateTime 2017/12/16 11:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestAsync {

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        System.out.println("执行开始");
        asyncTask.executeAsyncTask1();
        Future<String> future = asyncTask.executeAsyncTask2("测试");
        System.out.println(future.get());
        System.out.println("执行结束");
        Thread.sleep(5000);
    }
}
