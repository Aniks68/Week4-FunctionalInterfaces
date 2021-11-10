package services;

import services.serviceimplementation.Library;
import models.LibraryBook;
import services.serviceimplementation.LibraryUsers;

public interface LibraryUsersServices {

    void borrowABook(LibraryBook book, Library.Librarian librarian);

    void returnBook(LibraryBook book);

    int compareTo(LibraryUsers otherPerson);
}
