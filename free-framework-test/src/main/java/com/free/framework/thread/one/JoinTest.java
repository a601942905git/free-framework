/**
 * com.free.framework.thread.JoinTest
 *
 * @author lipeng
 * @dateTime 2018/5/8 上午11:23
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = null;
        for (int i = 0; i < 10; i++) {
            thread = new Thread(new Player((i + 1) + "号玩家"));
            thread.start();
            thread.join();
        }
        System.out.println("进入游戏");
    }

    static class Player implements Runnable {

        private String name;

        Player(String name) {
            this.name = name;
        }

        @Override

        public void run() {
            System.out.println(name + "已经加载完毕，等待其它玩家加载！");
        }
    }
}
