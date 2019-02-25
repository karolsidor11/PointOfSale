package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {

    private String name;
    private BigDecimal price;
    private String barcode;

    public Product() {
    }

    public Product(String name, BigDecimal price, String barcode) {
        this.name = name;
        this.price = price;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(barcode, product.barcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price, barcode);
    }

    @Override
    public String toString() {
        return "Product{" + "name='" + name + '\'' + ", price=" + price + ", barcode='" + barcode + '\'' + '}';
    }
}
