import enums.IOMessage;
import model.Product;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;
import service.accessProductService.AccessProductService;
import service.accessProductService.AccessProductServiceImpl;
import service.ioService.IOService;
import service.ioService.IOServiceImpl;
import service.orderService.OrderService;
import service.orderService.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PointOfSale {

    public static void main(String[] args) {

        IOService ioService = new IOServiceImpl();
        ProductRepository productRepository = new ProductRepositoryImpl();

        AccessProductService productService = new AccessProductServiceImpl(ioService,productRepository);

        Optional<Product> xdf123 = productService.getProductById("XDF123");

        System.out.println(xdf123.get().getName());


        List<Product> products = new ArrayList<>();

        OrderService orderService = new OrderServiceImpl(products);

        orderService.add(new Product("Apple", new BigDecimal(12), "XDF123"));
        orderService.add(new Product("Applea", new BigDecimal(15), "XDF123"));

        String s = orderService.showOrderItem();
        System.out.println(s);

        System.out.println(orderService.showPriceOrder());

        System.out.println(IOMessage.PRODUCT_NOT_FOUND.toString());

    }

}
