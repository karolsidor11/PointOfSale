package service.predicateService;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.accessProductService.AccessProductService;
import service.ioService.IOService;
import service.orderService.OrderService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class ProductPradicateImplTest {

    private AccessProductService accessProductService;
    private IOService ioService;
    private OrderService orderService;
    private ProductPredicate productPredicate;


    @BeforeEach
    void setUp() {
        accessProductService = mock(AccessProductService.class);
        ioService = mock(IOService.class);
        orderService = mock(OrderService.class);
        productPredicate = new ProductPradicateImpl(accessProductService, ioService, orderService);
    }

    @org.junit.jupiter.api.Test
    public void checkProduct_getProductByID() {

        //  given
        String barcode = "ZSD123";

        // when
        when(accessProductService.getProductById(barcode)).thenReturn(Optional.of(new Product("Apple", new BigDecimal(11), barcode)));

        //then
        assertNotNull(accessProductService.getProductById(barcode).get());
        assertEquals("Apple", accessProductService.getProductById(barcode).get().getName());

    }

    @Test
    public void checkProduct_getIfBarcodeIsInvalid() {

        // given
        String barcode = "123456";

        // when
        Optional<Product> productById = accessProductService.getProductById(barcode);

        //  then
        assertEquals(false, productById.isPresent());
    }

    @Test
    public void checkProduct_EmptyProduct() {

//        given
        String barcode = "";

//       when
        when(accessProductService.getProductById(barcode)).thenReturn(Optional.empty());
        when(ioService.printOnLcd("Product not found")).thenReturn("Product not found");
        String s = productPredicate.checkProduct(barcode);

//        then
        verify(accessProductService, times(1)).getProductById(anyString());
        verify(ioService, times(1)).printOnLcd(anyString());
        verify(orderService, times(0)).add(any(Product.class));
        assertEquals("Product not found", productPredicate.checkProduct(barcode));

    }
    @Test
    public void checkProduct_ReturnProduct(){

//        given
        String barcode="XDF123";

//        when
        when(accessProductService.getProductById(barcode)).thenReturn(Optional.of(new Product("Apple", new BigDecimal(11),barcode)));
        when(productPredicate.checkProduct(barcode)).thenReturn("{Apple=11}");
        String s = productPredicate.checkProduct(barcode);

//        then
        assertEquals("{Apple=11}", s);
        verify(accessProductService, atLeastOnce()).getProductById(barcode);
        verify(ioService, atLeastOnce()).printOnLcd(anyString());
        verify(orderService,atLeastOnce()).add(any(Product.class));

    }

}