
import java.util.Scanner;

public class BookDatabase {
    private String[] isbnArray;  // Array to store ISBN numbers
    private int count;  // Counter to keep track of the number of ISBNs in the array
    private static final int MAX_SIZE = 100;  // Maximum size of the ISBN array
    private static final String LOWER_BOUND = "0000000000";  // Lower bound for valid ISBNs
    private static final String UPPER_BOUND = "9999999999";  // Upper bound for valid ISBNs


    // Constructor to initialize the ISBN array and counter
    public BookDatabase() {
        isbnArray = new String[MAX_SIZE];
        count = 0;
    }


    // Method to validate the ISBN
    public boolean validateISBN(String isbn) {
        if (isbn.length() != 10) {  // Check if the ISBN is 10 digits long
            return false;
        }

        for (char c : isbn.toCharArray()) {  // Ensure all characters are digits
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // Compare the ISBN against the lower and upper bounds
        return isbn.compareTo(LOWER_BOUND) >= 0 && isbn.compareTo(UPPER_BOUND) <= 0;
    }

    // Method to add a valid ISBN to the database
    public void addISBN(String isbn) {
        if (validateISBN(isbn)) {  // Check if the ISBN is valid
            if (count < MAX_SIZE) {  // Ensure there is space in the database
                isbnArray[count++] = isbn;  // Add ISBN to the array and increment the counter
                System.out.println("ISBN added to the database.");
            } else {
                System.out.println("Database is full. Cannot add more ISBNs.");
            }
        } else {
            System.out.println("Invalid ISBN. Cannot add to the database.");
        }
    }

    // Method to search for an ISBN in the database
    public boolean searchISBN(String isbn) {
        for (int i = 0; i < count; i++) {  // Iterate over the array of ISBNs
            if (isbnArray[i].equals(isbn)) {  // Check if the ISBN is found
                return true;
            }
        }
        return false;  // Return false if ISBN is not found
    }


    // Method to print all ISBNs in the database
    public void printAllISBNs() {
        if (count == 0) {  // Check if there are no ISBNs in the database
            System.out.println("No ISBNs in the database.");
            return;
        }
        System.out.println("All ISBNs in the database:");
        for (int i = 0; i < count; i++) {  // Iterate over the array and print each ISBN
            System.out.println(isbnArray[i]);
        }
    }

    // Main method to provide a menu for user interaction
    public static void main(String[] args) {
        BookDatabase db = new BookDatabase();  // Create an instance of BookDatabase
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object for user input

        while (true) {
            // Display the menu options
            System.out.println("Menu:");
            System.out.println("1. Add ISBN");
            System.out.println("2. Search ISBN");
            System.out.println("3. Print all ISBNs");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 4) {  // Exit the loop if the user chooses to exit
                System.out.println("Exiting the program...");
                break;
            }

            switch (choice) {
                case 1:
                    // Prompt the user to enter an ISBN to add
                    System.out.print("Enter ISBN to add: ");
                    String isbnToAdd = scanner.nextLine();
                    db.addISBN(isbnToAdd);  // Call the addISBN method
                    break;
                case 2:
                    // Prompt the user to enter an ISBN to search
                    System.out.print("Enter ISBN to search: ");
                    String isbnToSearch = scanner.nextLine();
                    boolean found = db.searchISBN(isbnToSearch);  // Call the searchISBN method
                    if (found) {
                        System.out.println("ISBN found in the database.");
                    } else {
                        System.out.println("ISBN not found in the database.");
                    }
                    break;
                case 3:
                    // Call the printAllISBNs method to print all ISBNs in the database
                    db.printAllISBNs();
                    break;
                default:
                    // Display an error message if the user enters an invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();  //This Close the Scanner object
    }
}
