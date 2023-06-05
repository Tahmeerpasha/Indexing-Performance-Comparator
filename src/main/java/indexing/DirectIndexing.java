
package indexing;

import java.io.*;
import java.util.Arrays;

public class DirectIndexing {
    private static final int MAX_VALUE = 1000; // Adjust the maximum value based on your requirements
    private boolean[] index;
    private String indexFilePath;

    public DirectIndexing(String dirPath) {
        index = new boolean[MAX_VALUE + 1];
        Arrays.fill(index, false);
        indexFilePath = dirPath + File.separator + "index.dat";
        loadIndex();
    }

    public void insert(int value) {
        index[value] = true;
        saveIndex();
    }

    public boolean delete(int value) {
        boolean isDeleted = index[value];
        index[value] = false;
        if (isDeleted) {
            saveIndex();
        }
        return isDeleted;
    }

    public boolean search(int value) {
        return index[value];
    }

    public void printAllValues() {
        System.out.println("Values stored in Direct Indexing:");
        for (int i = 0; i <= MAX_VALUE; i++) {
            if (index[i]) {
                System.out.println(i);
            }
        }
    }

    private void loadIndex() {
        try {
            File file = new File(indexFilePath);
            if (file.exists()) {
                DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
                for (int i = 0; i <= MAX_VALUE; i++) {
                    index[i] = inputStream.readBoolean();
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveIndex() {
        try {
            File file = new File(indexFilePath);
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
            for (int i = 0; i <= MAX_VALUE; i++) {
                outputStream.writeBoolean(index[i]);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}