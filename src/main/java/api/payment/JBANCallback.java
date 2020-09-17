package api.payment;

public interface JBANCallback<T> {
    T execute(String jbanToken);
}
