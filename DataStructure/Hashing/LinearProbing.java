package DataStructure.Hashing;

class LinearProbing {

    static void linearProbing(int[] arr, int[] hash_table) {
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] % 10;
            if (hash_table[index] == 0) {
                hash_table[index] = arr[i];

            } else {
                int temp = index;
                temp++;

                do {

                    if (temp == index) break;
                    temp++;
                    if (temp >= hash_table.length) temp = 0;

                } while (hash_table[temp] != 0);

                hash_table[temp] = arr[i];
            }

            System.out.println();
            for (int j = 0; j < hash_table.length; j++) {
                System.out.print(hash_table[j] + " ");
            }
        }
    } // DONE

    static void linearProbingRetrieveData(int[] hash_table, int key) {
        for (int i = 0; i < hash_table.length; i++) {
            int index = key % 10;
            if (hash_table[index] == key) {
                System.out.println();
            }
        }
    } // pending

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 20, 8, 9, 11, 13, 15, 16, 18};
        int[] hash_table = new int[10];

        linearProbing(arr, hash_table);
    }
}
