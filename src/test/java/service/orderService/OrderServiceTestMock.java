package service.orderService;

import model.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTestMock {

    @Mock
    private OrderService orderService;

    @Before
    public void before() {
        orderService = mock(OrderServiceImpl.class);
    }

    @Test
    public void shouldOrderPrice() {
        when(orderService.showPriceOrder()).thenReturn("55");
        assertEquals("55", orderService.showPriceOrder());
    }

    @Test
    public void shouldOrderItem() {
        when(orderService.showOrderItem()).thenReturn("Apple - 12");
        assertEquals("Apple - 12", orderService.showOrderItem());
    }

    @Test
    public void addProduct() {
        Product product = new Product("Apple", new BigDecimal(12), "XDF123");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                System.out.println("Add method" + invocationOnMock.getArgument(0));
                return null;
            }
        }).when(orderService).add(product);

    }
}
