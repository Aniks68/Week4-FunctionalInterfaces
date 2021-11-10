package services.serviceimplementation;

import models.LibraryBook;
import models.Person;
import services.LibrarianServices;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import static models.Library.*;
import static models.Library.getAvailableBooks;

public class Librarian extends Person implements LibrarianServices {

    public Librarian(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void addABook(LibraryBook book) {
        addBook.accept(getAvailableBooks(), book);
    }

    @Override
    public void lendBook() {
        Iterator value = requestQueue().iterator();

        while (value.hasNext()) {
            LibraryUsers user = (LibraryUsers) value.next();
            LibraryBook book = getAppliedList().get(user);

            try {
                lendBookProcess(user, book);
            } catch (Exception e) {
                throw new IllegalArgumentException("Apologies, " + book.getTitle() + " is not available now.");
            }
        }
    }

    private void lendBookProcess(LibraryUsers user, LibraryBook book) {

        if (getAvailableBooks().containsKey(book.getTitle()) && (getAvailableBooks().get(book.getTitle()) != 0)
                && (!user.getBorrowedBooks().contains(book))) {
            user.getBorrowedBooks().add(book);
            updateLentRecords(user, book);
            updateAvailCopies.accept(getAvailableBooks(), book);
            System.out.println(book.getTitle() + " has been lent to " + user.getFirstName() + " " + user.getLastName());
        } else {
            System.out.println("Apologies " + user.getFirstName() + ", " + book.getTitle() + " is not available now.");
        }
    }

    private void updateLentRecords(LibraryUsers user, LibraryBook book) {
        if(!getLentRecords().containsKey(user)) {
            getLentRecords().put(user, user.getBorrowedBooks());
        } else {
            getLentRecords().get(user).add(book);
        }
    }

    @Override
    public void acceptReturnedBooks() {
        Iterator users = getReturningBooks().keySet().iterator();
        Iterator books = getReturningBooks().values().iterator();

        while (users.hasNext() && books.hasNext()) {
            LibraryUsers user = (LibraryUsers) users.next();
            LibraryBook book = (LibraryBook) books.next();

            try {
                if ((getLentRecords().containsKey(user)) && (getLentRecords().get(user).contains(book))) {
                    getLentRecords().get(user).remove(book);
                    user.getBorrowedBooks().remove(book);
                    updateReturnedCopies(book);
                    System.out.println(user.getFirstName() + " has been removed from the list.");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("You are either not in the record or returning the wrong book");
            }
        }
    }


    private void updateReturnedCopies(LibraryBook book) {
        try {
            if (getAvailableBooks().containsKey(book.getTitle())) {
                int availCopy = getAvailableBooks().get(book.getTitle());
                getAvailableBooks().replace(book.getTitle(), availCopy+1);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static Queue<LibraryUsers> requestQueue() {
        return bookRequestPriorityQueue;
    }


    private static Queue<LibraryUsers> bookRequestPriorityQueue = new PriorityQueue<>((o1, o2) -> {
        final int user1Priority = o1.getRole().getPriority();
        final int user2Priority = o2.getRole().getPriority();
        return Integer.compare(user1Priority, user2Priority);
    });

}
