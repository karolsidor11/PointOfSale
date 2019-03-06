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

    @Before
    public void setup() {
        productRepository = new ProductRepositoryImpl();
        Product builder = new Product.Builder().name("Chessse").price(new BigDecimal(12)).barcode("XDS123").build();
    }

    @Test
    public void shouldReturnAllProduct() {

        //  when
        List<Product> allProducts = productRepository.getAllProducts();

        // then
        assertEquals(4, allProducts.size());
    }

    @Test
    public void shouldGetProductFromList() {

        // given
        String barcode = "XDF123";

        // when
        Product xdf123 = productRepository.getProductByBarCode(barcode);

        //  then
        assertNotNull(xdf123);
        assertEquals("XDF123", xdf123.getBarcode());
    }

    @Test
    public void shouldAddProductToOrder() {

        // given
        Product.Builder product = new Product.Builder();
        Product build = product.name("Banana").barcode("XDF345").price(new BigDecimal(22)).build();

        //  when
        List<Product> allProducts = productRepository.getAllProducts();
        allProducts.add(build);

        //   then
        assertEquals(5, allProducts.size());
        assertEquals("XDF345", build.getBarcode());

    }

    @Test
    public void shouldCheckProductByID() {

        // given
        String barcode = "XDY321";

        // when
        Product xds123 = productRepository.getProductByBarCode(barcode);

        //  then
        assertEquals(barcode, xds123.getBarcode());
    }
}
