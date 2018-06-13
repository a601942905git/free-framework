import java.util.concurrent.CountDownLatch;

/**
 * com.free.framework.thread.CountDownLatchTest
 *
 * @author lipeng
 * @dateTime 2018/5/8 下午2:08
 */
public class CountDownLatchTest {

    public static final Integer COUNT_DOWN_LATCH_NUMBER = 5;

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(COUNT_DOWN_LATCH_NUMBER);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < COUNT_DOWN_LATCH_NUMBER; i++) {
            new Thread(new Player((i + 1), COUNT_DOWN_LATCH)).start();
        }
        COUNT_DOWN_LATCH.await();
        System.out.println("进入游戏");

//        for (int i = 0; i < COUNT_DOWN_LATCH_NUMBER; i++) {
//            new Thread(new Player1((i + 1), COUNT_DOWN_LATCH)).start();
//        }
//        COUNT_DOWN_LATCH.await();
//        System.out.println("测试进入游戏");
    }

    static class Player implements Runnable{
        private Integer number;

        private CountDownLatch countDownLatch;

        Player(Integer number, CountDownLatch countDownLatch) {
            this.number = number;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(number + "号玩家已经加载完毕，等待其它玩家加载！");
            countDownLatch.countDown();
        }
    }

    static class Player1 implements Runnable{
        private Integer number;

        private CountDownLatch countDownLatch;

        Player1(Integer number, CountDownLatch countDownLatch) {
            this.number = number;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(number + "号玩家已经加载完毕，等待其它玩家加载！");
            countDownLatch.countDown();
        }
    }
}
