package indexing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SequentialIndexing {
    private List<Integer> data;
    private String indexFilePath;

    public SequentialIndexing(String dirPath) {
        data = new ArrayList<>();
        indexFilePath = dirPath + File.separator + "index.txt";
        loadIndex();
    }

    public void insert(int value) {
        data.add(value);
        saveIndex();
    }

    public boolean delete(int value) {
        boolean isDeleted = data.remove(Integer.valueOf(value));
        if (isDeleted) {
            saveIndex();
        }
        return isDeleted;
    }

    public boolean search(int value) {
        return data.contains(value);
    }

    public void printAllValues() {
        if (data.isEmpty()) {
            System.out.println("No values found.");
            return;
        }

        System.out.println("Values stored in Sequential Indexing:");
        for (int value : data) {
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
                    data.add(Integer.parseInt(line));
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
            for (int value : data) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}