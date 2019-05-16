import api.UserConnection;
import api.UsernamePasswordAuthenticationProvider;

public class ApiUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	private UsernamePasswordAuthenticationProvider apiAuthenticationProvider;
	
	public ApiUsernamePasswordAuthenticationProvider(String username, String passwordHash) {
        apiAuthenticationProvider = new UsernamePasswordAuthenticationProvider(username, passwordHash);
    }
	
	@Override
	public UserConnection authenticate() {
		return apiAuthenticationProvider.authenticate();
	}

}
