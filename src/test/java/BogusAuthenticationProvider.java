import api.UserConnection;
import wrappers.AuthenticationService;

public class BogusAuthenticationProvider implements AuthenticationService {
    @Override
    public UserConnection authenticate() {
        return new UserConnection();
    }
}
