package library;

import java.util.ArrayList;

public class User {
    public User(int id, String username, String password, Boolean isAdmin,ArrayList<String> booksLoanedOut) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.booksLoanedOut = booksLoanedOut;
    }

    private long id;
    private String username;
    private String password;
    private Boolean isAdmin;
    private ArrayList<String> booksLoanedOut;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public ArrayList<String> getBooksLoanedOut() {
        return booksLoanedOut;
    }

    public void addLoanedBook(String bookNumber){
        this.booksLoanedOut.add(bookNumber);
    }

    public void returnBook(String bookNumber){
        this.booksLoanedOut.remove(bookNumber);
    }

    public void setBooksLoanedOut(ArrayList<String> booksLoanedOut) {
        this.booksLoanedOut = booksLoanedOut;
    }
}
