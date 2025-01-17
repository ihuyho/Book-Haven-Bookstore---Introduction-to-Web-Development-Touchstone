package TouchstoneProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Book> books = readBooks("library.csv");
        displayMenu();
        listBooks("library.csv");
        addBook(input, "library.csv");
    }

    public static ArrayList<Book> readBooks(String csvFile) {
        ArrayList<Book> bookList = new ArrayList<>();

        File inputDataFile = new File(csvFile);
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(inputDataFile.toPath());
            for(String line : lines) {
                String[] bookData = line.split(",");

                String title = bookData[0];
                String authorLast = bookData[1];
                String authorFirst = bookData[2];
                String genre = bookData[3];
                String rating = bookData[4];

                Book book = new Book(title, authorLast, authorFirst, genre, rating);

                bookList.add(book);
            }
        } 
        catch(FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        catch(NumberFormatException ex) {
            System.out.println("Number Format error: " + ex.getMessage());
        }
        return bookList;
    }

    public static void writeBook(String csvFile, ArrayList<Book> books) {
        ArrayList<String> newBook = new ArrayList<>();
        for(Book book : books) {
            newBook.add(book.getTitle() + "," + book.getAuthorLast() + "," + book.getAuthorFirst() + "," + book.getGenre() + "," + book.getRating());
        }
        File outputFile = new File(csvFile);
        try{
            Files.write(outputFile.toPath(), newBook, StandardOpenOption.APPEND);
        }
        catch(IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    public static void addBook(Scanner input, String csvFile) {
        ArrayList<Book> booksToAdd = new ArrayList<>();
        char oneMore = 'y';
        while(oneMore == 'y') {
            System.out.print("Enter Title of the book: ");
            String title = input.nextLine();
            System.out.print("Enter the Author's Last Name: ");
            String last = input.nextLine();
            System.out.print("Enter the Author's First Name: ");
            String first = input.nextLine();
            System.out.print("Enter the genre of the book: ");
            String genre = input.nextLine();
            System.out.println("Enter your rating for the book (0-5 stars): ");
            System.out.print("If you don't have one yet, enter 'none': ");
            String rating = input.nextLine();
            input.nextLine();
            Book books = new Book(title, last, first, genre, rating);
            booksToAdd.add(books);

            System.out.println("Add more? (y/n): ");
            oneMore = input.nextLine().charAt(0);
            Character.toLowerCase(oneMore);
        }
        writeBook("library.csv", booksToAdd);
    }

    public static void listBooks(String csvFile) {
        ArrayList<Book> books = readBooks(csvFile);
        int bookList = 1;
        for(Book book : books) {
            System.out.println(bookList++ + ". " + book.getTitle());
            System.out.println("\tAuthor: " + book.getAuthorFirst() + " " + book.getAuthorLast());
            System.out.println("\tGenre: " + book.getGenre());
            System.out.print("\tPersonal Rating: ");
            if(!"none".equals(book.getRating())) {
                System.out.println(book.getRating() + " stars");
            }
            else {
                System.out.println("--");
            }
        }
    }

    public static boolean deleteBooks(String csvFile, Scanner input) {
        ArrayList<Book> books = readBooks(csvFile);
        boolean bookDeleted = false;
        Book bookToDelete = null;
        char oneMore = 'y';
        while(oneMore == 'y') {
            System.out.print("Enter the title of the book you want to delete: ");
            String title = input.nextLine();
            for(Book book : books) {
                if(title.equals(book.getTitle())) {
                    bookToDelete = book;
                }
            }
    
            if(bookToDelete != null) {
                books.remove(bookToDelete);
                bookDeleted = true;
                ArrayList<String> remainingBooks = new ArrayList<>();
                for(Book book : books) {
                    remainingBooks.add(book.getTitle() + "," + book.getAuthorLast() + "," + book.getAuthorFirst() + "," + book.getGenre() + "," + book.getRating());
                }
                File outputFile = new File(csvFile);
                try {
                    Files.write(outputFile.toPath(), remainingBooks, StandardOpenOption.TRUNCATE_EXISTING);
                } catch (IOException ex) {
                    System.out.println("Error writing to file: " + ex.getMessage());
                }
            }
        }
        return bookDeleted;
    }

    public static void displayMenu() {
        System.out.println("Welcome to your library! What would you like to do?");
        System.out.println("\n list - List all books");
        System.out.println(" add - Add a book");
        System.out.println(" del - Delete a book");
        System.out.println(" exit - Exit program");
        System.out.print("Enter command: ");
    }
}
