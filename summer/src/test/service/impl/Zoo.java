package test.service.impl;

import org.summer.ioc.annotation.IceCream;
import org.summer.ioc.annotation.Santa;

import test.service.IAnimal;
import test.service.IZoo;

@IceCream("zoo")
public class Zoo implements IZoo {

    @Santa
    private IAnimal monkey;

    @Santa
    private IAnimal tiger;

    public void meeting() {
        monkey.say();
        tiger.say();
    }

}
