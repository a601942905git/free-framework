package com.free.framework.mode.factory.abstractfactory;

import com.free.framework.mode.factory.Car;

/**
 * com.free.framework.mode.factory.abstractfactory.BenChiSUVFactory
 *
 * @author lipeng
 * @dateTime 2017/12/21 22:08
 */
public class BenChiSUVFactory implements SUVFactory{

    @Override
    public Car createCar() {
        return new BenChiSUVCar();
    }
}
