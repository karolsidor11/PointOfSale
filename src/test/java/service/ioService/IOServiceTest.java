package service.ioService;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class IOServiceTest {

    private IOService ioService;

    @Before
    public void before() {
        ioService = new IOServiceImpl();
    }

    @Test
    public void shouldPrintOnLcd() {

        // given
        String text = "WELCOME";

        // when
        String welcome = ioService.printOnLcd(text);

        //  then
        assertNotNull(welcome);
        assertEquals("WELCOME", welcome);
    }

    @Test
    public void shouldPrintOnPrinter() {

        //   given
        String printer = "Printer";

        //  when
        String printers = ioService.printOnPrinter(printer);

        // then
        assertNotNull(printer);
        assertEquals("Printer", printer);
    }

}
