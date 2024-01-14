public class CuckooHashingTable {
    private int capacity;
    private int secondHashFunctionNo;
    private int[] table1;
    private boolean[] isOccupied1;
    private int[] table2;
    private boolean[] isOccupied2;
    private int unsuccessfulInsertNumber;
    private int initialIndex;

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

    private int hashFunction1(int key) {
        return key % capacity;
    }

    private int hashFunction2(int key) {
        int index = (key * Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE) % capacity;
        if (index < 0)
            index += capacity;
        return index;
    }

    private int hashFunction3(int key) {
        int index = (key + Constants.GREATEST_PRIME_LESS_THAN_TABLE_SIZE) % capacity;
        if (index < 0)
            index += capacity;
        return index;
    }

    public void insert(int key) {
        initialIndex = hashFunction1(key);
        insertToTable1(key);
    }

    private void insertToTable1(int key) {
        int index = hashFunction1(key);
        if (isOccupied1[index]) {
            int removedKey = table1[index];
            isOccupied1[index] = false;
            insertToTable2(removedKey);
            return;
        }
        table1[index] = key;
        isOccupied1[index] = true;
    }

    public void insertToTable2(int key) {
        int index = -1;

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
            int removedKey = table2[index];
            if (initialIndex == hashFunction1(removedKey)) {
                unsuccessfulInsertNumber++;
                return;
            }
            isOccupied2[index] = false;
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
