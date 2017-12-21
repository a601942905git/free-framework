package com.free.framework.mode.factory.abstractfactory;

/**
 * com.free.framework.mode.factory.abstractfactory.Test
 *
 * @author lipeng
 * @dateTime 2017/12/21 22:09
 */
public class Test {

    public static void main(String[] args) {
        CarFactory carFactory = new BaoMaSUVFactory();
        carFactory.createCar();

        carFactory = new BenChiSUVFactory();
        carFactory.createCar();
    }
}
