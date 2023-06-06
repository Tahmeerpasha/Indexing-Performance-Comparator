
package cli;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class FileIndexingProject {
    public FileIndexingProject() {
        main(null);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the file to index: ");
        String filePath = scanner.nextLine();
        List<IndexingTechnique> techniques = Arrays.asList(
                new LinearIndexing(),
                new BinaryIndexing(),
                new HashIndexing()
//                new BPlusTreeIndexing(),
//                new TrieIndexing()
        );

        for (IndexingTechnique technique : techniques) {
            long startTime = System.nanoTime();

            Index index = technique.indexFile(filePath);
            technique.writeIndexToFile(index, technique.getIndexFileName());

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.println(technique.getIndexingTechniqueName() + " indexing completed.");
            System.out.println("Time taken: " + elapsedTime + " nanoseconds");
            System.out.println();
        }

        scanner.close();
    }

    interface IndexingTechnique {
        Index indexFile(String filePath);

        void writeIndexToFile(Index index, String fileName);

        String getIndexingTechniqueName();

        String getIndexFileName();
    }

    static class LinearIndexing implements IndexingTechnique {
        @Override
        public Index indexFile(String filePath) {
            List<String> lines = readLinesFromFile(filePath);
            Map<String, List<Integer>> index = new HashMap<>();

            for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
                String line = lines.get(lineNumber);
                String[] words = line.split("\\s+");

                for (String word : words) {
                    List<Integer> lineNumbers = index.getOrDefault(word, new ArrayList<>());
                    lineNumbers.add(lineNumber);
                    index.put(word, lineNumbers);
                }
            }

            return new Index(index);
        }

        @Override
        public void writeIndexToFile(Index index, String fileName) {
            try (PrintWriter writer = new PrintWriter(fileName)) {
                for (Map.Entry<String, List<Integer>> entry : index.getEntries()) {
                    String word = entry.getKey();
                    List<Integer> lineNumbers = entry.getValue();

                    writer.println(word + ": " + lineNumbers);
                }
            } catch (IOException e) {
                System.out.println("Error writing index to file: " + e.getMessage());
            }
        }

        @Override
        public String getIndexingTechniqueName() {
            return "Linear";
        }

        @Override
        public String getIndexFileName() {
            return "linear_index.txt";
        }
    }

    static class BinaryIndexing implements IndexingTechnique {
        @Override
        public Index indexFile(String filePath) {
            List<String> lines = readLinesFromFile(filePath);
            Map<String, List<Integer>> index = new TreeMap<>();

            for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
                String line = lines.get(lineNumber);
                String[] words = line.split("\\s+");

                for (String word : words) {
                    List<Integer> lineNumbers = index.getOrDefault(word, new ArrayList<>());
                    lineNumbers.add(lineNumber);
                    index.put(word, lineNumbers);
                }
            }

            return new Index(index);
        }

        @Override
        public void writeIndexToFile(Index index, String fileName) {
            try (PrintWriter writer = new PrintWriter(fileName)) {
                for (Map.Entry<String, List<Integer>> entry : index.getEntries()) {
                    String word = entry.getKey();
                    List<Integer> lineNumbers = entry.getValue();

                    writer.println(word + ": " + lineNumbers);
                }
            } catch (IOException e) {
                System.out.println("Error writing index to file: " + e.getMessage());
            }
        }

        @Override
        public String getIndexingTechniqueName() {
            return "Binary";
        }

        @Override
        public String getIndexFileName() {
            return "binary_index.txt";
        }
    }

    static class HashIndexing implements IndexingTechnique {
        @Override
        public Index indexFile(String filePath) {
            List<String> lines = readLinesFromFile(filePath);
            Map<String, List<Integer>> index = new HashMap<>();

            for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
                String line = lines.get(lineNumber);
                String[] words = line.split("\\s+");

                for (String word : words) {
                    List<Integer> lineNumbers = index.getOrDefault(word, new ArrayList<>());
                    lineNumbers.add(lineNumber);
                    index.put(word, lineNumbers);
                }
            }

            return new Index(index);
        }

        @Override
        public void writeIndexToFile(Index index, String fileName) {
            try (PrintWriter writer = new PrintWriter(fileName)) {
                for (Map.Entry<String, List<Integer>> entry : index.getEntries()) {
                    String word = entry.getKey();
                    List<Integer> lineNumbers = entry.getValue();

                    writer.println(word + ": " + lineNumbers);
                }
            } catch (IOException e) {
                System.out.println("Error writing index to file: " + e.getMessage());
            }
        }

        @Override
        public String getIndexingTechniqueName() {
            return "Hash";
        }

        @Override
        public String getIndexFileName() {
            return "hash_index.txt";
        }
    }

    static class BPlusTreeIndexing implements IndexingTechnique {
        @Override
        public Index indexFile(String filePath) {
            // Implement B+ Tree indexing logic here
            // This is a placeholder implementation
            System.out.println("B+ Tree indexing not implemented.");
            return new Index(new HashMap<>());
        }

        @Override
        public void writeIndexToFile(Index index, String fileName) {
            // Implement index writing logic here
            // This is a placeholder implementation
            System.out.println("B+ Tree index writing not implemented.");
        }

        @Override
        public String getIndexingTechniqueName() {
            return "B+ Tree";
        }

        @Override
        public String getIndexFileName() {
            return "bplus_index.txt";
        }
    }

    static class TrieIndexing implements IndexingTechnique {
        @Override
        public Index indexFile(String filePath) {
            // Implement Trie indexing logic here
            // This is a placeholder implementation
            System.out.println("Trie indexing not implemented.");
            return new Index(new HashMap<>());
        }

        @Override
        public void writeIndexToFile(Index index, String fileName) {
            // Implement index writing logic here
            // This is a placeholder implementation
            System.out.println("Trie index writing not implemented.");
        }

        @Override
        public String getIndexingTechniqueName() {
            return "Trie";
        }

        @Override
        public String getIndexFileName() {
            return "trie_index.txt";
        }
    }

    static class Index {
        private final Map<String, List<Integer>> index;

        public Index(Map<String, List<Integer>> index) {
            this.index = index;
        }

        public List<Map.Entry<String, List<Integer>>> getEntries() {
            return new ArrayList<>(index.entrySet());
        }
    }

    private static List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

