package javaproxies.app;

import javaproxies.app.dynamicjdk.ISubject;

public class Math implements ISubject {

    public String name;

    public Math() {
    }

    public Math(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
