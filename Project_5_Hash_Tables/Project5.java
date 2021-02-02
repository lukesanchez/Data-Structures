/**
 * COP 3530: Project 5 – Hash Tables
 * <p>
 * Project5 creates a hash table that uses linked list for separate chaining.
 * With a Hash Table created, a CSV file can be parsed to ingest the country data
 * Once every is inserted into the table based on it's hash, the table can be modified
 * Countries in the table can be displayed, deleted, or found.
 * The number of free and collided cells can also be displayed.
 * <p>
 * @author Luke Sanchez
 * @version Dec. 4, 2020
 */

import java.io.*;

public class Project5 {

    public static void main(String[] args) throws IOException {

        HashTable nationsTable = new HashTable();
        parseCSV(nationsTable); //adds countries to hash table

        nationsTable.display();

        nationsTable.delete("Australia", "Canberra");
        nationsTable.delete("Tunisia", "Tunis");
        nationsTable.delete("Norway", "Oslo");
        System.out.println();

        nationsTable.find("Sri Lanka", "Colombo");
        nationsTable.find("Cyprus", "Nicosia");
        nationsTable.find("Tunisia", "Tunis");
        System.out.println();

        nationsTable.delete("Malawi", "Lilongwe");
        nationsTable.delete("Germany", "Berlin");
        nationsTable.delete("Ireland", "Dublin");
        nationsTable.delete("Yemen", "Sanaa");
        nationsTable.delete("India", "New Delhi");

        nationsTable.display();

        nationsTable.printFreeAndCollisions();

    }//end main

    /**
     * The parseCSV method parses a CSV file to extract Country data.
     * A input CSV file needs to be specified to extract the data.
     * A bufferedReader object is created to hold the CSV data.
     * For every line in the file the country data is extracted.
     * Country name, capital, and CFR are extracted from the CSV file.
     * The Country data is then inserted into the hash table.
     *
     * @param nationsTable HashTable that was created in main.
     * @throws IOException if file is not found.
     */
    public static void parseCSV(HashTable nationsTable) throws IOException {

        File inputFile = new File("Countries5.csv"); //pathname of CSV file

        if (!inputFile.exists())
            System.out.println("\nThat file does not exist!");

        System.out.println("Importing data from " + inputFile.toString() + "...");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        br.readLine(); //skips first line of file.
        String line;
        int numOfRecords = 0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String name = values[0];
            String capital = values[1];
            String c19Cases = values[4];
            String c19Deaths = values[5];
            double c19CFR;
            numOfRecords++;
            if (Integer.parseInt(c19Deaths) <= 0)
                c19CFR = 0.0;
            else
                c19CFR = Integer.parseInt(c19Deaths) / Double.parseDouble(c19Cases);

            nationsTable.insert(name, capital, c19CFR);
        }
        br.close();
        System.out.printf("There were %d country records read into the hash table\n", numOfRecords);
    } //end parseCSV
}//end Project5
