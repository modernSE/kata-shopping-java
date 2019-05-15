package wrappers;

import api.BankeinzugService;
import api.JBANConnection;
import api.JBANConnectionException;
import api.JBANValidationException;

public class JBANService implements PaymentService {

    private JBANConnection jbanConnection;

    private BankeinzugService bankeinzugService;

    public JBANService(String serviceId, BankeinzugService bankeinzugService) throws Exception {
        try {
            jbanConnection = new JBANConnection(serviceId);
        } catch (JBANValidationException e) {
            throw new Exception("Error validation JBAN");
        }
        this.bankeinzugService = bankeinzugService;
    }

    @Override
    public void pay(double amount) throws Exception {
        try {
            String jbanResult = jbanConnection.executeWithJBANToken(token -> afterAcquireJBANToken(amount, token));
            System.out.println("Payed " + amount + " via JBAN Bankeinzug.");
        } catch (
                JBANConnectionException e) {
            throw new Exception("Failed to execute transaction. Unknown error.");
        }
    }

    private String afterAcquireJBANToken(double amount, String token) {
        bankeinzugService.legitimiereBankeinzug(amount, token);
        return "JBAN Payment successful";
    }
}
