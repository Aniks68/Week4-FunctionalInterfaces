package services.serviceimplementation;

import enums.Role;
import models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryUsersTest {
    LibraryUsers user;
    @BeforeEach
    void setUp() {
        user = new LibraryUsers("Martha", "Hassan", Role.TEACHER);
    }

    @Test
    @DisplayName("To borrow a book from the library")
    void borrowABook() {
        // Given
        Library decagonLib = new Library();
        LibraryBook javaNote = new LibraryBook("Elvis", "Java for JJC", 8);
        user.borrowABook(javaNote);

        final int expectedResult = 1;
        final int actualResult = decagonLib.getAppliedList().size();

        assertEquals(expectedResult, actualResult);
    }

    @Test@DisplayName("To return a borrowed book to the library")
    void returnBook() {
        // Given
        LibraryBook ios = new LibraryBook("Elvis", "iOS for JJC", 8);
        user.borrowABook(ios);
        user.returnBook(ios);

        final int expectedResult = 0;
        final int actualResult = user.getBorrowedBooks().size();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("To test the priority comparison")
    void compareTo() {
        // Given
        LibraryUsers user2 = new LibraryUsers("Maeli", "Elvira", Role.SENIOR_STUDENT);

        final int expectedResult = 1;
        final int actualResult = user.compareTo(user2);

        assertEquals(expectedResult, actualResult);
    }
}