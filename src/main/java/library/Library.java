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





public class Library {

    private ArrayList<Book> Books = new ArrayList<>();

    public Library() {
        createJSONBooks();
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
//        String line = "";
//        String splitBy ="";
//        try {
//
//
//            BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/books_data.csv"));
//            br.readLine();
//            while((line = br.readLine())!= null) {
//                String[] bookInfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                Book newBook = new Book(Integer.parseInt(bookInfo[0]),bookInfo[1],bookInfo[2],bookInfo[3], bookInfo[4], bookInfo[5] );
//                Books.add(newBook);
//            }
//        }
//
//        catch (IOException e) {
//            e.printStackTrace();
//        }
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

    public static void main(String[] args) {
        Library newLibrary = new Library();
        newLibrary.printBooksInfomation();
    }
}
