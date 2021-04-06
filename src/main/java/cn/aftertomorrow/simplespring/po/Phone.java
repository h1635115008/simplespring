package cn.aftertomorrow.simplespring.po;

/**
 * @author huangming
 * @className Phone
 * @description
 * @date 2019/3/12
 */
public class Phone {
    private String brand;
    private String model;
    private Integer price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone [brand=" + brand + ", model=" + model + ", price=" + price + "]";
    }

}
