package fileio;

/**
 * Class that contains the credentials of the user.
 */
public class CredentialsInput {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public CredentialsInput() {
    }

    /**
     * Method that returns the name of the user.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets the name of the user.
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Method that returns the password of the user.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method that sets the password of the user.
     * @param password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Method that returns the account type of the user.
     * @return accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Method that sets the account type of the user.
     * @param accountType
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * Method that returns the country of the user.
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Method that sets the country of the user.
     * @param country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Method that returns the balance of the user.
     * @return balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Method that sets the balance of the user.
     * @param balance
     */
    public void setBalance(final String balance) {
        this.balance = balance;
    }
}
