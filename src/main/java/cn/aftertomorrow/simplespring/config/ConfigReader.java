package cn.aftertomorrow.simplespring.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.aftertomorrow.simplespring.bean.Bean;
import cn.aftertomorrow.simplespring.bean.Property;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @author huangming
 * @className ConfigReader
 * @description
 * @date 2019/3/12
 */
public class ConfigReader {
    public static Map<String, Bean> readConfig(String xmlPath) throws DocumentException {
        Map<String, Bean> beanConfigs = new HashMap<>(16);
        SAXReader reader = new SAXReader();
        Document document = reader.read(ConfigReader.class.getClassLoader().getResourceAsStream(xmlPath));
        Element root = document.getRootElement();
        Iterator<Element> parents = root.elementIterator();
        // 第一级
        while (parents.hasNext()) {
            Bean bean = new Bean();
            Element parent = parents.next();
            bean.setId(parent.attributeValue("id"));
            bean.setClazz(parent.attributeValue("class"));
            bean.setScope(parent.attributeValue("scope"));
            Iterator<Element> children = parent.elementIterator();
            List<Property> properties = bean.getProperties();
            // 第二级
            while (children.hasNext()) {
                Element child = children.next();
                Property property = new Property();
                property.setName(child.attributeValue("name"));
                property.setRef(child.attributeValue("ref"));
                property.setValue(child.attributeValue("value"));
                properties.add(property);
            }
            beanConfigs.put(bean.getId(), bean);
        }
        return beanConfigs;
    }
}
