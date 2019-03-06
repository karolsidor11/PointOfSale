package service.accessProductService;

import model.Product;
import repository.ProductRepository;
import service.ioService.IOService;

import java.util.Optional;

public class AccessProductServiceImpl implements AccessProductService {

    private IOService ioService;
    private ProductRepository productRepository;

    public AccessProductServiceImpl(IOService ioService, ProductRepository productRepository) {
        this.ioService = ioService;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(String barcode) {

        return getProduct(barcode);
    }

    private Optional<Product> getProduct(String barcode) {
        if (barcode.equals("")) {
            ioService.printOnLcd("Invalid bar-code");
            return Optional.empty();
        } else {
            return Optional.ofNullable(productRepository.getProductByBarCode(barcode));
        }
    }
}