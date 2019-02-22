package repository;

import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> productList = new ArrayList<>();

    public ProductRepositoryImpl() {

        productList.add(new Product("Bread",  new BigDecimal(2), "XDF123"));
        productList.add(new Product("Butter",  new BigDecimal(4), "XDY321"));
        productList.add(new Product("Apple",  new BigDecimal(2), "XDA234"));
        productList.add(new Product("Snickers",  new BigDecimal(3), "XDC456"));
    }

    @Override
    public Product getProductByBarCode(String barcode) {
        return productList.stream().filter(product -> product.getBarcode().equals(barcode)).findFirst().get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }
}
