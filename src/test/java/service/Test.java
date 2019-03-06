package service;

import model.Product;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class Test {

    @Mock
    private ProductRepository productRepository;
    private List<Product> productList = new ArrayList<>();

    @Before
    public void before() {
        Product product = new Product("Banana", new BigDecimal(22), "XDF321");
        productList.add(product);
        Mockito.when(productRepository.getProductByBarCode("123")).thenReturn(new Product("Apple", new BigDecimal(12), "123"));
        Mockito.when(productRepository.getAllProducts()).thenReturn(productList);
    }

    @org.junit.Test
    public void getProduct() {
        Product productByBarCode = productRepository.getProductByBarCode("123");

        assertNotNull(productByBarCode);
        assertEquals("Apple", productByBarCode.getName());

    }
    @org.junit.Test
    public void invalidBarcode(){
        Product acb = productRepository.getProductByBarCode("ABC");

        assertNull(acb);
    }

    @org.junit.Test
    public void shouldGetProductList() {
        List<Product> allProducts = productRepository.getAllProducts();

        assertNotNull(allProducts);
        assertEquals(1, allProducts.size());
    }


}
