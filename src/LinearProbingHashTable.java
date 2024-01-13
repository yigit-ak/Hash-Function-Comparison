public class LinearProbingHashTable {
    private int capacity;
    private int[] table;
    private boolean[] isOccupied;

    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new int[capacity];
        this.isOccupied = new boolean[capacity];
    }

    private int hashFunction(int key) {
        return key % capacity;
    }

    public void insert(int key) {
        int index = hashFunction(key);

        while (isOccupied[index]) {
            // Çakışma durumu: bir sonraki boş hücreyi bulana kadar devam et
            index = (index + 1) % capacity;
        }

        // Veriyi tabloya ekle
        table[index] = key;
        isOccupied[index] = true;
    }

    public boolean search(int key) {
        int index = hashFunction(key);

        while (isOccupied[index]) {
            if (table[index] == key) {
                // Aranan değeri bulduk
                return true;
            }

            // Çakışma durumu: bir sonraki hücreye git
            index = (index + 1) % capacity;
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
