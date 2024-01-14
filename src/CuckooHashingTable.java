public class CuckooHashingTable {
    private int capacity;
    private int secondHashFunctionNo;
    private int[] table1;
    private boolean[] isOccupied1;
    private int[] table2;
    private boolean[] isOccupied2;
    private int unsuccessfulInsertNumber; // represents the number of cycles in cuckoo
    private int initialIndex; // will be used to detect a cycle

    public CuckooHashingTable(int capacity, int secondHashFunctionNo) {
        this.capacity = capacity;
        this.table1 = new int[capacity];
        this.isOccupied1 = new boolean[capacity];
        this.table2 = new int[capacity];
        this.isOccupied2 = new boolean[capacity];
        this.secondHashFunctionNo = secondHashFunctionNo;
        this.unsuccessfulInsertNumber = 0;
        this.initialIndex = -1;
    }

    // for this experiment the first hash function for each sets, will be
    // hashFunction1
    private int hashFunction1(int key) {
        return key % capacity;
    }

    // h2(k) = k * P % M
    // P is the greatest prime number smaller than the table size
    private int hashFunction2(int key) {
        int index = (key * Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE) % capacity;

        // index may be too large that it can exceed the limits of an integer
        if (index < 0)
            index += capacity;

        return index;
    }

    // h2(k) = k + P % M
    // P is the greatest prime number smaller than the table size
    private int hashFunction3(int key) {
        int index = (key + Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE) % capacity;

        // index may be too large that it can exceed the limits of an integer
        if (index < 0)
            index += capacity;

        return index;
    }

    public void insert(int key) {
        // memorize the first index to detect a cycle
        initialIndex = hashFunction1(key);

        // initially try to insert the key by using 1st hash function
        insertToTable1(key);
    }

    private void insertToTable1(int key) {
        int index = hashFunction1(key);

        if (isOccupied1[index]) {
            // on collision remove the key from index
            int removedKey = table1[index];

            // put the current key in index
            table1[index] = key;

            // find a place for removed key in the other table
            insertToTable2(removedKey);

            return;
        }

        table1[index] = key;
        isOccupied1[index] = true;
    }

    public void insertToTable2(int key) {
        int index = -1; // just a dummy value for index

        // insert to the 2nd table according to selected method in constructor
        switch (secondHashFunctionNo) {
            case 1:
                index = hashFunction1(key);
                break;
            case 2:
                index = hashFunction2(key);
                break;
            case 3:
                index = hashFunction3(key);
                break;
        }

        if (isOccupied2[index]) {
            // on collision remove the key from index
            int removedKey = table2[index];

            // put the current key in index
            table2[index] = key;

            // check if there is a cycle
            if (initialIndex == hashFunction1(removedKey)) {
                // when cycle occurs, don't insert the key
                unsuccessfulInsertNumber++;

                return;
            }

            insertToTable1(removedKey);

            return;
        }

        table2[index] = key;
        isOccupied2[index] = true;
    }

    public int getUnsuccessfulInsertNumber() {
        return unsuccessfulInsertNumber;
    }

}
