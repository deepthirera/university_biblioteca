package biblioteca;

public class User {
    private String userName, password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public String getUsername() {
        return this.userName;  }

    public String getPassword() {
        return this.password;
    }
}

