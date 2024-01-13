import java.io.File;
import java.util.Scanner;

public class DoubleHashingHashTable {
    private int capacity;
    private int[] table;
    private boolean[] isOccupied;
    private int R;

    public DoubleHashingHashTable(int capacity, int R) {
        this.capacity = capacity;
        this.table = new int[capacity];
        this.isOccupied = new boolean[capacity];
        this.R = R;
    }

    private int hashFunction1(int key) {
        return key % capacity;
    }

    private int hashFunction2(int key) {
        return R - (key % R);
    }

    public void insert(int key) {
        int index = hashFunction1(key);
        int step = hashFunction2(key);
        int i = 0;
        while (isOccupied[index]) {
            // Çakışma durumu: Double hashing kullanarak bir sonraki hücreyi bulana kadar
            // devam et
            index = (index + i * step) % capacity;
            if (index < 0) {
                index += capacity;
            }
            i++;
        }

        // Veriyi tabloya ekle
        table[index] = key;
        isOccupied[index] = true;
    }

    public boolean search(int key) {
        int index = hashFunction1(key);
        int step = hashFunction2(key);
        int i = 0;
        while (isOccupied[index]) {
            if (table[index] == key) {
                // Aranan değeri bulduk
                return true;
            }

            // Çakışma durumu: Double hashing kullanarak bir sonraki hücreye git
            index = (index + i * step) % capacity;
            i++;
        }

        // Aranan değer tabloda bulunamadı
        return false;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (isOccupied[i]) {
                System.out.println("Index " + i + ": " + table[i]);
            } else {
                System.out.println("Index " + i + ": boş");
            }
        }
    }
}
