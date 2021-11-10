package services.serviceimplementation;

import models.Library;
import models.LibraryBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static services.serviceimplementation.LibraryUsers.Role.SENIOR_STUDENT;

class LibrarianTest {
    LibraryUsers user;
    Librarian librarian;
    Library decagonLib;
    LibraryBook javaAdvanced;
    LibraryBook node;

    @BeforeEach
    void setUp() {
        user = new LibraryUsers("Ella", "Velasco", SENIOR_STUDENT);
        librarian = new Librarian("Robin", "Silva");
        decagonLib = new Library();
        javaAdvanced = new LibraryBook("Amigoscode", "Java for Pros", 4);
        node = new LibraryBook("Geeks4Geeks", "Node.js for Junior Developers", 3);
    }

    @Test
    @DisplayName("To test if both books were added to the empty bookList")
    void addABook() {
        // Given
        librarian.addABook(javaAdvanced);
        librarian.addABook(node);

        final int expectedResult = 2;
        final int actualResult = decagonLib.getAvailableBooks().size();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("To test if the borrower and borrowed book are added to library records")
    void lendBook() {
        // Given
        librarian.addABook(javaAdvanced);
        librarian.addABook(node);
        user.borrowABook(javaAdvanced, librarian);
        librarian.lendBook();

        final boolean expectedResult = true;
        final boolean actualResult = decagonLib.getLentRecords().containsKey(user) && decagonLib.getLentRecords().get(user).contains(javaAdvanced);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void acceptReturnedBooks() {
    }
}