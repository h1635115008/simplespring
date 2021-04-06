package cn.aftertomorrow.simplespring;

import cn.aftertomorrow.simplespring.factory.ClassPathXmlApplicationContext;

/**
 * @author huangming
 * @className Main
 * @description
 * @date 2021/4/6
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException, InstantiationException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        System.out.println(context.getBean("phone") == context.getBean("phone"));
        System.out.println(context.getBean("animal"));
        System.out.println(context.getBean("animal") == context.getBean("animal"));
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
