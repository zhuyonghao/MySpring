package context;

import beans.*;
import beans.factory.BeanFactory;
import beans.factory.config.BeanDefinition;
import beans.factory.support.SimpleBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import core.ClassPathXmlResource;
import core.Resource;

/**
 * 实现接口并使用组合实现多态
 * 调用ClassPathXmlResource获取URL
 * 调用XmlBeanDefinitionReader加载资源并注册BeanDefinition
 * beanFactory使用SimpleBeanFactory进行初始化
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    BeanFactory beanFactory;
    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }
    //context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }
}
