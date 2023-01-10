package library;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReturnBook {

    public static void returnBook(Scanner scanner, ArrayList<Book> books, User currentUser ){
        ArrayList<String> userBookIds = currentUser.getBooksLoanedOut();
        showUserBooks(books,userBookIds);
        System.out.println("Please enter the number of the book you wish to return");

        String bookNumber = scanner.nextLine();
        returnUserBooks(currentUser,bookNumber);

    }

    public static void showUserBooks(ArrayList<Book> books, ArrayList<String> userBookIds){

        for (String userBookId:userBookIds) {
            for (Book book:books) {
                if (book.getNumber().equals(userBookId)){
                    System.out.println(book);
                }
            }
        }
    }

    public static void returnUserBooks(User user, String bookNumber){
        JSONParser jsonParser = new JSONParser();
        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/user_data.json"));
            JSONArray userJsonArray = (JSONArray) userFile;

            for (int i=0; i < userJsonArray.size(); i++){
                JSONObject userObj = (JSONObject) userJsonArray.get(i);
                if(userObj.get("id").equals (user.getId())) {
                    ArrayList<String> userBooks = (ArrayList<String>) userObj.get("booksLoanedOut");
                    userBooks.remove(bookNumber);
                    userObj.put("booksLoanedOut", userBooks);
                    userJsonArray.set(i, userObj);
                    System.out.println("Book has been returned");
                }
            }
            FileWriter file = new FileWriter("src/main/resources/user_data.json");
            file.write(userJsonArray.toJSONString());
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
}
