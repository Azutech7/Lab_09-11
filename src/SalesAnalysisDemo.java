import java.io.*;
import java.util.Scanner;

/**
 * Asks the user for a file path to input data,
 * prechecks the existence of that file,
 * and then processes that data for a month's
 * worth of data.
 * 
 * @author Mason Heuberger, mheuberger@student.sdccd.edu
 * @version 1.0
 * @since 2026-11-22
 */
public class SalesAnalysisDemo {
    public static void main(String args[]) throws IOException {
        Scanner kb = new Scanner(System.in);
        String path;

        do {
            System.out.print("Enter the path to the SalesData.txt file: ");
            path = kb.nextLine();
        } while (!new File(path).exists());

        System.out.println(); // For formatting
        SalesAnalysis salesAna = new SalesAnalysis(path);

        salesAna.processFile();
        salesAna.writeOutput();
    }
}