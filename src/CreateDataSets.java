import java.io.File;
import java.io.FileWriter;

public class CreateDataSets {
    static int range[][] = {
            { 1, 500_000 },
            { 1, 1_000_000 },
            { 500_000, 1_000_000 },
            { 1, 2_000_000 },
            { 500_000, 2_000_000 },
            { 1_000_000, 2_000_000 },
            { 1, 3_000_000 },
            { 1, 4_000_000 },
            { 1, 5_000_000 },
            { 1, 10_000_000 } };

    public static void main(String[] args) {
        // create 10 datasets with 600'000 numbers
        for (int i = 0; i < 10; i++) {
            String dataSetFileName = "dataset" + i + ".txt";
            try {
                File file = new File(dataSetFileName);
                if (file.createNewFile()) {
                    System.out.println(dataSetFileName + " created successfully.");
                } else {
                    System.out.println(dataSetFileName + " already exists.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                System.exit(1);
            }

            try {
                FileWriter fileWriter = new FileWriter(dataSetFileName);
                for (int j = 0; j < 600000; j++) {
                    fileWriter.write(
                            "" + ((int) (Math.random() * (range[i][1] - range[i][0])) + range[i][0]) + "\n");
                }
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                System.exit(1);
            }

        }
    }
}
