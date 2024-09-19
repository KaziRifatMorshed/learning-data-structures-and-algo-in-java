package DataStracture.Array;

import java.util.Random;

public class DeleteRandomEntry {
    private Integer[] arr;

    void RandomDelete() {
//        get array element count
        int element_count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) element_count++;
        }
        Random random = new Random();
        while (true) {
            int r = random.nextInt(arr.length);
            if (arr[r] != null) {
                arr[r] = null;
                element_count--;
            }
            if (element_count == 0) break;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 20; i++){
            System.out.println(random.nextInt(6)); // 6 won't be touched
        }
    }
}
