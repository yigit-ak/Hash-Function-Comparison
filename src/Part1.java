import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        // TEST CASE 1: comparing different coefficients of quadratic hash
        try {
            File quadraticFile = new File("quadratic-results.txt");
            quadraticFile.createNewFile();
            FileWriter quadraticWriter = new FileWriter("quadratic-results.txt");
            for (int c1 = 1; c1 <= Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE; c1 += Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE
                    / 20) {
                for (int c2 = 1; c2 <= Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE; c2 += Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE
                        / 20) {
                    System.out.print("" + c1 + "\t" + c2);
                    quadraticWriter.write("" + c1 + "\t" + c2);
                    for (int datasetNo = 0; datasetNo < 10; datasetNo++) {
                        QuadraticProbingHashTable hashTable = new QuadraticProbingHashTable(
                                Constants.TABLE_SIZE, c1, c2);
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
                        quadraticWriter.write("\t" + (insertionTimeOfDataset / 600000));
                    }
                    System.out.print("\n");
                    quadraticWriter.write("\n");
                    quadraticWriter.flush();
                }
            }
            quadraticWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        /* TEST CASE 2: comparing different R values for double hashing */

        try {
            File doubleFile = new File("double-results.txt");
            doubleFile.createNewFile();
            FileWriter doubleWriter = new FileWriter("double-results.txt");
            for (int i = 0; i < 500; i++) {
                System.out.print("" + Constants.GREATEST_PRIMES_LESS_THAN_TABLE_SIZE[i] + "\t");
                doubleWriter.write("" + i + "\t");
                for (int datasetNo = 0; datasetNo < 10; datasetNo++) {
                    DoubleHashingHashTable double_hashTable = new DoubleHashingHashTable(
                            Constants.TABLE_SIZE,
                            Constants.GREATEST_PRIMES_LESS_THAN_TABLE_SIZE[i]);
                    double insertionTimeOfDataset = 0;
                    try {
                        File datasetFile = new File("datasets/dataset" + datasetNo + ".txt");
                        Scanner fileReader = new Scanner(datasetFile);
                        double startTime = System.nanoTime();
                        while (fileReader.hasNextInt()) {
                            double_hashTable.insert(fileReader.nextInt());
                        }
                        insertionTimeOfDataset = System.nanoTime() - startTime;
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        System.exit(1);
                    }

                    System.out.print("\t" + (insertionTimeOfDataset / 600000));
                    doubleWriter.write("\t" + (insertionTimeOfDataset / 600000));
                }
                System.out.print("\n");
                doubleWriter.write("\n");
                doubleWriter.flush();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        /* TEST CASE 3: */
        try {
            File cuckooFile = new File("cuckoo-results.txt");
            cuckooFile.createNewFile();
            FileWriter cuckooWriter = new FileWriter("cuckoo-results.txt");
            for (int i = 1; i <= 3; i++) {
                cuckooWriter.write("hash functions: 1 and " + i + "\n");
                CuckooHashingTable cuckooTable = new CuckooHashingTable(Constants.TABLE_SIZE, i);
                for (int datasetNo = 0; datasetNo < 10; datasetNo++) {
                    double insertionTimeOfDataset = 0;
                    try {
                        File datasetFile = new File("datasets/dataset" + datasetNo + ".txt");
                        Scanner fileReader = new Scanner(datasetFile);
                        double startTime = System.nanoTime();
                        while (fileReader.hasNextInt()) {
                            cuckooTable.insert(fileReader.nextInt());
                        }
                        insertionTimeOfDataset = System.nanoTime() - startTime;
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        System.exit(1);
                    }

                    System.out.print("\t" + (insertionTimeOfDataset / 600000));
                    cuckooWriter.write("\t" + (insertionTimeOfDataset / 600000));
                }
                System.out.print("\n");
                cuckooWriter.write("\n");
                cuckooWriter.write("unsuccessful insert per iteration:\t"
                        + (cuckooTable.getUnsuccessfulInsertNumber() / 10) + "\n");
                cuckooWriter.flush();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}