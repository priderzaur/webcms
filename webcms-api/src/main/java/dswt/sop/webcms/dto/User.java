package dswt.sop.webcms.dto;

/**
 * Created by riordachioaia on 04.04.2018.
 */
public class User {
    private String email;
    private String password;

    public User() {}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
