package DS_Lab_tests;

import java.util.ArrayList;

class User {
    private int user_id;
    private ArrayList<String> posts;

    public User(int user_id) {
        this.user_id = user_id;
        posts = new ArrayList<>();
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void post(String s) {
        posts.add(s);
    }

    public void getNewsFeed() {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.size() == 1) {
                System.out.print(posts.get(i));
            } else {
                System.out.println(posts.get(i));
            }
        }
    }
}

class X {
    User[] hash_table_of_users;

    public X() {
        hash_table_of_users = new User[1000];
    }

    void createUser(int user_id) {
        int index_to_be_added = linearProbing(user_id, hash_table_of_users);
        hash_table_of_users[index_to_be_added] = new User(user_id);
    }

    void post(int user_id, String text) {
        int index_of_user_in_hash = linearProbingRetrieveData(hash_table_of_users, user_id);
        hash_table_of_users[index_of_user_in_hash].post(text);
    }

    void getNewsFeed(int user_id) {
        int index_of_user_in_hash = linearProbingRetrieveData(hash_table_of_users, user_id);
        hash_table_of_users[index_of_user_in_hash].getNewsFeed();
    }

    static int linearProbing(int key, User[] hash_table) {
        int index = key % 100;
        if (hash_table[index] == null) {
            return index;
        } else {
            int temp = index;
            temp++;

            do {
                if (temp == index) break; // ---------- check again
                temp++;
                if (temp >= hash_table.length) temp = 0;

            } while (hash_table[temp] != null);

            return temp;
        }
    } // done for now

    static int linearProbingRetrieveData(User[] hash_table, int key) {
        int index = key % 100;
        if (hash_table[index].getUser_id() == key) {
            return index;
        } else {
            int temp = index;
            do {
                if (temp == index) break;
                temp++;
                if (temp >= hash_table.length) temp = 0;
            } while (hash_table[temp].getUser_id() != key);
            return temp;
        }
    } // working
}

class LT4 {
    public static void main(String[] args) {
        X x = new X();

        x.createUser(105);
        x.createUser(115);

        x.post(105, "Skipped the gym today.");
        x.post(105, "Just saw a cat staring a puddle.");
        x.post(115, "Big wins start with a small step.");

        x.getNewsFeed(105);
        System.out.println();
        x.getNewsFeed(115);
    }

} // DONE