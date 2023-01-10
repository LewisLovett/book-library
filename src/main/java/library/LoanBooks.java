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

public class LoanBooks {

    public static void loanBook(Scanner scanner, ArrayList<String> loanedBooks, ArrayList<Book> books, User currentUser ){
        System.out.println("Please enter the number of the book you wish to loan");
        System.out.println(books);
        String bookNumber = scanner.nextLine();
        if(loanedBooks.contains(bookNumber)){
            System.out.println("That book has already been loaned out");
        }else{
            if(doesBookExist(bookNumber, books)) {
                System.out.println("Loaned out");
                currentUser.addLoanedBook(bookNumber);
                updateLoanTimes(bookNumber);
                updateUserBooks(currentUser,bookNumber);
            }else{
                System.out.println("That book does not exist");
            }
        }

    }
    public static Boolean doesBookExist(String bookNumber,ArrayList<Book> books){
        for (Book book:books) {
            if(book.getNumber().equals(bookNumber)){
                return true;
            }
        }
        return false;
    }

    public static void updateLoanTimes(String bookNumber){
        JSONParser jsonParser = new JSONParser();
        try {
            Object bookFile = jsonParser.parse(new FileReader("src/main/resources/books_data.json"));
            JSONArray bookJsonArray = (JSONArray) bookFile;
            for (int i=0; i < bookJsonArray.size(); i++){
                JSONObject bookObj = (JSONObject) bookJsonArray.get(i);
                if(bookObj.get("Number").equals(bookNumber)) {
                    long timesLoanedOut = (long)bookObj.get("timesLoanedOut");
                    timesLoanedOut++;
                    bookObj.put("timesLoanedOut", timesLoanedOut);
                    bookJsonArray.set(i, bookObj);
                }
            }
            FileWriter file = new FileWriter("src/main/resources/books_data.json");
            file.write(bookJsonArray .toJSONString());
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

    public static void updateUserBooks(User user, String bookNumber){
        JSONParser jsonParser = new JSONParser();
        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/user_data.json"));
            JSONArray userJsonArray = (JSONArray) userFile;

            for (int i=0; i < userJsonArray.size(); i++){
                JSONObject userObj = (JSONObject) userJsonArray.get(i);
                if(userObj.get("id").equals (user.getId())) {
                    ArrayList<String> userBooks = (ArrayList<String>) userObj.get("booksLoanedOut");
                    userBooks.add(bookNumber);
                    userObj.put("booksLoanedOut", userBooks);
                    userJsonArray.set(i, userObj);
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
