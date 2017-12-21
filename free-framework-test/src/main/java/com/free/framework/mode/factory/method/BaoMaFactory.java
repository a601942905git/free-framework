package com.free.framework.mode.factory.method;


import com.free.framework.mode.factory.BaoMaCar;
import com.free.framework.mode.factory.Car;

/**
 * com.free.framework.mode.factory.method.BaoMaFactory
 *
 * @author lipeng
 * @dateTime 2017/12/21 21:33
 */
public class BaoMaFactory implements CarFactory{
    @Override
    public Car createCar() {
        return new BaoMaCar();
    }
}
