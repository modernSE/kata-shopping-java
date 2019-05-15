package api;

public interface JBANCallback<T> {
    T execute(String jbanToken);
}
