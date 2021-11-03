package services.serviceimplementation;

import models.Person;
import services.LibrarianServices;
import java.util.Map;

import static models.Library.*;

public class Librarian extends Person implements LibrarianServices {

    public Librarian(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void addBook(Map<String, Integer> bookList, LibraryBook book) {
        if (bookList.containsKey(book.getTitle())) {
            int availCopy = bookList.get(book.getTitle());
            bookList.replace(book.getTitle(), availCopy+=book.getCopies());
        } else bookList.put(book.getTitle(), book.getCopies());
    }

    @Override
    public void lendBook(LibraryBook book, LibraryUsers user) {

        System.out.println("InsideLendBook: "+ book.getTitle());
        try {
            if (availableBooks.containsKey(book.getTitle()) && (availableBooks.get(book.getTitle()) != 0)) {
                lentRecords.put(user, book.getTitle());
                user.getBorrowedBooks().add(book);
                updateAvailCopies(availableBooks, book);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Apologies, " + book.getTitle() + " is not available now.");
        }
    }

    @Override
    public void updateAvailCopies(Map<String, Integer> bookList, LibraryBook book) {
        try {
            if (bookList.containsKey(book.getTitle())) {
                int availCopy = bookList.get(book.getTitle());
                bookList.replace(book.getTitle(), availCopy -= 1);
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptReturnedBooks(LibraryUsers users, LibraryBook book) {
        try {
            if ((lentRecords.containsKey(users)) && (lentRecords.get(users).equals(book.getTitle()))) {
                lentRecords.remove(users, book.getTitle());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("You are either not in the record or returning the wrong book");
        }
    }


}
