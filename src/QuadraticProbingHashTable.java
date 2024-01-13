public class QuadraticProbingHashTable {
    private int capacity;
    private int[] table;
    private boolean[] isOccupied;
    private int c1;
    private int c2;

    public QuadraticProbingHashTable(int capacity, int c1, int c2) {
        this.capacity = capacity;
        this.table = new int[capacity];
        this.isOccupied = new boolean[capacity];
        this.c1 = c1;
        this.c2 = c2;
    }

    private int hashFunction(int key) {
        return (capacity + key) % capacity;
    }

    public void insert(int key) {
        int index = hashFunction(key);
        int i = 0;

        while (isOccupied[index]) {
            // Çakışma durumu: Quadratic probing kullanarak bir sonraki hücreyi bulana kadar
            // devam et
            System.out.println("COLLUSION! " + key + "=" + index + ", " + table[index]);
            index = (index + c1 * i + c2 * i * i) % capacity;
            i++;
        }

        // Veriyi tabloya ekle
        table[index] = key;
        isOccupied[index] = true;
    }

    public boolean search(int key) {
        int index = hashFunction(key);
        int i = 1;

        while (isOccupied[index]) {
            if (table[index] == key) {
                // Aranan değeri bulduk
                return true;
            }

            // Çakışma durumu: Quadratic probing kullanarak bir sonraki hücreye git
            index = (index + c1 * i + c2 * i * i) % capacity;
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
