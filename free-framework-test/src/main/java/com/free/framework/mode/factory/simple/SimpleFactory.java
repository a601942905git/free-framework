package com.free.framework.mode.factory.simple;

import com.free.framework.mode.factory.AuDiCar;
import com.free.framework.mode.factory.BaoMaCar;
import com.free.framework.mode.factory.BenChiCar;
import com.free.framework.mode.factory.Car;

import java.util.Objects;

/**
 * com.free.framework.mode.factory.simple.SimpleFactory
 *
 * @author lipeng
 * @dateTime 2017/12/21 21:20
 */
public class SimpleFactory {

    /**
     * 简单工厂
     * 缺点就是如果需要造其它类型的汽车,就需要修改代码,违背了对修改关闭,对扩展开放
     * @param type
     * @return
     * @throws Exception
     */
    public Car createCar(String type) throws Exception {
        Car car;
        if (Objects.equals("BaoMa", type)) {
            car = new BaoMaCar();
        } else if (Objects.equals("BenChi", type)) {
            car = new BenChiCar();
        } else if (Objects.equals("AuDi", type)) {
            car = new AuDiCar();
        } else {
            throw new Exception("无车可造");
        }
        return car;
    }
}
