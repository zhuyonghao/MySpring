package beans.factory;

import beans.factory.config.BeanDefinition;
import beans.BeansException;

/**
 * 获取类名对应的实例化对象
 * 注册BeanDefinition
 */
public interface BeanFactory {
    Object getBean(String BeanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
