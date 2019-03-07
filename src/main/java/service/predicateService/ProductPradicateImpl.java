package service.predicateService;

import enums.IOMessage;
import model.Product;
import service.accessProductService.AccessProductService;
import service.ioService.IOService;
import service.orderService.OrderService;

import java.util.Optional;

public class ProductPradicateImpl implements ProductPredicate {

    private AccessProductService AccessProductService;
    private IOService ioService;
    private OrderService orderService;
    private IOMessage ioMessage;

    public ProductPradicateImpl(AccessProductService accessProductService, IOService ioService, OrderService orderService) {
        this.AccessProductService = accessProductService;
        this.orderService = orderService;
        this.ioService = ioService;
    }

    @Override
    public String checkProduct(String barcode) {

        Optional<Product> productByID = AccessProductService.getProductById(barcode);

        return getProduct(productByID);
    }

    private String getProduct(Optional<Product> productByID) {
        if (productByID.isPresent()) {
            orderService.add(productByID.get());
            return ioService.printOnLcd(productByID.get().getName() + " " + productByID.get().getPrice());
        } else {
            return ioService.printOnLcd(IOMessage.PRODUCT_NOT_FOUND.toString());
        }
    }
}
