package service.orderService;

import model.Product;

public interface OrderService {

    String showPriceOrder();

    String showOrderItem();

    void add(Product product);

}
