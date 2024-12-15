package javaproxies.app.cglib;

import javaproxies.app.Math;
import net.sf.cglib.proxy.Enhancer;

public class CgLibProxyApp {

    public static void main(String[] args) {

        Math proxy = (Math) Enhancer.create(Math.class, new CgLibProxyHandler());
        proxy.setName("algebra");
        System.out.println(proxy.getName());

    }
}
