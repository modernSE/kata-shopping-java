package api.payment;

public class JBANConnection {
    JBANTokenProvider jbanTokenProvider;

    public JBANConnection(String jban) throws JBANValidationException {
        validate(jban);
        jbanTokenProvider = new JBANTokenProvider();
    }

    private void validate(String jban) throws JBANValidationException {
        if (jban.length() != 10) {
            throw new JBANValidationException();
        }
    }

    public <T> T executeWithJBANToken(JBANCallback<T> jbanCallback) throws JBANConnectionException {
        if (jbanTokenProvider == null) {
            throw new JBANConnectionException("JBAN not validated");
        }
        return jbanCallback.execute(jbanTokenProvider.generateToken());
    }
}

class JBANTokenProvider {
    JBANTokenProvider() {

    }

    String generateToken() {
        return "jbantoken";
    }
}
