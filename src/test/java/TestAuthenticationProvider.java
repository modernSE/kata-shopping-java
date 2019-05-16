import api.UserConnection;

public class TestAuthenticationProvider implements AuthenticationProvider {

	@Override
	public UserConnection authenticate() {
		return new UserConnection();
	}

}
