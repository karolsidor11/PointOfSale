package service.accessProductService;

import model.Product;

import java.util.Optional;

public interface accessProductService {

    Optional<Product> getProductById(String barcode);
}
