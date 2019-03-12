package cn.aftertomorrow.main;

import java.lang.reflect.Field;
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

	public ClassPathXmlApplicationContext() {
		super();
		// TODO Auto-generated constructor stub

	}

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

	public Object createBean(String name) {
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
				System.out.println(property.getName());
				field.setAccessible(true);
				if (property.getRef() != null) {
					field.set(bean, getBean(property.getRef()));
				}
				if (property.getValue() != null) {
					field.set(bean, convertType(field.getType(), property.getValue()));
				}
			}
			beans.put(name, bean);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public Object convertType(Class<?> className, String value) {
		Object obj = null;
		if (className.equals(int.class) || className.equals(Integer.class)) {
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
		} else if (className.equals(String.class)) {
			obj = value;
		}
		/*
		 * else { try {
		 * className.getConstructor(String.class).newInstance(value); } catch
		 * (InstantiationException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (IllegalArgumentException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (NoSuchMethodException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SecurityException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		System.out.println(obj);
		return obj;
	}

	public Map<String, Object> getBeans() {
		return beans;
	}

	public void setBeans(Map<String, Object> beans) {
		this.beans = beans;
	}

	public void setBeanConfigs(Map<String, Bean> beanConfigs) {
		this.beanConfigs = beanConfigs;
	}

	public Map<String, Bean> getBeanConfigs() {
		return beanConfigs;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, InstantiationException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"cn/aftertomorrow/config/applicationContext.xml");
		/*
		 * Bean b bean = new Bean(); bean.setClazz("cn.aftertomorrow.po.Phone");
		 * bean.setId("phone"); Property property1 = new Property();
		 * property1.setName("brand"); property1.setValue("meizu");
		 * bean.getProperties().add(property1); Property property2 = new
		 * Property(); property2.setName("model");
		 * property2.setValue("pro6plus"); bean.getProperties().add(property2);
		 * Property property3 = new Property(); property3.setName("price");
		 * property3.setValue("2999"); bean.getProperties().add(property3);
		 * System.out.println(bean); context.getBeanConfigs().put("phone",
		 * bean);
		 */
		System.out.println(context.getBean("person"));
	}

}
