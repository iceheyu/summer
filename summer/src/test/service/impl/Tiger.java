package test.service.impl;

import org.summer.ioc.annotation.IceCream;
import org.summer.ioc.annotation.Santa;

import test.service.IAnimal;
import static org.summer.utils.Print.print;

@IceCream("tiger")
public class Tiger implements IAnimal {

    private String name = "tiger";

    @Santa
    private IAnimal monkey;

    public void say() {
        print("I am " + name);
        print("I like " + monkey.getName());
    }

    public String getName() {
        return name;
    }

}
