package library;

import org.json.CDL;

import java.io.*;
import java.util.ArrayList;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.FileReader;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class Library {

    private ArrayList<Book> books = new ArrayList<>();

    private ArrayList<String> loanedBooks = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private User currentUser;
    public Library() {
        populateLibrary();
        getUsers();
        populateLoanedBooks();
    }



    public void populateLibrary(){
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/books_data.json")) {
            Object obj = parser.parse(reader);
            JSONArray bookList = (JSONArray) obj;
            bookList.forEach( book -> addNewBook((JSONObject) book)  );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void populateLoanedBooks(){
        for (User user:users) {
            for (String bookNumber:user.getBooksLoanedOut()) {
                loanedBooks.add(bookNumber);
            }
        }
    }
    public void addNewBook(JSONObject book){

        Book newBook = new Book((String) book.get("Number"),(String)book.get("Title"),(String)book.get("Author"),(String)book.get("Genre"), (String)book.get("SubGenre"), (String)book.get("Publisher"),(long)book.get("timesLoanedOut") );
        books.add(newBook);
    }
    public void getUsers(){
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/user_data.json")) {
            Object obj = parser.parse(reader);
            JSONArray userList = (JSONArray) obj;
            userList.forEach( user -> addUser((JSONObject) user)  );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void addUser(JSONObject user){

        User newUser = new User(Math.toIntExact((Long) user.get("id")),(String)user.get("username"),(String) user.get("password"),(Boolean)user.get("isAdmin"),(ArrayList<String>) user.get("booksLoanedOut"));
        users.add(newUser);
    }

    public void printBooksInfomation(){
        for (Book book:books) {
            System.out.println(book);
        }
    }

    public void addNewUser(){
        System.out.println("Please enter your user name");
        String username = scanner.nextLine();
        System.out.println("Please enter your password");
        String password = scanner.nextLine();
        Boolean isAdmin = false;
        String isAdminInput = "";
        while(!isAdminInput.equals("Y")&&!isAdminInput.equals("N")){
            System.out.println("Admin? Y/N");
            isAdminInput = scanner.nextLine().toUpperCase();
        }
        if(isAdminInput.equals("Y")){
            isAdmin=true;
        }
        ArrayList<String> bookArry = new ArrayList<>();
        JSONObject obj = new JSONObject();
        obj.put("id", users.size()+1);
        obj.put("username", username);
        obj.put("password", password);
        obj.put("isAdmin",isAdmin);
        obj.put("booksLoanedOut",bookArry);

        JSONParser jsonParser = new JSONParser();

        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/user_data.json"));
            JSONArray jsonArray = (JSONArray) userFile;
            jsonArray.add(obj);
            FileWriter file = new FileWriter("src/main/resources/user_data.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

    public void login(){
        Boolean isUserLoggedIn = false;
        while(!isUserLoggedIn) {
            System.out.println("Login");
            System.out.println("Please enter your user name");
            String username = scanner.nextLine();
            System.out.println("Please enter your password");
            String password = scanner.nextLine();
            for(User user: users){
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    isUserLoggedIn=true;
                    currentUser = user;
                }
            }
            if(!isUserLoggedIn){
                System.out.println("Incorrect login details");
            }
        }
        mainMenu();

    }

    public void mainMenu(){
        System.out.println("Main Menu");
        System.out.println(currentUser.getUsername());
        while (true){
            if(currentUser.getBooksLoanedOut()!=null){
                System.out.println("Books you have loaned out:");
                for (String bookNumber:currentUser.getBooksLoanedOut()) {
                    for(Book book: books){
                        if(book.getNumber().equals(bookNumber)){
                            System.out.println(book.getTitle());
                        }
                    }
                }
            }
            System.out.println("Enter 1 to loan out a book");
            System.out.println("Enter 2 to view books loaned out");
            if(currentUser.getAdmin()){
                System.out.println("Enter 3 to create a new user");
            }
            String input = scanner.nextLine();
            if(input.equals("1")){
                LoanBooks.loanBook(scanner,loanedBooks,books,currentUser);
                populateLoanedBooks();

            } else if (input.equals("2")) {
                viewLoanedBooks();
            } else if (input.equals("3")) {
                addNewUser();
            }
        }
    }







    public void viewLoanedBooks(){

    }

    public static void main(String[] args) {
        Library library = new Library();
        library.login();


    }
}
