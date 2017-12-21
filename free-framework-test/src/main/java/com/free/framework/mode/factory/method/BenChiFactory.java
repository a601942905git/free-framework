package com.free.framework.mode.factory.method;

import com.free.framework.mode.factory.BenChiCar;
import com.free.framework.mode.factory.Car;

/**
 * com.free.framework.mode.factory.method.BenChiFactory
 *
 * @author lipeng
 * @dateTime 2017/12/21 21:36
 */
public class BenChiFactory implements CarFactory{

    @Override
    public Car createCar() {
        return new BenChiCar();
    }
}
