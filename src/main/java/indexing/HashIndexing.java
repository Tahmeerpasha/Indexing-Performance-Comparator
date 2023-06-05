package indexing;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HashIndexing {
    private Map<Integer, Boolean> index;
    private String indexFilePath;

    public HashIndexing(String dirPath) {
        index = new HashMap<>();
        indexFilePath = dirPath + File.separator + "index.txt";
        loadIndex();
    }

    public void insert(int value) {
        index.put(value, true);
        saveIndex();
    }

    public boolean delete(int value) {
        boolean isDeleted = index.remove(value) != null;
        if (isDeleted) {
            saveIndex();
        }
        return isDeleted;
    }

    public boolean search(int value) {
        return index.containsKey(value);
    }

    public void printAllValues() {
        System.out.println("Values stored in Hash Indexing:");
        for (int value : index.keySet()) {
            System.out.println(value);
        }
    }

    private void loadIndex() {
        try {
            File file = new File(indexFilePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    int value = Integer.parseInt(line);
                    index.put(value, true);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveIndex() {
        try {
            File file = new File(indexFilePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int value : index.keySet()) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}