package service.ioService;

public class IOServiceImpl implements IOService {
    @Override
    public String printOnLcd(String message) {
        return message;
    }

    @Override
    public String printOnPrinter(String message) {
        return message;
    }
}
