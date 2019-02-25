package service.orderService;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private List<Product> productList = new ArrayList<>();

    @Override
    public String showPriceOrder() {
        Integer orderPrice = 0;
        List<Integer> collect = productList.stream().map(product -> product.getPrice().toBigInteger().intValueExact()).collect(Collectors.toList());

        for (Integer integer : collect) {
            orderPrice = orderPrice + integer;
        }
        return orderPrice.toString();
    }

    @Override
    public String showOrderItem() {
        Map<String, Integer> collect = productList.stream().collect(Collectors.toMap(name -> name.getName(), price -> price.getPrice().toBigInteger().intValueExact()));

        return collect.toString();
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }
}
