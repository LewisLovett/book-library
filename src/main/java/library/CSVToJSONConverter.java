package library;

import org.json.CDL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class CSVToJSONConverter {

    public static void createJSONBooks(){

        InputStream inputStream = Library.class.getClassLoader().getResourceAsStream("books_data.csv");
        String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();
        try {
            Files.write(Path.of("src/main/resources/books_data.json"), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser jsonParser = new JSONParser();
        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/books_data.json"));
            JSONArray bookJsonArray = (JSONArray) userFile;

            for (int i=0; i < bookJsonArray.size(); i++){
                JSONObject bookObj = (JSONObject) bookJsonArray.get(i);
                bookObj.put("timesLoanedOut",0);
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

    public static void main(String[] args) {
        CSVToJSONConverter.createJSONBooks();
    }
}
