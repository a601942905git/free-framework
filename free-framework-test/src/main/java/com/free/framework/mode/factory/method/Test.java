package com.free.framework.mode.factory.method;

/**
 * com.free.framework.mode.factory.method.Test
 *
 * @author lipeng
 * @dateTime 2017/12/21 21:37
 */
public class Test {

    /**
     * 工厂方法
     * 1.定义创建对象的接口
     * 2.将对象的实例化放到子类中
     * @param args
     */
    public static void main(String[] args) {
        CarFactory carFactory = new BaoMaFactory();
        carFactory.createCar();

        carFactory = new BenChiFactory();
        carFactory.createCar();

        carFactory = new AuDiFactory();
        carFactory.createCar();
    }
}
