//package service;
//
//import model.Product;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import repository.ProductRepository;
//import utils.Lcd;
//import utils.Printer;
//
//import java.math.BigDecimal;
//
//import static junit.framework.TestCase.assertEquals;
//import static junit.framework.TestCase.assertNotNull;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ProductServiceTest {
//
//    private ProductServiceImpl productService;
//
//    @Mock
//    private ProductRepository productRepository;
//    @Mock
//    private Lcd lcd;
//    @Mock
//    private Printer printer;
//
//    @Before
//    public void before() {
//        productService = new ProductServiceImpl(productRepository, lcd, printer);
//    }
//
//    @Test
//    public void shouldGetProducByID() {
//        Product product = new Product("Apple", new BigDecimal(3), "XDF123");
//
//        Mockito.when(productRepository.getProductByBarCode("XDF123")).thenReturn(product);
//
//        assertNotNull(product);
//        assertEquals("Apple", productRepository.getProductByBarCode("XDF123").getName());
//
//    }
//
//    @Test
//    public void shouldSaleProduct() {
//
//        Mockito.when(productService.saleProduct("XDF123")).thenReturn("Banana 5");
//
//        assertEquals("Banana 5", productService.saleProduct("XDF123"));
//
//    }
//
//    @Test
//    public void confirmOrder() {
//
//        Mockito.when(productService.saleProduct("EXIT")).thenReturn("Your order:");
//
//        String exit = productService.saleProduct("EXIT");
//
//        assertThat(exit).isEqualTo("Your order:");
//    }
//
//    @Test
//    public void shouldProductNotFound(){
//
//        Mockito.when(productService.saleProduct("")).thenReturn("Product not found");
//
//        String s = productService.saleProduct("");
//
//        assertEquals("Product not found", s);
//    }
//
//}
