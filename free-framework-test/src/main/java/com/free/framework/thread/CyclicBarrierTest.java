package com.free.framework.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * com.free.framework.thread.CyclicBarrierTest
 *
 * @author lipeng
 * @dateTime 2018/5/14 上午10:46
 */
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
