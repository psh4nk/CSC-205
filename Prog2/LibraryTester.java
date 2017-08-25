/**
 * Made by Preston Shankle
 * CSC 205
 * 10/1/2016
 * LibraryTester Class
 */

import java.io.*;
import java.util.*;

public class LibraryTester {
    public static void main (String[] args){
        ArrayList<LibraryBook> books = new ArrayList<LibraryBook>(50);
        Scanner input = new Scanner(System.in);
        int numBooks = inputBooks(welcomeScreen(), books);
        sortBooks(books, numBooks);
        System.out.println();
        System.out.println("A total of " + numBooks + " books have been input and sorted by title.");
        System.out.println("Please hit return to continue...");
        input.nextLine();
        printMenu(books, numBooks);
        clearScreen();
    }

    private static int inputBooks(String inputFile, ArrayList<LibraryBook> books) {
        /**
         * Integer returning method that is
         * passed the user-selected file name called 'inputFile'
         * and an empty LibraryBook ArrayList called 'books'
         * as parameters. The inputFile is scanned for
         * title names, author names, copyright dates, prices,
         * and genres, and each line of the file is used to
         * create a new LibraryBook with these given characteristics.
         * The ArrayList is filled with the newly defined LibraryBooks.
         */

        int numBooks = 0;
        try {
            Scanner in = new Scanner(new File(inputFile));
            while (in.hasNext()) {
                Scanner lsc = new Scanner(in.nextLine()).useDelimiter(";");

                String title = lsc.next();
                String name = lsc.next();
                int copyright = lsc.nextInt();
                double price = lsc.nextDouble();
                String genre = lsc.next();

                books.add(new LibraryBook(title, name, copyright, price, genre));
                numBooks++;
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        return numBooks;
    }

    private static void printRecord(ArrayList<LibraryBook> books, int location){
        /**
         * Void method that prints all characteristics
         * of a book using the LibraryBook ArrayList 'books' and the
         * integer 'location' of the book.
         */

        System.out.println("Record #" + (location + 1) + " :\n");
        System.out.println("Title:          " + books.get(location).getTitle());
        System.out.println("Author's Name:  " + books.get(location).getAuthor());
        System.out.println("Copyright:      " + books.get(location).getCopyright());
        System.out.println("Price:          " + books.get(location).getPrice());
        System.out.println("Genre:  " + books.get(location).getGenre() + "\n");
    }

    private static void sortBooks(ArrayList<LibraryBook> books, int numBooks){
        /**
         * Void method that is passed an ArrayList of
         * LibraryBooks 'books' and an integer number of books,
         * 'numBooks.' Using a selection sort, the books are
         * sorted by title.
         */

        int minIndex, j;
        LibraryBook temp;  int pass = 0;

        for (int index = 0; index < numBooks; index++)
        {
            minIndex = index;
            for (j = minIndex+1; j < numBooks;  j++)
                if(books.get(j).getTitle().compareTo(books.get(minIndex).getTitle()) < 0)
                    minIndex = j;

            if (minIndex != index)
            {
                temp = books.get(index);
                books.set(index, books.get(minIndex));
                books.set(minIndex, temp);
            }
        }

    }
    private static String welcomeScreen(){
        /**
         * String returning method that prints out a welcome menu
         * for the program. This method also scans the current
         * directory for data-containing files (.dat) and prints
         * them below the welcome screen. Finally, the user inputs the
         * chosen file name, and the method returns the user selection.
         */

        Scanner input = new Scanner(System.in);
        Scanner file = new Scanner(System.in);
        clearScreen();
        System.out.println("                 BOOK SEARCH PROGRAM                ");
        System.out.println("____________________________________________________");
        System.out.println("     What file is your book data stored in? ");
        System.out.println("     Here are the files in your current directory:");
        System.out.println();
        File curDir = new File(".");
        String[] fileNames = curDir.list();
        ArrayList<String> data = new ArrayList<String>();
        for(String s:fileNames)
            if(s.endsWith(".dat"))
                data.add(s);
        for(int i = 0; i < data.size(); i++)
            System.out.println(data.get(i));
        System.out.print("Filename: ");
        String fileName = file.nextLine();
        while(!data.contains(fileName)){
            if(data.contains(fileName)) return fileName;
            else{
                System.out.println();
                System.out.println("File not found.");
                System.out.print("Try again: ");
                fileName = file.nextLine();
            }
        }
        return fileName;
    }

    private static void printMenu(ArrayList<LibraryBook> books, int numBooks){
        /**
         * Void method that is passed a LibraryBook ArrayList 'books'
         * and an integer number of books 'numBooks.' The menu for the
         * program is printed to the user's screen, and the
         * userSelection method is called for the user to select which
         * action he would like to take.
         */

        clearScreen();
        System.out.println();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("        THE GREAT BOOKS SEARCH PROGRAM     ");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("1) Display all book records");
        System.out.println("2) Search for a book by title");
        System.out.println("3) Exit Search Program");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Please Enter 1-3 >>>");
        userSelection(books, numBooks);
    }

    private static void userSelection(ArrayList<LibraryBook> books, int numBooks){
        /**
         * Void method that is passed a LibraryBook ArrayList 'books'
         * and an integer value of the number of books, 'numBooks.'
         * A scanner object receives the user's selection from the
         * main menu, and a switch statement is used to determine which
         * method should be called next.
         */

        int selection;
        Scanner input = new Scanner(System.in);
        selection = input.nextInt();
        while(selection != 1 && selection != 2 && selection != 3) {
            System.out.println("Invalid input, try again.");
            selection = input.nextInt();
        }
        switch(selection) {
            case 1: clearScreen();
                displayRecords(books, numBooks);
                break;
            case 2: searchBook(books, numBooks);
                break;
            case 3:
                break;
            default:    System.out.println("Invalid selection");
                break;
        }
    }

    private static int searchAllBooks(ArrayList<LibraryBook> books, String bookTitle, int numBooks){
        /**
         * Integer returning method that is passed a LibraryBook
         * ArrayList of 'books,' a String 'bookTitle,' and an
         * integer number of books 'numBooks.' Using a binary search,
         * the ArrayList is scanned for the bookTitle that is input by the user
         * in the searchBook method.
         *
         */

        int location = 0;
        int first = 0, last = numBooks, middle;
        int checks = 0;

        boolean found = false;

        do
        {
            middle = (first + last) / 2;

            if(books.get(middle).getTitle().compareTo(bookTitle) == 0)
                found = true;
            else if (books.get(middle).getTitle().compareTo(bookTitle) > 0)
                last = middle - 1;
            else
                first = middle + 1;
        }  while ( (! found) && (first <= last) );

        location = middle;

        return (found ? location : -1);
    }

    private static void searchBook(ArrayList<LibraryBook> books, int numBooks){
        /**
         * Void method that is passed a LibraryBook
         * ArrayList of 'books' and the integer 'numBooks.'
         * A title is input by the user and the searchAllBooks
         * method is called to search the ArrayList books for the title.
         */

        Scanner input = new Scanner(System.in);

        System.out.print("Search: ");
        String bookSearch = input.nextLine();
        int i = searchAllBooks(books, bookSearch, numBooks-1);
        if(i != -1) {
            System.out.println("Book found at location: " + i + ".");
            clearScreen();
            System.out.println("------------------------------");
            printRecord(books, i);
            System.out.println("------------------------------");
            String enter = input.nextLine();
        }
        else {
            System.out.println("Book not found");
            System.out.println("Please hit return to continue...");
            String enter = input.nextLine();
        }

        printMenu(books, numBooks);

    }

    private static void displayRecords(ArrayList<LibraryBook> books, int numBooks){
        /**
         * Void method that is passed the ArrayList books and
         * the integer number of books and prints a card using
         * the printRecord method to show the user the information
         * about a selected book.
         */

        Scanner enter = new Scanner(System.in);
        int pass = 0;
        int i = 0;
        while(i < books.size()) {
            for (i = 0; i < books.size(); i++) {
                System.out.println("------------------------------");
                printRecord(books, i);
                System.out.println("------------------------------");
                enter.nextLine();
                clearScreen();
                pass++;
            }
        }
        printMenu(books, numBooks);
    }


    private static void clearScreen() {
        /**
         * Void method that prints a line of bash
         * magic to clear the screen of any text.
         */

        System.out.println("\u001b[H\u001b[2J");
    }

}

