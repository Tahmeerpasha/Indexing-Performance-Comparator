package cli;

import indexing.*;

import java.io.File;
import java.util.Scanner;

public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean performComparison = false;

        SequentialIndexing sequentialIndexing = null;
        DirectIndexing directIndexing = null;
        BinaryIndexing binaryIndexing = null;
        HashIndexing hashIndexing = null;

        while (true) {
            System.out.println("Indexing Techniques:");
            System.out.println("1. Sequential Indexing");
            System.out.println("2. Direct Indexing");
            System.out.println("3. Binary Indexing");
            System.out.println("4. Hash Indexing");
            System.out.println("5. Compare Performance");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            if (choice == 6) {
                break;
            }

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please try again.");
                System.out.println();
                continue;
            }

            if (choice == 5) {
                if (sequentialIndexing != null && directIndexing != null &&
                        binaryIndexing != null && hashIndexing != null) {
                    performComparison = true;
                } else {
                    System.out.println("All indexing techniques must be selected to compare performance.");
                    System.out.println();
                    continue;
                }
            } else {
                if (performComparison) {
                    System.out.println("Cannot select other indexing techniques after comparison. Please select Compare Performance or Exit.");
                    System.out.println();
                    continue;
                }
            }

            System.out.println();
            System.out.println("Indexing Technique: " + choice);
            System.out.println("==========================================");

            switch (choice) {
                case 1:
                    if (sequentialIndexing == null) {
                        System.out.print("Enter the directory path for Sequential Indexing: ");
                        String sequentialDirPath = scanner.next();
                        sequentialIndexing = new SequentialIndexing(sequentialDirPath);
                    }
                    handleIndexingOperations(sequentialIndexing);
                    break;
                case 2:
                    if (directIndexing == null) {
                        System.out.print("Enter the directory path for Direct Indexing: ");
                        String directDirPath = scanner.next();
                        directIndexing = new DirectIndexing(directDirPath);
                    }
                    handleIndexingOperations(directIndexing);
                    break;
                case 3:
                    if (binaryIndexing == null) {
                        System.out.print("Enter the directory path for Binary Indexing: ");
                        String binaryDirPath = scanner.next();
                        binaryIndexing = new BinaryIndexing(binaryDirPath);
                    }
                    handleIndexingOperations(binaryIndexing);
                    break;
                case 4:
                    if (hashIndexing == null) {
                        System.out.print("Enter the directory path for Hash Indexing: ");
                        String hashDirPath = scanner.next();
                        hashIndexing = new HashIndexing(hashDirPath);
                    }
                    handleIndexingOperations(hashIndexing);
                    break;
                case 5:
                    comparePerformance(sequentialIndexing, directIndexing, binaryIndexing, hashIndexing);
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
    private static void handleIndexingOperations(DirectIndexing indexing) {
        Scanner scanner = new Scanner(System.in);
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
                    indexing.insert(insertValue);
                    System.out.println("Value inserted successfully.");
                    break;
                case 2:
                    System.out.print("Enter the value to delete: ");
                    int deleteValue = scanner.nextInt();
                    boolean isDeleted = indexing.delete(deleteValue);
                    if (isDeleted) {
                        System.out.println("Value deleted successfully.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the value to search: ");
                    int searchValue = scanner.nextInt();
                    boolean isFound = indexing.search(searchValue);
                    if (isFound) {
                        System.out.println("Value found.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 4:
                    indexing.printAllValues();
                    break;
                case 5:
                    exit = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
    private static void handleIndexingOperations(BinaryIndexing indexing) {
        Scanner scanner = new Scanner(System.in);
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
                    indexing.insert(insertValue);
                    System.out.println("Value inserted successfully.");
                    break;
                case 2:
                    System.out.print("Enter the value to delete: ");
                    int deleteValue = scanner.nextInt();
                    boolean isDeleted = indexing.delete(deleteValue);
                    if (isDeleted) {
                        System.out.println("Value deleted successfully.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the value to search: ");
                    int searchValue = scanner.nextInt();
                    boolean isFound = indexing.search(searchValue);
                    if (isFound) {
                        System.out.println("Value found.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 4:
                    indexing.printAllValues();
                    break;
                case 5:
                    exit = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
    private static void handleIndexingOperations(HashIndexing indexing) {
        Scanner scanner = new Scanner(System.in);
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
                    indexing.insert(insertValue);
                    System.out.println("Value inserted successfully.");
                    break;
                case 2:
                    System.out.print("Enter the value to delete: ");
                    int deleteValue = scanner.nextInt();
                    boolean isDeleted = indexing.delete(deleteValue);
                    if (isDeleted) {
                        System.out.println("Value deleted successfully.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the value to search: ");
                    int searchValue = scanner.nextInt();
                    boolean isFound = indexing.search(searchValue);
                    if (isFound) {
                        System.out.println("Value found.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 4:
                    indexing.printAllValues();
                    break;
                case 5:
                    exit = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
    private static void handleIndexingOperations(SequentialIndexing indexing) {
        Scanner scanner = new Scanner(System.in);
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
                    indexing.insert(insertValue);
                    System.out.println("Value inserted successfully.");
                    break;
                case 2:
                    System.out.print("Enter the value to delete: ");
                    int deleteValue = scanner.nextInt();
                    boolean isDeleted = indexing.delete(deleteValue);
                    if (isDeleted) {
                        System.out.println("Value deleted successfully.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the value to search: ");
                    int searchValue = scanner.nextInt();
                    boolean isFound = indexing.search(searchValue);
                    if (isFound) {
                        System.out.println("Value found.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 4:
                    indexing.printAllValues();
                    break;
                case 5:
                    exit = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
    private static void comparePerformance(SequentialIndexing sequentialIndexing, DirectIndexing directIndexing,
                                           BinaryIndexing binaryIndexing, HashIndexing hashIndexing) {
        long sequentialStartTime = System.currentTimeMillis();
        // Perform sequential indexing operations here
        long sequentialEndTime = System.currentTimeMillis();
        long sequentialExecutionTime = sequentialEndTime - sequentialStartTime;
        System.out.println("Sequential Indexing Execution Time: " + sequentialExecutionTime + " ms");

        long directStartTime = System.currentTimeMillis();
        // Perform direct indexing operations here
        long directEndTime = System.currentTimeMillis();
        long directExecutionTime = directEndTime - directStartTime;
        System.out.println("Direct Indexing Execution Time: " + directExecutionTime + " ms");

        long binaryStartTime = System.currentTimeMillis();
        // Perform binary indexing operations here
        long binaryEndTime = System.currentTimeMillis();
        long binaryExecutionTime = binaryEndTime - binaryStartTime;
        System.out.println("Binary Indexing Execution Time: " + binaryExecutionTime + " ms");

        long hashStartTime = System.currentTimeMillis();
        // Perform hash indexing operations here
        long hashEndTime = System.currentTimeMillis();
        long hashExecutionTime = hashEndTime - hashStartTime;
        System.out.println("Hash Indexing Execution Time: " + hashExecutionTime + " ms");

        System.out.println("==========================================");
        System.out.println();
    }
}
