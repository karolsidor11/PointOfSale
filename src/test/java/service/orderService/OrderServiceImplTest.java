package service.orderService;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

class OrderServiceImplTest {

    private OrderService orderService;
    @Spy
    private List<Product> productList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        orderService = new OrderServiceImpl(productList);
    }

    @Test
    public void add_shouldAddProductToList() {

        // given
        Product product = new Product("Apple", new BigDecimal(11), "CDA123");

        //  when
        orderService.add(product);

        // then
        assertNotNull(productList);
        assertEquals(1, productList.size());
        assertEquals("Apple", productList.get(0).getName());

    }

    @Test
    public void add_shouldAddEmptyProduct() {

        // when
        orderService.add(null);

        // then
        assertEquals(0, productList.size());
        assertEquals(true, productList.isEmpty());
    }

    @Test
    public void showPriceOrder_ShouldReturnOnePrice() {

        // given
        Product product = new Product.Builder().name("Ananas").price(new BigDecimal(22)).barcode("ANA123").build();

        // when
        productList.add(product);
        String s = orderService.showPriceOrder();

        // then
        assertNotNull(productList);
        assertEquals("22", s);
    }

    @Test
    public void showProceorder_ShouldAddManyProducts() {

        // given
        Product product = new Product.Builder().name("Apple").price(new BigDecimal(23)).barcode("XDF456").build();
        Product product1 = new Product.Builder().name("Orange").price(new BigDecimal(45)).barcode("XDF457").build();

        // when
        productList.add(product);
        productList.add(product1);
        String orderPrice = orderService.showPriceOrder();

        // then
        assertEquals(2, productList.size());
        assertEquals("68", orderPrice);


    }

    @Test
    public void showPriceOrder_IfProductListIsEmpty() {

        // when
        String s = orderService.showPriceOrder();

        // then
        assertNotNull(s);
        assertEquals("0", s);

    }

    @Test
    public void shouldOrderIteam_IfProductListIsEmpty() {

//        when
        String s = orderService.showOrderItem();

//        then
        assertNotNull(s);
        assertEquals("{}", s);
    }


    @Test
    public void showOrderIteam_shouldReturnOrderIteam() {

        // given
        Product product = new Product.Builder().name("Banana").price(new BigDecimal(12)).barcode("BAN321").build();

        // when
        productList.add(product);
        String s = orderService.showOrderItem();

        // then
        assertNotNull(productList);
        assertEquals("{Banana=12}", s);

    }

}