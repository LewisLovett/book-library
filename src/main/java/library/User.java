package library;

public class User {
    public User(int id, String username, String password, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    private int id;
    private String username;
    private String password;
    private Boolean isAdmin;
    private String[] booksLoanedOut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String[] getBooksLoanedOut() {
        return booksLoanedOut;
    }

    public void setBooksLoanedOut(String[] booksLoanedOut) {
        this.booksLoanedOut = booksLoanedOut;
    }
}
