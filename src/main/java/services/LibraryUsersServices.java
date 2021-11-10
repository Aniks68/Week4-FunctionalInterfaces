package services;

import models.Library;
import models.LibraryBook;
import services.serviceimplementation.Librarian;
import services.serviceimplementation.LibraryUsers;

public interface LibraryUsersServices {

    void borrowABook(LibraryBook book, Librarian librarian);

    void returnBook(LibraryBook book);

    int compareTo(LibraryUsers otherPerson);
}
