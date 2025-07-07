import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * 解析XML文件获取Bean的配置信息
 * 见名知意：获取类路径下的XML文件来构建应用上下文
 */
public class ClassPathXmlApplicationContext {

    // 直接初始化保证对象对象在创建之后立即处于可用状态，避免空指针
    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    // 存放单例模式的Bean实例
    private final Map<String, BeanDefinition> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String filename){
        this.readXml(filename);
        this.instanceBeans();
    }

    private void readXml(String filename) {
        SAXReader saxReader = new SAXReader();

        try{
            // 获取URL
            URL xmlPath = this.getClass().getClassLoader().getResource(filename);
            // 根据URL获取文件信息
            Document document = saxReader.read(xmlPath);
            // 解析文件获取根元素
            Element rootElement = document.getRootElement();

            // 遍历每一个根元素获取Bean定义信息,将其存放在beanDefinitions中
            for (Element element : rootElement.elements()) {
                String beanID = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");

                BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
                beanDefinitions.add(beanDefinition);
            }

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 利用反射创建Bean实例，并存储在singleton中
     */
    private void instanceBeans(){
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                singletons.put(beanDefinition.getId(), (BeanDefinition) Class.forName(beanDefinition.getClassName()).
                        getDeclaredConstructor().
                        newInstance());
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 根据类名返回对象
     */
    public Object getBean(String beanName){
        return singletons.get(beanName);
    }
}
