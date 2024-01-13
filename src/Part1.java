import java.io.File;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        final int TABLE_SIZE = 1_000_000;
        final int GREATEST_PRIME_LESS_THAN_TABLE_SIZE = 999_983;
        /*
         * TEST CASE 1: comparing different coefficient values of quadratic hash
         * functions
         */

        try {
            for (int c1 = 1; c1 <= GREATEST_PRIME_LESS_THAN_TABLE_SIZE; c1 += GREATEST_PRIME_LESS_THAN_TABLE_SIZE
                    / 10) {
                for (int c2 = 1; c2 <= GREATEST_PRIME_LESS_THAN_TABLE_SIZE; c2 += GREATEST_PRIME_LESS_THAN_TABLE_SIZE
                        / 10) {
                    System.out.print("" + c1 + "\t" + c2);
                    for (int datasetNo = 0; datasetNo < 10; datasetNo++) {
                        QuadraticProbingHashTable hashTable = new QuadraticProbingHashTable(
                                TABLE_SIZE, c1, c2);
                        double insertionTimeOfDataset = 0;
                        try {
                            File datasetFile = new File("datasets/dataset" + datasetNo + ".txt");
                            Scanner fileReader = new Scanner(datasetFile);
                            double startTime = System.nanoTime();
                            while (fileReader.hasNextInt()) {
                                hashTable.insert(fileReader.nextInt());
                            }
                            insertionTimeOfDataset = System.nanoTime() - startTime;
                        } catch (Exception e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                            System.exit(1);
                        }

                        System.out.print("\t" + (insertionTimeOfDataset / 600000));
                    }
                    System.out.print("\n");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        /* TEST CASE 2: comparing different R values for double hashing */

        /* TEST CASE 3: */
    }
}