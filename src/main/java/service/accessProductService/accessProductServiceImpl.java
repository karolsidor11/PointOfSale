package service.accessProductService;

import model.Product;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;
import service.ioService.IOService;
import service.ioService.IOServiceImpl;

import java.util.Optional;

public class accessProductServiceImpl implements accessProductService {

    private IOService ioService;
    private ProductRepository productRepository;

    public accessProductServiceImpl() {
        ioService = new IOServiceImpl();
        productRepository = new ProductRepositoryImpl();
    }

    @Override
    public Optional<Product> getProductById(String barcode) {

        if (barcode.isEmpty()) {
            ioService.printOnLcd("Invalid bar-code");
            return Optional.empty();
        } else {
            return Optional.ofNullable(productRepository.getProductByBarCode(barcode));
        }
    }
}
