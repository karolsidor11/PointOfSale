package service.orderService;

import model.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private List<Product> productList;

    public OrderServiceImpl(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String showPriceOrder() {
        List<Integer> collect = productList.stream().map(product -> product.getPrice().toBigInteger().intValueExact()).collect(Collectors.toList());

        int sum = collect.stream().mapToInt(Integer::intValue).sum();
        return String.valueOf(sum);
    }

    @Override
    public String showOrderItem() {

        Map<String, Integer> collect = productList.stream().collect(Collectors.toMap(Product::getName, price -> price.getPrice().toBigInteger().intValueExact()));
        return collect.toString();

    }

    @Override
    public void add(Product product) {
        if (product != null) {
            productList.add(product);
        }
    }
}
