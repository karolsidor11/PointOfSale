package service.productService;

import enums.IOMessage;
import service.accessProductService.AccessProductService;
import service.ioService.IOService;
import service.orderService.OrderService;
import service.predicateService.ProductPredicate;

public class ProductServiceImpl implements ProductService {

    private OrderService orderService;
    private ProductPredicate productPredicate;
    private IOService ioService;
    private AccessProductService accessProductService;

    public ProductServiceImpl(ProductPredicate productPredicate, OrderService orderService,
                              IOService ioService, AccessProductService accessProductService) {
        this.productPredicate = productPredicate;
        this.orderService = orderService;
        this.ioService = ioService;
        this.accessProductService = accessProductService;
    }

    @Override
    public String saleProduct(String barcode) {

        return getSale(barcode);
    }

    private String getSale(String barcode) {
        if (barcode.equals(IOMessage.EXIT.toString())) {
            ioService.printOnPrinter(orderService.showOrderItem() + " " + orderService.showPriceOrder());
            return ioService.printOnLcd(IOMessage.YOUR_ORDER.toString() + orderService.showOrderItem() + orderService.showPriceOrder());
        } else {
            return productPredicate.checkProduct(barcode);

        }
    }
}