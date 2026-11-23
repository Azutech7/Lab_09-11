import java.io.*;
import java.util.Scanner;

/**
 * Handles the data scraping printing component
 * of the sales analysis.
 * 
 * @author Mason Heuberger, mheuberger@student.sdccd.edu
 * @version 1.0
 * @since 2026-11-22
 */
public class SalesAnalysis {
    private double[] weeklyTotal;
    private int lineNumber;
    private String inputFile;

    public static final int WEEKS_IN_MONTH = 4;

    /**
     * Constructs based on a given fileName.
     * 
     * @param fileName path/name of the data file
     */
    public SalesAnalysis(String fileName) {
        inputFile = fileName;
        weeklyTotal = new double[WEEKS_IN_MONTH];
        lineNumber = 0;
    }

    /**
     * Processes the data within inputFile.
     * 
     * @throws IOException TOCTOU for deletion or perms of inputFile
     */
    public void processFile() throws IOException {
        File fileIn = new File(inputFile);
        Scanner scanner = new Scanner(fileIn);

        while (scanner.hasNext())
            setArrayElement(scanner.nextLine().split(","));

        scanner.close();
    }

    /**
     * Stores the read data for each row of the input file.
     * 
     * @param inArray a row of sales data
     */
    private void setArrayElement(String[] inArray) {
        weeklyTotal[lineNumber] = 0.;

        for (String s : inArray)
            weeklyTotal[lineNumber] += Double.parseDouble(s);
        
        lineNumber++;
    }

    /**
     * Cleanly formats and prints the collected data.
     */
    public void writeOutput() {
        String str = "";

        double total = 0.;
        int high = 0, low = 0;

        for (int i = 0; i < WEEKS_IN_MONTH; i++) {
            /* Week# Summary */
            str += "Week"+(i+1)+" Info\n";
            str += "Total Sales: $%,.2f\n".formatted(weeklyTotal[i]);
            str += "Avg Daily Sales for Week: $%,.2f\n\n".formatted(weeklyTotal[i]/7);

            /* Statistics */
            total += weeklyTotal[i];
            if (weeklyTotal[i] > weeklyTotal[high])
                high = i;
            if (weeklyTotal[i] < weeklyTotal[low])
                low = i;
        }
        
        /* Statistics Summary */
        str += "Total Sales for all weeks: $%,.2f\n".formatted(total);
        str += "Avg Weekly Sales: $%,.2f\n".formatted(total/WEEKS_IN_MONTH);
        str += "Week"+(high+1)+" had the highest amount of sales\n";
        str += "Week"+(low+1)+" had the lowest amount of sales";

        System.out.println(str);
    }
}