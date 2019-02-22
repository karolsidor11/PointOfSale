package repository;

import model.Product;

import java.util.List;

public interface ProductRepository {

    Product getProductByBarCode(String barcode);

    List<Product> getAllProducts();
}
