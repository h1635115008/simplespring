package cn.aftertomorrow.simplespring.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangming
 * @className Bean
 * @description
 * @date 2019/3/12
 */
public class Bean {
    private String id;
    private String clazz;
    private String scope;
    private List<Property> properties = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Bean [id=" + id + ", clazz=" + clazz + ", scope=" + scope + ", properties=" + properties + "]";
    }

}
