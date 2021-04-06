package cn.aftertomorrow.simplespring.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aftertomorrow.simplespring.bean.Bean;
import cn.aftertomorrow.simplespring.bean.Property;
import cn.aftertomorrow.simplespring.config.ConfigReader;
import org.dom4j.DocumentException;


/**
 * @author huangming
 * @className ClassPathXmlApplicationContext
 * @description
 * @date 2019/3/12
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    /**
     * 实例化的bean集合
     */
    private Map<String, Object> beans = new HashMap<>();

    /**
     * 所有bean的配置信息
     */
    private Map<String, Bean> beanConfigs = new HashMap<>();

    public ClassPathXmlApplicationContext(String xmlPath) {
        super();
        try {
            beanConfigs = ConfigReader.readConfig(xmlPath);
            for (String name : beanConfigs.keySet()) {
                if (beanConfigs.get(name).getScope().equals("singleton")) {
                    beans.put(name, createBean(name));
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String name) {
        if (beanConfigs.get(name).getScope().equals("prototype")) {
            return createBean(name);
        } else {
            return beans.get(name);
        }
    }

    private Object createBean(String name) {
        Object bean = null;
        Field field = null;
        try {
            Bean beanConfig = beanConfigs.get(name);
            Class<?> clazz = Class.forName(beanConfig.getClazz());
            bean = clazz.newInstance();
            List<Property> properties = beanConfig.getProperties();
            for (Property property : properties) {
                field = clazz.getDeclaredField(property.getName());
                Class<?> fieldType = field.getType();
                Method m = clazz.getDeclaredMethod(getSetterName(property.getName()), fieldType);
                if (property.getRef() != null) {
                    m.invoke(bean, getBean(property.getRef()));
                }
                if (property.getValue() != null) {
                    m.invoke(bean, convertType(field.getType(), property.getValue()));
                }
            }
            beans.put(name, bean);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return bean;
    }

    private Object convertType(Class<?> className, String value) {
        Object obj = null;
        if (className.equals(String.class)) {
            obj = value;
        } else if (className.equals(int.class) || className.equals(Integer.class)) {
            obj = Integer.valueOf(value);
        } else if (className.equals(byte.class) || className.equals(Byte.class)) {
            obj = Byte.valueOf(value);
        } else if (className.equals(float.class) || className.equals(Float.class)) {
            obj = Float.valueOf(value);
        } else if (className.equals(char.class) || className.equals(Character.class)) {
            obj = Character.valueOf(value.charAt(0));
        } else if (className.equals(short.class) || className.equals(Short.class)) {
            obj = Short.valueOf(value);
        } else if (className.equals(long.class) || className.equals(Long.class)) {
            obj = Long.valueOf(value);
        } else if (className.equals(boolean.class) || className.equals(Boolean.class)) {
            obj = Boolean.valueOf(value);
        } else if (className.equals(double.class) || className.equals(Double.class)) {
            obj = Double.valueOf(value);
        }
        return obj;
    }

    private Map<String, Object> getBeans() {
        return beans;
    }

    private void setBeans(Map<String, Object> beans) {
        this.beans = beans;
    }

    private void setBeanConfigs(Map<String, Bean> beanConfigs) {
        this.beanConfigs = beanConfigs;
    }

    private Map<String, Bean> getBeanConfigs() {
        return beanConfigs;
    }

    private String getSetterName(String name) {
        return "set" + new StringBuffer(name).replace(0, 1, name.substring(0, 1).toUpperCase());
    }
}
