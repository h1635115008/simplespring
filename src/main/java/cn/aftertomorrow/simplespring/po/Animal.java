package cn.aftertomorrow.simplespring.po;

/**
 * @author huangming
 * @className Animal
 * @description
 * @date 2019/3/12
 */
public class Animal {
    private String name;
    private String kind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Animal [name=" + name + ", kind=" + kind + "]";
    }

}
