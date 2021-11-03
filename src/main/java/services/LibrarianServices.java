package services;

import services.serviceimplementation.LibraryBook;
import services.serviceimplementation.LibraryUsers;
import java.util.Map;

public interface LibrarianServices {

    void addBook(Map<String, Integer> bookList, LibraryBook book);

    void lendBook(LibraryBook book, LibraryUsers user);

    void updateAvailCopies(Map<String, Integer> bookList, LibraryBook book);

    void acceptReturnedBooks(LibraryUsers users, LibraryBook book);
}
