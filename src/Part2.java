import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {

        File quadraticFile = new File("part-2-results.txt");
        // TEST CASE 1: comparing different coefficients of linear probing hash
        try {
            quadraticFile.createNewFile();
            FileWriter quadraticWriter = new FileWriter("part-2-results.txt");
            System.out.print("Linear Probing Result: ");
            // quadraticWriter.write("" + c1 + "\t" + c2);

            LinearProbingHashTable hashTable = new LinearProbingHashTable(Constants.TABLE_SIZE);
            double insertionTimeOfDataset = 0;
            try {
                File datasetFile = new File("datasets/unique_numbers.txt");
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
            quadraticWriter.write("Linear Probing Result: " + (insertionTimeOfDataset / 600000));
            System.out.print("\n");
            quadraticWriter.write("\n");
            quadraticWriter.flush();
            quadraticWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        // TEST CASE 2: comparing different coefficients of quadratic hash
        try {
            int c1 = 349994;
            int c2 = 399993;
            // File quadraticFile = new File("quadratic-results.txt");
            quadraticFile.createNewFile();
            FileWriter quadraticWriter = new FileWriter("part-2-results.txt", true);
            System.out.print("Quadratic Probing Result for c1=349994 and c2=399993: ");
            // quadraticWriter.write("" + c1 + "\t" + c2);

            QuadraticProbingHashTable hashTable = new QuadraticProbingHashTable(
                    Constants.TABLE_SIZE, c1, c2);
            double insertionTimeOfDataset = 0;
            try {
                File datasetFile = new File("datasets/unique_numbers.txt");
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
            quadraticWriter.write("Quadratic Probing Result for c1=349994 and c2=399993: " + (insertionTimeOfDataset / 600000));
            System.out.print("\n");
            quadraticWriter.write("\n");
            quadraticWriter.flush();
            quadraticWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }



        /* TEST CASE 3: comparing different R values for double hashing */
        try {
            // File doubleFile = new File("double-results.txt");
            // quadraticFile.createNewFile();

            int R = 999727;

            FileWriter doubleWriter = new FileWriter("part-2-results.txt", true);

            System.out.print("Double Hashing Result for R=999727: ");
            doubleWriter.write("Double Hashing Result for R=999727: ");

            DoubleHashingHashTable double_hashTable = new DoubleHashingHashTable(
                    Constants.TABLE_SIZE, R);
            double insertionTimeOfDataset = 0;
            try {
                File datasetFile = new File("datasets/unique_numbers.txt");
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

            System.out.print("\n");
            doubleWriter.write("\n");
            doubleWriter.flush();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        /* TEST CASE 4: */
        try {
            // File cuckooFile = new File("cuckoo-results.txt");
            // cuckooFile.createNewFile();
            FileWriter cuckooWriter = new FileWriter("part-2-results.txt", true);

            cuckooWriter.write("Cuckoo Hashing Result for hash functions 1 and 2: ");
            CuckooHashingTable cuckooTable = new CuckooHashingTable(Constants.TABLE_SIZE, 2);

            double insertionTimeOfDataset = 0;
            try {
                File datasetFile = new File("datasets/unique_numbers.txt");
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

            System.out.print("Cuckoo Hashing Result for hash functions 1 and 2:\t" + (insertionTimeOfDataset / 600000));
            cuckooWriter.write("\t" + (insertionTimeOfDataset / 600000));

            System.out.print("\n");
            cuckooWriter.write("\n");
            cuckooWriter.write("unsuccessful insert per iteration:\t"
                    + (cuckooTable.getUnsuccessfulInsertNumber() / 10) + "\n");
            cuckooWriter.flush();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
