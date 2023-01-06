package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private ArrayList<Book> Books = new ArrayList<>();

    public Library() {
        populateLibrary();
    }

    public void populateLibrary(){
        String line = "";
        String splitBy ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/books_data.csv"));
            br.readLine();
            while((line = br.readLine())!= null) {
                String[] bookInfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Book newBook = new Book(Integer.parseInt(bookInfo[0]),bookInfo[1],bookInfo[2],bookInfo[3], bookInfo[4], bookInfo[5] );
                Books.add(newBook);
            }
        }

        catch (IOException e) {
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
        newLibrary.printBooksInfomation();
    }
}
