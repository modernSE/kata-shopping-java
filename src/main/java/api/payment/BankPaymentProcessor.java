package api.payment;

public class BankPaymentProcessor implements PaymentProcessor {

    private BankeinzugService bankeinzugService = new BankeinzugService();

    private final String JBAN;

    
    public BankPaymentProcessor(String jBAN) {
        JBAN = jBAN;
    }

    @Override
    public void pay(double price) throws PaymentFailedException {
        String jbanResult = null;
        try {
            JBANConnection jbanConnection = new JBANConnection(JBAN);
            jbanResult = jbanConnection.executeWithJBANToken((token) -> afterAcquireJBANToken(token, price));
            System.out.println("Payed " + price + " via JBAN Bankeinzug." + jbanResult);
        } catch (JBANValidationException e) {
            jbanResult = "Error validation JBAN";
            throw new PaymentFailedException(jbanResult);
        } catch (JBANConnectionException e) {
            jbanResult = "Failed to execute transaction. Unknown error.";
            throw new PaymentFailedException(jbanResult);
        }
    }
    
    private String afterAcquireJBANToken(String token, double price) {
        bankeinzugService.legitimiereBankeinzug(price, token);
        return "JBAN Payment successful";
    }

    
}
