package TouchstoneProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String csvFile = "library.csv";
        String cmd = "";

        while(!cmd.toLowerCase().equals("exit")) {
            displayMenu();
            cmd = input.nextLine();
            String order = "none";
            if(cmd.toLowerCase().equals("list")) {
                listBooks(csvFile);
            }
            else if(cmd.toLowerCase().equals("add")) {
                addBook(input, csvFile);
            }
            else if(cmd.toLowerCase().equals("del")) {
                deleteBooks(csvFile, input);
            }
            else if(cmd.toLowerCase().equals("edit")) {
                edit(csvFile, input);
            }
            else if(cmd.toLowerCase().equals("rate")) {
                editRating(csvFile, input);
            }
            else if(cmd.toLowerCase().equals("sort")) {
                sortBooks(csvFile, input);
            }
        }
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
            String name = input.nextLine();
            String last = capitalizeNames(name);
            System.out.print("Enter the Author's First Name: ");
            name = input.nextLine();
            String first = capitalizeNames(name);
            System.out.print("Enter the genre of the book: ");
            name = input.nextLine();
            String genre = capitalizeNames(name);
            System.out.println("Enter your rating for the book (0-5 stars): ");
            System.out.print("If you don't have one yet, enter 'x': ");
            String rating = input.nextLine();
            if(rating.equals("x")) {
                rating = "no rating yet";
            }
            Book books = new Book(title, last, first, genre, rating);
            booksToAdd.add(books);

            System.out.println("Add more? (y/n): ");
            oneMore = input.nextLine().charAt(0);
            Character.toLowerCase(oneMore);
        }
        writeBook("library.csv", booksToAdd);
    }

    public static String capitalizeNames(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String[] words = name.split("\\s+"); 
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String[] parts = word.split("(?=['-])|(?<=['-])");
                for (int i = 0; i < parts.length; i++) {
                    if (Character.isLetter(parts[i].charAt(0))) {
                        parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1);
                    }
                }
                
                capitalized.append(String.join("", parts)).append(" ");
            }
            
        }

        return capitalized.toString().trim();
    }


    public static void listBooks(String csvFile) {
        ArrayList<Book> books = readBooks(csvFile);
        int bookList = 1;
        for(Book book : books) {
            System.out.println(bookList++ + ". " + book.getTitle());
            System.out.println("\tAuthor: " + book.getAuthorFirst() + " " + book.getAuthorLast());
            System.out.println("\tGenre: " + book.getGenre());
            System.out.print("\tPersonal Rating: ");
            if(!"x".equals(book.getRating())) {
                System.out.println(book.getRating() + " stars");
            }
            else {
                System.out.println("--");
            }
        }  
    }

    public static void printBooksInOrder(String csvFile, ArrayList<Book> books) {
        int bookList = 1;
        for(Book book : books) {
            System.out.println(bookList++ + ". " + book.getTitle());
            System.out.println("\tAuthor: " + book.getAuthorFirst() + " " + book.getAuthorLast());
            System.out.println("\tGenre: " + book.getGenre());
            System.out.print("\tPersonal Rating: ");
            if(!"x".equals(book.getRating())) {
                System.out.println(book.getRating() + " stars");
            }
            else {
                System.out.println("--");
            }
        }
    }


    public static void sortBooks(String csvFile, Scanner input) {
        ArrayList<Book> books = readBooks(csvFile);
        System.out.println("How do you want to sort your books?");
        System.out.println("\tt - Title\n\tf - Author's First Name\n\tl - Author's Last Name\n\tr - Rating");
        String order = input.nextLine();
        if(order.equals("t")) {
            System.out.println("Ascending (a) or Descending (d)");
            String ascOrDesc = input.nextLine();
            if(ascOrDesc.equals("a")) {
                books.sort(Comparator.comparing(book -> stripLeadingArticles(book.getTitle())));
                printBooksInOrder(csvFile, books);
            }
            else if(ascOrDesc.equals("d")) {
                books.sort(Comparator.comparing((Book book) -> stripLeadingArticles(book.getTitle())).reversed());
                printBooksInOrder(csvFile, books);
            }
        }
        else if(order.equals("f")) {
            System.out.println("Ascending (a) or Descending (d)");
            String ascOrDesc = input.nextLine();
            if(ascOrDesc.equals("a")) {
                books.sort(Comparator.comparing(book -> stripLeadingArticles(book.getAuthorFirst())));
                printBooksInOrder(csvFile, books);
            }
            else if(ascOrDesc.equals("d")) {
                books.sort(Comparator.comparing((Book book) -> stripLeadingArticles(book.getAuthorFirst())).reversed());
                printBooksInOrder(csvFile, books);
            }
        }
        else if(order.equals("l")) {
            System.out.println("Ascending (a) or Descending (d)");
            String ascOrDesc = input.nextLine();
            if(ascOrDesc.equals("a")) {
                books.sort(Comparator.comparing(book -> stripLeadingArticles(book.getAuthorLast())));
                printBooksInOrder(csvFile, books);
            }
            else if(ascOrDesc.equals("d")) {
                books.sort(Comparator.comparing((Book book) -> stripLeadingArticles(book.getAuthorLast())).reversed());
                printBooksInOrder(csvFile, books);
            }
        }
        else if(order.equals("r")) {
            System.out.println("Ascending (a) or Descending (d)");
            String ascOrDesc = input.nextLine();
            if(ascOrDesc.equals("a")) {
                books.sort(Comparator.comparing(book -> stripLeadingArticles(book.getRating())));
                printBooksInOrder(csvFile, books);
            }
            else if(ascOrDesc.equals("d")) {
                books.sort(Comparator.comparing((Book book) -> stripLeadingArticles(book.getRating())).reversed());
                printBooksInOrder(csvFile, books);
            }
        }
        
    }

    private static String stripLeadingArticles(String title) {
        String[] articles = {"the ", "a ", "an "};
        title = title.toLowerCase(); // Make it case-insensitive
        for (String article : articles) {
            if (title.startsWith(article)) {
                return title.substring(article.length()).trim();
            }
        }
        return title;
    }

    public static void editRating(String csvFile, Scanner input) {
        ArrayList<Book> books = readBooks(csvFile);
        Book bookToEdit = null;
        char oneMore = 'y';
        while(oneMore == 'y') {
            System.out.print("Enter the title of the book you want to edit: ");
            String title = input.nextLine();
            for(Book book : books) {
                if(title.equals(book.getTitle())) {
                    bookToEdit = book;
                }
            }
        
            if(bookToEdit != null) {
                System.out.println("What is the new rating? (0-5)");
                String newRating = input.nextLine();
                bookToEdit.setRating(newRating);
                System.out.println("Rating has been updated!");
            }

            System.out.println("One more? (y/n)");
            oneMore = input.nextLine().charAt(0);
        }
    }

    public static void edit(String csvFile, Scanner input) {
        ArrayList<Book> books = readBooks(csvFile);
        Book bookToEdit = null;
        char oneMore = 'y';
        while(oneMore == 'y') {
            System.out.print("Enter the title of the book you want to edit: ");
            String title = input.nextLine();
            for(Book book : books) {
                if(title.equals(book.getTitle())) {
                    bookToEdit = book;
                }
            } 

            if(bookToEdit != null) {
                System.out.println("What do you want to edit?");
                System.out.print("\tt - Title\n\tl - Author's Last Name\n\tf - Author's First Name\n\tg - Genre\n");
                String answer = input.nextLine();
                if(answer.equals("t")) {
                    System.out.println("Enter the new title: ");
                    String newTitle = input.nextLine();
                    bookToEdit.setTitle(newTitle);
                    System.out.println("Title has been udpated!");
                }
                else if(answer.equals("l")) {
                    System.out.println("Enter the new last name: ");
                    String newLast = input.nextLine();
                    bookToEdit.setAuthorLast(newLast);
                    System.out.println("Last name has been updated!");
               
                }
                else if(answer.equals("f")) {
                    System.out.println("Enter the new first name: ");
                    String newFirst = input.nextLine();
                    bookToEdit.setAuthorFirst(newFirst);
                    System.out.println("First name has been updated!");
                
                }
                else if(answer.equals("g")) {
                    System.out.println("Enter the new genre");
                    String newGenre = input.nextLine();
                    bookToEdit.setGenre(newGenre);
                    System.out.println("Genre has been updated!");
                
                }
            }

            System.out.println("Want to edit more (y/n)? ");
            oneMore = input.nextLine().charAt(0);

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

            System.out.println("Delete another book? (y/n): ");
            oneMore = input.nextLine().charAt(0);
        }
        return bookDeleted;
    }

    public static void displayMenu() {
        System.out.println("Welcome to your library! What would you like to do?");
        System.out.println("\nlist - List all books");
        System.out.println("\tadd - Add a book");
        System.out.println("\tdel - Delete a book");
        System.out.println("\tedit - Edit a book");
        System.out.println("\trate - Edit the rating of a book");
        System.out.println("\tsort - Sort books in order");
        System.out.println("\texit - Exit program");
        System.out.print("Enter command: ");
    }

    
}
