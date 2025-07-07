package beans.factory;

import beans.factory.config.BeanDefinition;
import beans.BeansException;

public interface BeanFactory {
    Object getBean(String BeanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
