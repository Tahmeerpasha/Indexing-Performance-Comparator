package cli;
import indexing.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the directory path to store the index files: ");
        String dirPath = scanner.nextLine();
        SequentialIndexing sequentialIndexing = new SequentialIndexing(dirPath);
        boolean exit = false;
            while (!exit) {
                System.out.println("Indexing Operations:");
                System.out.println("1. Insert a value");
                System.out.println("2. Delete a value");
                System.out.println("3. Search for a value");
                System.out.println("4. Print all values");
                System.out.println("5. Back");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                System.out.println();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the value to insert: ");
                        int insertValue = scanner.nextInt();
                        sequentialIndexing.insert(insertValue);
                        System.out.println("Value inserted successfully.");
                        break;
                    case 2:
                        System.out.print("Enter the value to delete: ");
                        int deleteValue = scanner.nextInt();
                        boolean isDeleted = sequentialIndexing.delete(deleteValue);
                        if (isDeleted) {
                            System.out.println("Value deleted successfully.");
                        } else {
                            System.out.println("Value not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the value to search: ");
                        int searchValue = scanner.nextInt();
                        boolean isFound = sequentialIndexing.search(searchValue);
                        if (isFound) {
                            System.out.println("Value found.");
                        } else {
                            System.out.println("Value not found.");
                        }
                        break;
                    case 4:
                        sequentialIndexing.printAllValues();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                System.out.println();
            }
        new FileIndexingProject();

        scanner.close();

    }


    public static class FileIndexingProject {
        public FileIndexingProject() {
            main(null);
        }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the path of the file to index: ");
            String filePath = scanner.nextLine();
            List<cli.FileIndexingProject.IndexingTechnique> techniques = Arrays.asList(
                    new cli.FileIndexingProject.LinearIndexing(),
                    new cli.FileIndexingProject.BinaryIndexing(),
                    new cli.FileIndexingProject.HashIndexing()
//                new BPlusTreeIndexing(),
//                new TrieIndexing()
            );

            for (cli.FileIndexingProject.IndexingTechnique technique : techniques) {
                long startTime = System.nanoTime();

                cli.FileIndexingProject.Index index = technique.indexFile(filePath);
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
            cli.FileIndexingProject.Index indexFile(String filePath);

            void writeIndexToFile(cli.FileIndexingProject.Index index, String fileName);

            String getIndexingTechniqueName();

            String getIndexFileName();
        }

        static class LinearIndexing implements cli.FileIndexingProject.IndexingTechnique {
            @Override
            public cli.FileIndexingProject.Index indexFile(String filePath) {
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

                return new cli.FileIndexingProject.Index(index);
            }

            @Override
            public void writeIndexToFile(cli.FileIndexingProject.Index index, String fileName) {
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

        static class BinaryIndexing implements cli.FileIndexingProject.IndexingTechnique {
            @Override
            public cli.FileIndexingProject.Index indexFile(String filePath) {
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

                return new cli.FileIndexingProject.Index(index);
            }

            @Override
            public void writeIndexToFile(cli.FileIndexingProject.Index index, String fileName) {
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

        static class HashIndexing implements cli.FileIndexingProject.IndexingTechnique {
            @Override
            public cli.FileIndexingProject.Index indexFile(String filePath) {
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

                return new cli.FileIndexingProject.Index(index);
            }

            @Override
            public void writeIndexToFile(cli.FileIndexingProject.Index index, String fileName) {
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

        static class BPlusTreeIndexing implements cli.FileIndexingProject.IndexingTechnique {
            @Override
            public cli.FileIndexingProject.Index indexFile(String filePath) {
                // Implement B+ Tree indexing logic here
                // This is a placeholder implementation
                System.out.println("B+ Tree indexing not implemented.");
                return new cli.FileIndexingProject.Index(new HashMap<>());
            }

            @Override
            public void writeIndexToFile(cli.FileIndexingProject.Index index, String fileName) {
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
                return "plus_index.txt";
            }
        }

        static class TrieIndexing implements cli.FileIndexingProject.IndexingTechnique {
            @Override
            public cli.FileIndexingProject.Index indexFile(String filePath) {
                // Implement Trie indexing logic here
                // This is a placeholder implementation
                System.out.println("Trie indexing not implemented.");
                return new cli.FileIndexingProject.Index(new HashMap<>());
            }

            @Override
            public void writeIndexToFile(cli.FileIndexingProject.Index index, String fileName) {
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


}
