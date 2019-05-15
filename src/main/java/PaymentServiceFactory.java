import api.BankeinzugService;
import wrappers.BuyBuddyService;
import wrappers.JBANService;
import wrappers.PaymentService;

public class PaymentServiceFactory {
    public static PaymentService createPaymentService(String serviceId) throws Exception {
        if (serviceId.startsWith("buybuddy:")) {
            return new BuyBuddyService(serviceId.substring(9));
        } else if (serviceId.startsWith("jban:")) {
            BankeinzugService bankeinzugService = new BankeinzugService();
            return new JBANService(serviceId.substring(5), bankeinzugService);
        } else {
            throw new Exception("Unknown payment provider!");
        }
    }
}
