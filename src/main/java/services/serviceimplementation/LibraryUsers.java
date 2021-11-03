package services.serviceimplementation;

import enums.Role;
import models.Person;
import services.LibraryUsersServices;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static models.Library.*;

public class LibraryUsers extends Person implements LibraryUsersServices {
    private Role role;
    private static List<Person> registeredPersons = new ArrayList<>();
    public Set<LibraryBook> borrowedBooks = new HashSet<>();

    public LibraryUsers(String firstName, String lastName, Role role) {
        super(firstName, lastName);
        this.role = role;
        this.borrowedBooks = borrowedBooks;
    }

    public Set<LibraryBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void borrowABook(LibraryBook book) {
        if (!book.getTitle().isEmpty()) {
            LocalDateTime time = LocalDateTime.now();
            appliedList.put(this, book);
            applyTime.put(time, this);
        }
    }

    @Override
    public void returnBook(LibraryBook book) {
        try {
            if (borrowedBooks.contains(book)) {
                borrowedBooks.remove(book);
                returningBooks.put(this, book.getTitle());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Sorry, you did not borrow this book from the library");
        }
    }

    @Override
    public int compareTo(LibraryUsers otherPerson) {

//        Integer.compare(otherPerson.getRole().getPriority(), users.getRole().getPriority());

        if(getRole().getPriority() < otherPerson.getRole().getPriority()){
            return 1;
        }
        else if(getRole().getPriority() > otherPerson.getRole().getPriority()){
            return -1;
        }
        return 0;
    }
}
