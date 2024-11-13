package amt_interface;

import java.util.ArrayList;
import java.util.List;

class User {
	private String userID;
    private String userPin;
    private Account account;

    public User(String userID, String userPin, Account account) {
        this.userID = userID;
        this.userPin = userPin;
        this.account = account;
    }

    public boolean authenticate(String id, String pin) {
        return userID.equals(id) && userPin.equals(pin);
    }

    public Account getAccount() {
        return account;
    }
}
