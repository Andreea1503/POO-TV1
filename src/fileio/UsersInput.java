package fileio;
import java.util.ArrayList;
public class UsersInput {
    private CredentialsInput credentials;

    public UsersInput() {
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "UsersInput{"
                + "usersCredentials="
                + credentials
                + '}';
    }

}
