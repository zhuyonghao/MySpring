package beans.factory.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用来存放解析后类的信息
 * id：唯一标识
 * className：要实例化类的名称
 */
@AllArgsConstructor
@Data
public class BeanDefinition {
    private String id;
    private String className;
}
