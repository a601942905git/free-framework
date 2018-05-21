## 多线程同步
### 1、join()方法
> 使得调用该方法的线程在此之前执行完毕，并且阻塞当前执行线程。
假如我们在main()方法中开启一个耗时执行的线程，可能我们的耗时执行线程还没有
执行完毕,main()方法的主线程已经退出。
如果我们希望主线程在耗时线程执行完毕之后再继续执行，那么我们可以调用耗时线程
的join()方法
```
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
```
### 2、 CountDownLatch 计数器
> 首先定义一个计数器，在需要等待的线程中调用countDown()方法，在执行线程中
调用await()方法进行阻塞
```
public class CountDownLatchTest {

    public static final Integer COUNT_DOWN_LATCH_NUMBER = 5;

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(COUNT_DOWN_LATCH_NUMBER);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < COUNT_DOWN_LATCH_NUMBER; i++) {
            new Thread(new Player((i + 1), COUNT_DOWN_LATCH)).start();
        }
        COUNT_DOWN_LATCH.await();
        System.out.println("进入游戏");

          // 用于测试CountDownLatch不可重复使用
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

```
### 3、 CyclicBarrier 
> 用于一组线程中阻塞，直到指定数量的线程都到达栅栏，
那么阻塞的线程可以继续执行
```
public class CyclicBarrierTest {

    public static final Integer PARTIES = 5;


    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTIES);
        for (int i = 0; i < PARTIES; i++) {
            new Thread(new Player((i + 1), cyclicBarrier)).start();
        }
    }

    public static void test2() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTIES, () -> {
            System.out.println("所有玩家已准备完毕,进入游戏！");
        });
        for (int i = 0; i < PARTIES; i++) {
            new Thread(new Player((i + 1), cyclicBarrier)).start();
        }

          // 用于测试CyclicBarrier可以重复使用
//        for (int i = 0; i < PARTIES; i++) {
//            new Thread(new Player1((i + 1), cyclicBarrier)).start();
//        }
    }

    static class Player implements Runnable{
        private Integer number;

        private CyclicBarrier cyclicBarrier;

        Player(Integer number, CyclicBarrier cyclicBarrier) {
            this.number = number;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(number + "号玩家已经准备好，等待其它玩家准备");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(number + "号玩家进入游戏！");
        }
    }

    static class Player1 implements Runnable{
        private Integer number;

        private CyclicBarrier cyclicBarrier;

        Player1(Integer number, CyclicBarrier cyclicBarrier) {
            this.number = number;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(number + "号测试玩家已经准备好，等待其它玩家准备");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(number + "号测试玩家进入游戏！");
        }
    }
}

```
### 4、Semaphore信号量
> 相当于锁一样，对一组共享资源进行控制
```
public class SemaphoreTest {

    public static void main(String[] args) {
        // 8个工人
        Integer workerNumber = 8;
        // 5台机器
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < workerNumber; i++) {
            new Thread(new Worker((i + 1), semaphore)).start();
        }
    }

    static class Worker implements Runnable{

        private Integer number;

        private Semaphore semaphore;

        Worker(Integer number, Semaphore semaphore) {
            this.number = number;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(number + "号工人占用一台机器");
                // 模拟工人使用机器的时间
                Thread.sleep(number * 1000);
                semaphore.release();
                System.out.println(number + "号工人释放一台机器");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

```