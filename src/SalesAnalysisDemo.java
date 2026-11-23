import java.io.*;
import java.util.Scanner;

public class SalesAnalysisDemo {

    public static void main(String args[]) throws IOException {
        Scanner kb = new Scanner(System.in);
        String path;

        do {
            System.out.print("Enter the path to the SalesData.txt file: ");
            path = kb.nextLine();
        } while (!new java.io.File(path).exists());

        SalesAnalysis salesAna = new SalesAnalysis(path);

        salesAna.processFile();
        salesAna.writeOutput();
    }
}