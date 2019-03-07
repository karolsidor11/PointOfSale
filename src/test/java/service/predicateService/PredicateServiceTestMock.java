package service.predicateService;

import model.Product;
import org.junit.Before;
import org.junit.Test;
import service.accessProductService.AccessProductService;
import service.accessProductService.AccessProductServiceImpl;
import service.ioService.IOService;
import service.ioService.IOServiceImpl;
import service.orderService.OrderService;
import service.orderService.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PredicateServiceTestMock {

    private IOService ioService;
    private AccessProductService service;
    private OrderService orderService;
    private ProductPredicate productPredicate;


    @Before
    public void before() {

        ioService = mock(IOServiceImpl.class);
        service = mock(AccessProductServiceImpl.class);
        orderService = mock(OrderServiceImpl.class);
        productPredicate = mock(ProductPradicateImpl.class);

        Product product = new Product("Banana", new BigDecimal(23), "XCD212");

        when(productPredicate.checkProduct("XDF123")).thenReturn("Apple 12");
        when(productPredicate.checkProduct(null)).thenReturn("Product not found");
        when(service.getProductById("XDC123")).thenReturn(Optional.ofNullable(product));
    }

    @Test
    public void checkProduct() {

        String xdf123 = productPredicate.checkProduct("XDF123");

        assertEquals("Apple 12", xdf123);
    }

    @Test
    public void emptyProduct() {
        String s = productPredicate.checkProduct(null);
        assertEquals("Product not found", s);
    }

    @Test
    public void getProductFromAccess(){
        Optional<Product> xdc123 = service.getProductById("XDC123");
        assertNotNull(xdc123.get());
        assertEquals("Banana", xdc123.get().getName());
    }

    @Test
    public void emptyGetProduct(){

        Optional<Product> productById = service.getProductById(null);

        assertEquals(Optional.empty(), productById);

    }

    @Test
    public void getProduct(){
        Optional<Product> xdf123 = service.getProductById("XDF123");

        System.out.println(xdf123);
    }
}
