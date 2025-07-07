package beans.factory.xml;

import beans.factory.BeanFactory;
import beans.factory.config.BeanDefinition;
import core.Resource;
import org.dom4j.Element;

/**
 * 读取XML文件信息获取BeanDefinition对象
 * 并在BeanFactory中注册
 */
public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}