package service;

import model.Product;
import repository.ProductRepository;
import utils.Lcd;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private List<Product> order = new ArrayList<>();
    private Lcd lcd;
    private Printer printer;

    public ProductServiceImpl(ProductRepository productRepository, Lcd lcd, Printer printer) {
        this.productRepository = productRepository;
        this.lcd = lcd;
        this.printer = printer;
    }

    @Override
    public String saleProduct(String barcode) {

        if (barcode.equals("EXIT")) {
            printer.print(showOrderItem() + showPriceOrder());
            return lcd.print("Your order:" + showOrderItem() + showPriceOrder());
        } else {
            return checkProduct(barcode);

        }
    }

    private String checkProduct(String barcode) {
        Optional<Product> productByID = getProductByID(barcode);

        if (productByID.isPresent()) {
            order.add(productByID.get());
            return lcd.print(productByID.get().getName() + " " + productByID.get().getPrice());
        } else {
            return lcd.print("Product not found");
        }
    }

    private Optional<Product> getProductByID(String barcode) {
        if (barcode.isEmpty()) {
            lcd.print("Invalid bar-code");
            return Optional.empty();
        } else {
            return Optional.ofNullable(productRepository.getProductByBarCode(barcode));
        }
    }

    private String showPriceOrder() {
        Integer orderPrice = 0;
        List<Integer> collect = order.stream().map(product -> product.getPrice().toBigInteger().intValueExact()).collect(Collectors.toList());

        for (Integer integer : collect) {
            orderPrice = orderPrice + integer;
        }

        return orderPrice.toString();
    }

    private String showOrderItem() {

        Map<String, Integer> collect = order.stream().collect(Collectors.toMap(name -> name.getName(), price -> price.getPrice().toBigInteger().intValueExact()));

        return collect.toString();
    }
}
