package services.serviceimplementation;

import models.LibraryBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.serviceimplementation.Library.Librarian;

import static org.junit.jupiter.api.Assertions.*;
import static services.serviceimplementation.LibraryUsers.Role.SENIOR_STUDENT;

class LibraryUsersTest {
    LibraryUsers user;
    Librarian librarian;
    Library decagonLib;
    LibraryBook javaAdvanced;

    @BeforeEach
    void setUp() {
        user = new LibraryUsers("Ella", "Velasco", SENIOR_STUDENT);
        librarian = new Librarian("Robin", "Silva");
        decagonLib = new Library();
        javaAdvanced = new LibraryBook("Amigoscode", "Java for Pros", 4);
    }

    @Test
    @DisplayName("To test if the applied list contains borrower")
    void borrowABook() {
        // Given

        librarian.addABook(javaAdvanced);
        user.borrowABook(javaAdvanced, librarian);

         final boolean expectedResult = true;
         final boolean actualResult = decagonLib.getAppliedList().containsKey(user);

         assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("To check if the return list contains user and book being returned")
    void returnBook() {
        // Given

        librarian.addABook(javaAdvanced);
        user.borrowABook(javaAdvanced, librarian);
        librarian.lendBook();
        user.returnBook(javaAdvanced);

        final boolean expectedResult = true;
        final boolean actualResult = decagonLib.getReturningBooks().containsKey(user)
                && decagonLib.getReturningBooks().get(user) == javaAdvanced;

        assertEquals(expectedResult, actualResult);
    }
}