package cha.pao.fan.blogs.utils;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

public class GroovyTest {
    public static void main(String args[])
    {
        try {
            GroovyClassLoader loader = new GroovyClassLoader();
            loader.addClasspath("");
            Class fileCreator = loader.parseClass("def cal(){println (\"asdfadsf\")}");
            GroovyObject object = (GroovyObject) fileCreator.newInstance();
            System.out.println(object.invokeMethod("cal",null));
            System.out.println(System.getProperty("java.class.path"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
