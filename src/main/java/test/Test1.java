package test;

import beans.BeansException;
import context.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Aservice aService = (Aservice) ctx.getBean("aservice");
        aService.sayHello();
    }
}