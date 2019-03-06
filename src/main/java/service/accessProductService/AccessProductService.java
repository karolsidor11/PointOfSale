package service.accessProductService;

import model.Product;

import java.util.Optional;

public interface AccessProductService {

    Optional<Product> getProductById(String barcode);
}
