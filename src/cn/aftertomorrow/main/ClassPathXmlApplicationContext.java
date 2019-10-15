package cn.aftertomorrow.main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import cn.aftertomorrow.bean.Bean;
import cn.aftertomorrow.bean.Property;
import cn.aftertomorrow.config.ConfigReader;

public class ClassPathXmlApplicationContext implements BeanFactory {
	// 实例化的bean集合
	private Map<String, Object> beans = new HashMap<>();
	// 所有bean的配置信息
	private Map<String, Bean> beanConfigs = new HashMap<>();

	public ClassPathXmlApplicationContext(String xmlPath) {
		super();
		// TODO Auto-generated constructor stub
		try {
			beanConfigs = ConfigReader.readConfig(xmlPath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object getBean(String name) {
		// TODO Auto-generated method stub
		Object bean = null;
		if (beans.get(name) == null) {
			bean = createBean(name);
		} else {
			bean = beans.get(name);
		}
		return bean;
	}

	private Object createBean(String name) {
		Object bean = null;
		Field field = null;
		try {
			Bean beanConfig = beanConfigs.get(name);
			System.out.println(beanConfig.getClazz());
			Class<?> clazz = Class.forName(beanConfig.getClazz());
			bean = clazz.newInstance();
			List<Property> properties = beanConfig.getProperties();
			for (Property property : properties) {
				field = clazz.getDeclaredField(property.getName());
				Class<?> fieldType = field.getType();
				System.out.println(property.getName());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
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
		System.out.println(obj);
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

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, InstantiationException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"cn/aftertomorrow/config/applicationContext.xml");
		System.out.println(context.getBean("phone"));
		System.out.println(context.getBean("animal"));
		// try {
		// Method m = String.class.getDeclaredMethod("equals", String.class);
		// System.out.println(m.invoke(new String("hello"), "hello"));
		// } catch (NoSuchMethodException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(context.getBean("person"));
		// int i1 = 0b10000000000000000000000000000000;
		// int i2 = 0b10000000000000000000000000000001;
		// 10000000000000000000000000000001
		// 11111111111111111111111111111111
		// System.out.println(i1);
		// System.out.println(i2);
		// System.out.println(Integer.toBinaryString(-2147483648));
	}
	// 原00000001
	// 反00000001
	// 补00000001
	// 原10000011
	// 反11111100
	// 补11111101
	// 补码相加11111110
	// 补码的反码10000001
	// 补码的补码10000010
}
