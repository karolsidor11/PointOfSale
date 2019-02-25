package service;

import service.ioService.IOService;
import service.ioService.IOServiceImpl;
import service.orderService.OrderService;
import service.orderService.OrderServiceImpl;
import service.predicateService.ProductPradicateImpl;
import service.predicateService.ProductPredicate;

public class ProductServiceImpl implements ProductService {

    private OrderService orderService;
    private ProductPredicate productPredicate;
    private IOService ioService;

    public ProductServiceImpl() {
        orderService = new OrderServiceImpl();
        productPredicate = new ProductPradicateImpl();
        ioService= new IOServiceImpl();
    }

    @Override
    public String saleProduct(String barcode) {

        if (barcode.equals("EXIT")) {
            ioService.printOnPrinter(orderService.showOrderItem()+" "+orderService.showPriceOrder());
            return ioService.printOnLcd("Your order:" + orderService.showOrderItem() + orderService.showPriceOrder());
        } else {
            return productPredicate.checkProduct(barcode);

        }
    }

//    private String checkProduct(String barcode) {
//        Optional<Product> productByID = getProductByID(barcode);
//
//        if (productByID.isPresent()) {
//            order.add(productByID.get());
//            return lcd.print(productByID.get().getName() + " " + productByID.get().getPrice());
//        } else {
//            return lcd.print("Product not found");
//        }
//    }

//    private Optional<Product> getProductByID(String barcode) {
//        if (barcode.isEmpty()) {
//            lcd.print("Invalid bar-code");
//            return Optional.empty();
//        } else {
//            return Optional.ofNullable(productRepository.getProductByBarCode(barcode));
//        }
//    }

//    private String showPriceOrder() {
//        Integer orderPrice = 0;
//        List<Integer> collect = order.stream().map(product -> product.getPrice().toBigInteger().intValueExact()).collect(Collectors.toList());
//
//        for (Integer integer : collect) {
//            orderPrice = orderPrice + integer;
//        }
//
//        return orderPrice.toString();
//    }
//
//    private String showOrderItem() {
//
//        Map<String, Integer> collect = order.stream().collect(Collectors.toMap(name -> name.getName(), price -> price.getPrice().toBigInteger().intValueExact()));
//
//        return collect.toString();
//    }
}
