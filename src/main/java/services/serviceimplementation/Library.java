package services.serviceimplementation;

import models.LibraryBook;
import models.Person;
import services.LibrarianServices;

import java.time.LocalDateTime;
import java.util.*;

public class Library implements Comparable<LibraryUsers.Role> {
    private static Map<LibraryUsers, Set<LibraryBook>> lentRecords = new HashMap<>();
    private static Map<String, Integer> availableBooks = new HashMap<>();
    private static HashMap<LibraryUsers, LibraryBook> appliedList = new HashMap<LibraryUsers, LibraryBook>();
    private static LinkedHashMap<LocalDateTime, LibraryUsers> applyTime = new LinkedHashMap<>();
    private static Map<LibraryUsers, LibraryBook> returningBooks = new HashMap<>();


    public Library() {
    }

    public static Map<LibraryUsers, Set<LibraryBook>> getLentRecords() {
        return lentRecords;
    }

    public static Map<String, Integer> getAvailableBooks() {
        return availableBooks;
    }

    public static HashMap<LibraryUsers, LibraryBook> getAppliedList() {
        return appliedList;
    }

    public static LinkedHashMap<LocalDateTime, LibraryUsers> getApplyTime() {
        return applyTime;
    }

    public static Map<LibraryUsers, LibraryBook> getReturningBooks() {
        return returningBooks;
    }

    @Override
    public int compareTo(LibraryUsers.Role o) {
        return 0;
    }


    public static class Librarian extends Person implements LibrarianServices {

        public Librarian(String firstName, String lastName) {
            super(firstName, lastName);
        }

        public void addABook(LibraryBook book) {
            addBook.accept(availableBooks, book);
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

           if (availableBooks.containsKey(book.getTitle()) && (availableBooks.get(book.getTitle()) != 0)
                    && (!user.getBorrowedBooks().contains(book))) {
                user.getBorrowedBooks().add(book);
                updateLentRecords(user, book);
                updateAvailCopies.accept(availableBooks, book);
                System.out.println(book.getTitle() + " has been lent to " + user.getFirstName() + " " + user.getLastName());
            } else {
                System.out.println("Apologies " + user.getFirstName() + ", " + book.getTitle() + " is not available now.");
            }
        }

        private void updateLentRecords(LibraryUsers user, LibraryBook book) {
            if(!lentRecords.containsKey(user)) {
                lentRecords.put(user, user.getBorrowedBooks());
            } else {
                lentRecords.get(user).add(book);
            }
        }

        @Override
        public void acceptReturnedBooks() {
            Iterator users = returningBooks.keySet().iterator();
            Iterator books = returningBooks.values().iterator();

            while (users.hasNext() && books.hasNext()) {
                LibraryUsers user = (LibraryUsers) users.next();
                LibraryBook book = (LibraryBook) books.next();

                try {
                    if ((lentRecords.containsKey(user)) && (lentRecords.get(user).contains(book))) {
                        lentRecords.get(user).remove(book);
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
                if (availableBooks.containsKey(book.getTitle())) {
                    int availCopy = availableBooks.get(book.getTitle());
                    availableBooks.replace(book.getTitle(), availCopy+1);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        public static Queue<LibraryUsers> requestQueue() {
            return bookRequestPriorityQueue;
        }


        private static Queue<LibraryUsers> bookRequestPriorityQueue =
                new PriorityQueue<>(new Comparator<LibraryUsers>() {
                    @Override
                    public int compare(LibraryUsers o1, LibraryUsers o2) {
                        final int user1Priority = o1.getRole().getPriority();
                        final int user2Priority = o2.getRole().getPriority();
                        if (user1Priority < user2Priority) {
                            return -1;
                        } else if (user1Priority > user2Priority) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });

    }
}

