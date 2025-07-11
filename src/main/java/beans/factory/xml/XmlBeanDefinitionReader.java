package beans.factory.xml;

import beans.factory.BeanFactory;
import beans.factory.config.BeanDefinition;
import beans.factory.support.SimpleBeanFactory;
import core.Resource;
import org.dom4j.Element;

/**
 * 读取XML文件信息获取BeanDefinition对象
 * 并在BeanFactory中注册
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory;
    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
