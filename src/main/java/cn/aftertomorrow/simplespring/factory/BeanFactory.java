package cn.aftertomorrow.simplespring.factory;

/**
 * @author huangming
 * @className BeanFactory
 * @description
 * @date 2019/3/12
 */
public interface BeanFactory {

    /**
     * 获取bean对象
     *
     * @param name
     * @return
     */
    public Object getBean(String name);
}
