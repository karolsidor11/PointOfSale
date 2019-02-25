package service.predicateService;

import model.Product;
import service.ioService.IOService;
import service.ioService.IOServiceImpl;
import service.orderService.OrderService;
import service.orderService.OrderServiceImpl;
import service.accessProductService.accessProductService;
import service.accessProductService.accessProductServiceImpl;

import java.util.Optional;

public class ProductPradicateImpl implements ProductPredicate {

    private accessProductService accessProductService;
    private IOService ioService;
    private OrderService orderService;

    public ProductPradicateImpl() {
        accessProductService = new accessProductServiceImpl();
        ioService = new IOServiceImpl();
        orderService = new OrderServiceImpl();
    }

    @Override
    public String checkProduct(String barcode) {

        Optional<Product> productByID = accessProductService.getProductById(barcode);

        if (productByID.isPresent()) {
            orderService.add(productByID.get());
            return ioService.printOnLcd(productByID.get().getName() + " " + productByID.get().getPrice());
        } else {
            return ioService.printOnLcd("Product not found");
        }
    }
}
