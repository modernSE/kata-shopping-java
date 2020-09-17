package api.auth;

import api.UserConnection;

public interface AuthProvider {
    UserConnection authenticate();
}
