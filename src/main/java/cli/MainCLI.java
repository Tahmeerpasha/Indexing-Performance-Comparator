package cli;
import indexing.*;
import java.util.*;
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
                    case 1 -> {
                        System.out.print("Enter the value to insert: ");
                        int insertValue = scanner.nextInt();
                        sequentialIndexing.insert(insertValue);
                        System.out.println("Value inserted successfully.");
                    }
                    case 2 -> {
                        System.out.print("Enter the value to delete: ");
                        int deleteValue = scanner.nextInt();
                        boolean isDeleted = sequentialIndexing.delete(deleteValue);
                        if (isDeleted) {
                            System.out.println("Value deleted successfully.");
                        } else {
                            System.out.println("Value not found.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter the value to search: ");
                        int searchValue = scanner.nextInt();
                        boolean isFound = sequentialIndexing.search(searchValue);
                        if (isFound) {
                            System.out.println("Value found.");
                        } else {
                            System.out.println("Value not found.");
                        }
                    }
                    case 4 -> sequentialIndexing.printAllValues();
                    case 5 -> exit = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            }
        new FileIndexingProject();

        scanner.close();

    }
}
