package com.free.framework.mode.factory.simple;

/**
 * com.free.framework.mode.factory.simple.Test
 *
 * @author lipeng
 * @dateTime 2017/12/21 21:30
 */
public class Test {

    public static void main(String[] args) throws Exception {
        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.createCar("BaoMa");
        simpleFactory.createCar("BenChi");
        simpleFactory.createCar("AuDi");
    }
}
