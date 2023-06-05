package indexing;

import java.io.*;
import java.util.*;

public class Indexing {
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
    public class BinaryIndexing {
        private List<Integer> data;
        private String indexFilePath;

        public BinaryIndexing(String dirPath) {
            data = new ArrayList<>();
            indexFilePath = dirPath + File.separator + "index.txt";
            loadIndex();
        }

        public void insert(int value) {
            int index = Collections.binarySearch(data, value);
            if (index < 0) {
                index = -(index + 1);
            }
            data.add(index, value);
            saveIndex();
        }

        public boolean delete(int value) {
            int index = Collections.binarySearch(data, value);
            if (index >= 0) {
                data.remove(index);
                saveIndex();
                return true;
            }
            return false;
        }

        public boolean search(int value) {
            int index = Collections.binarySearch(data, value);
            return index >= 0;
        }

        public void printAllValues() {
            if (data.isEmpty()) {
                System.out.println("No values found.");
                return;
            }

            System.out.println("Values stored in Binary Indexing:");
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
}
