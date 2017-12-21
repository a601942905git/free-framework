package com.free.framework.mode.factory.method;

import com.free.framework.mode.factory.Car;

/**
 * com.free.framework.mode.factory.method.CarFactory
 *
 * @author lipeng
 * @dateTime 2017/12/21 21:31
 */
public interface CarFactory {

    /**
     * 生产汽车
     * @return
     */
    Car createCar();
}
