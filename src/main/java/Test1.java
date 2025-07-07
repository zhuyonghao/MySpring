public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Aservice aService = (Aservice) ctx.getBean("aservice");
        aService.sayHello();
    }
}