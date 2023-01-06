package library;

import org.json.CDL;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.google.gson.Gson;



public class Library {

    private ArrayList<Book> Books = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private User user;
    public Library() {
        populateLibrary();
    }

    public void createJSONBooks(){

        InputStream inputStream = Library.class.getClassLoader().getResourceAsStream("books_data.csv");
        String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();
        try {
            Files.write(Path.of("src/main/resources/books_data.json"), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void addNewBook(JSONObject book){
        Book newBook = new Book((String) book.get("Number"),(String)book.get("Title"),(String)book.get("Author"),(String)book.get("Genre"), (String)book.get("SubGenre"), (String)book.get("Publisher") );
        Books.add(newBook);
    }
    public void printBooksInfomation(){
        for (Book book:Books) {
            System.out.println(book);
        }
    }
    public void login(){
        System.out.println("Please enter your user name");
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
        if(isAdminInput=="Y"){
            isAdmin=true;
        }
        User newUser = new User(1,username,password,isAdmin);

        JSONObject obj = new JSONObject();
        obj.put("id", 1);
        obj.put("username", username);
        obj.put("password", password);
        obj.put("isAdmin",isAdmin);

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

    public static void main(String[] args) {
        Library library = new Library();
        library.addNewUser();
    }
}
