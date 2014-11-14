package test.service.impl;

import org.summer.ioc.annotation.IceCream;
import test.service.IAnimal;
import static org.summer.utils.Print.print;

@IceCream("monkey")
public class Monkey implements IAnimal {

    private String name = "monkey";

    public void say() {
        print("I am " + name);
    }

    public String getName() {
        return name;
    }

}
