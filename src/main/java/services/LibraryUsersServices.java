package services;

import services.serviceimplementation.LibraryBook;
import services.serviceimplementation.LibraryUsers;

public interface LibraryUsersServices {

    void borrowABook(LibraryBook book);

    void returnBook(LibraryBook book);

    int compareTo(LibraryUsers otherPerson);
}
