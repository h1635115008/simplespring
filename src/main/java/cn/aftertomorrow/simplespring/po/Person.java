package cn.aftertomorrow.simplespring.po;

/**
 * @author huangming
 * @className Person
 * @description
 * @date 2019/3/12
 */
public class Person {
    private String name;
    private String age;
    private String sex;
    private Phone phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", sex=" + sex + ", phone=" + phone + "]";
    }

}
