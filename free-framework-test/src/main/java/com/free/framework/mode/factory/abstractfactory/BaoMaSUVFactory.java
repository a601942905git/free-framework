package com.free.framework.mode.factory.abstractfactory;

import com.free.framework.mode.factory.Car;

/**
 * com.free.framework.mode.factory.abstractfactory.BaoMaSUVFactory
 *
 * @author lipeng
 * @dateTime 2017/12/21 22:05
 */
public class BaoMaSUVFactory implements SUVFactory{

    @Override
    public Car createCar() {
        return new BaoMaSUVCar();
    }
}
