package repository;

import model.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductRepositoryTest {

    private ProductRepository productRepository;
    private Product product;

    @Before
    public void before() {
        productRepository = new ProductRepositoryImpl();
        product = new Product("Chessse", new BigDecimal(12), "XDS123");

    }

    @Test
    public void shouldAllProduct() {
        List<Product> allProducts = productRepository.getAllProducts();
        assertEquals(4, allProducts.size());
    }

    @Test
    public void shouldGetProductFromList() {

        Product xdf123 = productRepository.getProductByBarCode("XDF123");
        assertNotNull(xdf123);
    }

    @Test
    public void shouldAddProductToOrder() {

        List<Product> allProducts = productRepository.getAllProducts();
        allProducts.add(product);

        assertEquals(5, allProducts.size());
    }

    @Test
    public void shouldCheckProductByID(){

        List<Product> allProducts = productRepository.getAllProducts();
        allProducts.add(product);

        Product xds123 = productRepository.getProductByBarCode("XDS123");

        assertEquals(xds123, productRepository.getProductByBarCode("XDS123"));
    }
}
