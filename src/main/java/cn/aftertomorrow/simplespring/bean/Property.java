package cn.aftertomorrow.simplespring.bean;

/**
 * @author huangming
 * @className Property
 * @description
 * @date 2019/3/12
 */
public class Property {
    private String name;
    private String value;
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property [name=" + name + ", value=" + value + ", ref=" + ref + "]";
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}
