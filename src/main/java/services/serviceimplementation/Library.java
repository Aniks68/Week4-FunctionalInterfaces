package services.serviceimplementation;

import models.Person;
import services.LibrarianServices;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Library implements Comparable<LibraryUsers.Role> {
    private static Map<LibraryUsers, Set<LibraryBook>> lentRecords = new HashMap<>();
    private static Map<String, Integer> availableBooks = new HashMap<>();
    private static HashMap<LibraryUsers, LibraryBook> appliedList = new HashMap<LibraryUsers, LibraryBook>();
    private static LinkedHashMap<LocalDateTime, LibraryUsers> applyTime = new LinkedHashMap<>();
    private static Map<LibraryUsers, String> returningBooks = new HashMap<>();


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

    public static Map<LibraryUsers, String> getReturningBooks() {
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

        public BiConsumer<Map<String,Integer>, LibraryBook> addBook = (bookList,book) -> {

            if (bookList.containsKey(book.getTitle())) {
                int availCopy = bookList.get(book.getTitle());
                bookList.replace(book.getTitle(), availCopy+=book.getCopies());
            } else bookList.put(book.getTitle(), book.getCopies());
        };

//         BiConsumer<Map<>, LibraryBook> addBookTest = (bookList, book) -> {
//            if (bookList.containsKey(book.getTitle())) {
//                int availCopy = (int) bookList.get(book.getTitle());
//                bookList.replace(book.getTitle(), availCopy+=book.getCopies());
//            } else bookList.put(book.getTitle(), book.getCopies());
//        };


        @Override
        public void addBook(Map<String, Integer> bookList, LibraryBook book) {
            addBook(bookList,book);
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

            Predicate<LibraryBook> book1 = (x) -> availableBooks.containsKey(book.getTitle().toString());
//            System.out.println("========");
//            System.out.println("The truth is : " + book1);
//            System.out.println("++++++++++++++++++");
            if (availableBooks.containsKey(book.getTitle()) && (availableBooks.get(book.getTitle()) != 0)
                    && (!user.getBorrowedBooks().contains(book))) {
                user.getBorrowedBooks().add(book);
                updateLentRecords(user, book);
                updateAvailCopies(availableBooks, book);
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
        public void updateAvailCopies(Map<String, Integer> bookList, LibraryBook book) {
            try {
                if (bookList.containsKey(book.getTitle())) {
                    int availCopy = bookList.get(book.getTitle());
                    bookList.replace(book.getTitle(), availCopy-1);
                }
            } catch (ArithmeticException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void acceptReturnedBooks(LibraryUsers user, LibraryBook book) {
            try {
                if ((lentRecords.containsKey(user)) && (lentRecords.get(user).contains(book))) {
                    lentRecords.get(user).remove(book);
                    user.getBorrowedBooks().remove(book);
                    updateReturnedCopies(book);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("You are either not in the record or returning the wrong book");
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

