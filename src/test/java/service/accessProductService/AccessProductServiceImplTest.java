package service.accessProductService;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import repository.ProductRepository;
import service.ioService.IOService;

import java.math.BigDecimal;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

class AccessProductServiceImplTest {

    private AccessProductService accessProductService;
    private ProductRepository productRepository;
    private IOService ioService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        ioService = mock(IOService.class);
        accessProductService = new AccessProductServiceImpl(ioService, productRepository);
    }

    @org.junit.jupiter.api.Test
    public void getProductById_shouldReturnEmptyOptionalIfBarcodeIsEmpty() {

        // given
        String barcode = "XDF123";
        when(productRepository.getProductByBarCode(barcode)).thenReturn(null);

        // when
        Optional<Product> productById = accessProductService.getProductById(barcode);

        // then
        assertEquals(false, productById.isPresent());

    }

    @Test
    public void getProductById_shouldPrintOnLCdMessageIfBarcodeIsEmpty() {

        // given
        String barcode = "";

        // when
        Optional<Product> productById = accessProductService.getProductById(barcode);

        //then
        verify(ioService, times(1)).printOnLcd(anyString());

    }

    @Test
    public void getProductById_shouldReturnProduct() {

        // given
        String barcode = "CDA123";

        // when
        when(productRepository.getProductByBarCode(barcode)).thenReturn(new Product("Apple", new BigDecimal(12), "CDA123"));
        Optional<Product> productById = accessProductService.getProductById(barcode);

        // then
        assertNotNull(productById.get());
    }

    @Test
    public void getProductById_shouldReturnEmptyOptionalIfProductNotInDb() {

        // given
        String barcode = "CDF123";

        // when
        Optional<Product> productById = accessProductService.getProductById(barcode);

        //then
        assertEquals(false, productById.isPresent());

    }
}