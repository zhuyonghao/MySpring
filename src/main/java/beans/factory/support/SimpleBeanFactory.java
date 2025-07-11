package beans.factory.support;

import beans.BeansException;
import beans.factory.BeanFactory;
import beans.factory.DefaultSingletonBeanRegistry;
import beans.factory.config.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 根据类名直接获取对象，如果没有获得，实例化对象，并将其放在singleton中
 * 注册BeanDefinition将名称和BeanDefinition放入集合之中
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{
    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);
    public SimpleBeanFactory() {
    }

    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException {
        //先尝试直接拿bean实例
        Object singleton = this.getSingleton(beanName);
        //如果此时还没有这个bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            //获取bean的定义
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("No bean.");
            }
            try {
                singleton = Class.forName(beanDefinition.getClassName()).
                        getDeclaredConstructor().
                        newInstance();
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            //新注册这个bean实例
            this.registerSingleton(beanName, singleton);
        }
        return singleton;
    }
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }
}
