package api.auth;

import api.UserConnection;

public class AlwaysOkAuthProvider implements AuthProvider {

    @Override
    public UserConnection authenticate() {
        return new UserConnection();
    }
    
}
