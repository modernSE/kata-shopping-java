package api;

public class UsernamePasswordAuthenticationProvider {

    private String username;

    private String passwordHash;

    public UsernamePasswordAuthenticationProvider(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public UserConnection authenticate() {
        return new UserConnection();
    }
}
