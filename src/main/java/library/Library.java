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

public class Library {

    private ArrayList<Book> Books = new ArrayList<>();

    public Library() {
        createJSONBooks();
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

    public void printBooksInfomation(){
        for (Book book:Books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        Library newLibrary = new Library();
//        newLibrary.printBooksInfomation();
    }
}
