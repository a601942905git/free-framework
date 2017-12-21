package com.free.framework.mode.factory.method;

import com.free.framework.mode.factory.AuDiCar;
import com.free.framework.mode.factory.Car;

/**
 * com.free.framework.mode.factory.method.AuDiFactory
 *
 * @author lipeng
 * @dateTime 2017/12/21 21:36
 */
public class AuDiFactory implements CarFactory{

    @Override
    public Car createCar() {
        return new AuDiCar();
    }
}
