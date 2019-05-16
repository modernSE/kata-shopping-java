import api.UserConnection;

public interface AuthenticationProvider {

	public UserConnection authenticate();
}
