
package org.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("src/main/java/org/example/books_data.csv"));
        sc.useDelimiter(",");
        while (sc.hasNext())
        {
            System.out.print(sc.next()); }
        sc.close();
    }
}
