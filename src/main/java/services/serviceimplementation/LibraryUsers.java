package services.serviceimplementation;

import models.LibraryBook;
import models.Person;
import services.LibraryUsersServices;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static services.serviceimplementation.Library.*;

public class LibraryUsers extends Person implements LibraryUsersServices {
    private final Role role;
    private static final List<Person> registeredPersons = new ArrayList<>();
    private Set<LibraryBook> borrowedBooks = new HashSet<>();

    public LibraryUsers(String firstName, String lastName, Role role) {
        super(firstName, lastName);
        this.role = role;
        this.borrowedBooks = borrowedBooks;
        registeredPersons.add(this);
    }

    public Set<LibraryBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void borrowABook(LibraryBook book, Librarian librarian) {
        if (!book.getTitle().isEmpty()) {
//            LocalDateTime time = LocalDateTime.now();
            getAppliedList().put(this, book);
            Librarian.requestQueue().add(this);
//            getApplyTime().put(time, this);
        }
    }

    @Override
    public String toString() {
        return "" + super.toString();
    }

    @Override
    public void returnBook(LibraryBook book) {
        try {
            if (borrowedBooks.contains(book)) {
                borrowedBooks.remove(book);
                getReturningBooks().put(this, book);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Sorry, you did not borrow this book from the library");
        }
    }

    @Override
    public int compareTo(LibraryUsers otherPerson) {

        if(getRole().getPriority() < otherPerson.getRole().getPriority()){
            return 1;
        }
        else if(getRole().getPriority() > otherPerson.getRole().getPriority()){
            return -1;
        }
        return 0;
    }

    public enum Role {
        TEACHER(1),
        SENIOR_STUDENT(2),
        JUNIOR_STUDENT(3);

        private final int priority;

        Role(int priority) {
            this.priority = priority;
        }

        public int getPriority(){
            return priority;
        }
    }
}
