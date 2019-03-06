package service.productService;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.accessProductService.AccessProductService;
import service.accessProductService.AccessProductServiceImpl;
import service.ioService.IOService;
import service.ioService.IOServiceImpl;
import service.orderService.OrderService;
import service.orderService.OrderServiceImpl;
import service.predicateService.ProductPredicate;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private static final Logger logger = Logger.getLogger(String.valueOf(ProductServiceImplTest.class));

    private OrderService orderService;
    private ProductPredicate productPredicate;
    private IOService ioService;
    private AccessProductService accessProductService;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        orderService = mock(OrderServiceImpl.class);
        productPredicate = mock(ProductPredicate.class);
        ioService = mock(IOServiceImpl.class);
        accessProductService = mock(AccessProductServiceImpl.class);

        productService = new ProductServiceImpl(productPredicate, orderService, ioService, accessProductService);

    }

    @Test
    public void saleProduct_BarcodeIsExit() {

//        given
        String barcode = "EXIT";

//        when
        when(orderService.showOrderItem()).thenReturn("Apple= 12");
        when(orderService.showPriceOrder()).thenReturn("12");
        when(productService.saleProduct("EXIT")).thenReturn("Podsumowanie zamówienia");

        String s = productService.saleProduct(barcode);
        System.out.println(s);

//        then
        assertEquals("Podsumowanie zamówienia", productService.saleProduct(barcode));
        assertEquals("12", orderService.showPriceOrder());
        verify(ioService, atLeastOnce()).printOnLcd(anyString());
        verify(orderService, atLeastOnce()).showPriceOrder();

    }


    @Test
    public void saleProduct_shouldSaleEmptyProduct() {

        // given
        String barcode = "";

        // when
        when(accessProductService.getProductById(barcode)).thenReturn(Optional.empty());
        when(ioService.printOnLcd("Product not found")).thenReturn("Product not found");
        productService.saleProduct(barcode);

        // then
        assertEquals(Optional.empty(), accessProductService.getProductById(barcode));
        assertEquals("Product not found", ioService.printOnLcd("Product not found"));
        verify(ioService, atLeastOnce()).printOnLcd(anyString());
        verify(accessProductService, atLeastOnce()).getProductById(anyString());
        verify(productPredicate, atLeastOnce()).checkProduct(anyString());
    }

    @Test
    public void saleProduct_shouldSaleProduct() {

        // given
        String barcode = "XDF123";
        Product builder = new Product.Builder().name("Apple").price(new BigDecimal(21)).barcode(barcode).build();

        //  when
        when(accessProductService.getProductById(barcode)).thenReturn(Optional.ofNullable(builder));
        when(productPredicate.checkProduct(barcode)).thenReturn("Apple=12");

        accessProductService.getProductById(barcode);
        String s = productService.saleProduct(barcode);

        // then
        verify(accessProductService, atLeastOnce()).getProductById(barcode);
        verify(productPredicate, atLeastOnce()).checkProduct(barcode);
        assertEquals("Apple=12", productService.saleProduct(barcode));


    }
}