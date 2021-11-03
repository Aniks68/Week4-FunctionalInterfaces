package services.serviceimplementation;

import enums.Role;
import models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {

    Librarian librarian;
    @BeforeEach
    void setUp() {
        librarian = new Librarian("Joe", "Smith");
    }

    @Test
    @DisplayName("To add books to the library list")
    void addBook() {
        // Given
        Library decagonLib = new Library();
        LibraryBook biology = new LibraryBook("Dr Martins", "Modern Biology", 11);
        LibraryBook chemistry = new LibraryBook("Agawa", "New School Chemistry", 6);

        librarian.addBook(decagonLib.getAvailableBooks(), biology);
        librarian.addBook(decagonLib.getAvailableBooks(), chemistry);
        final int expectedResult = 2;

        final int actualResult = decagonLib.getAvailableBooks().size();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("To try lending a book")
    void lendBook() {
        // Given
        Library decagonLib = new Library();
        LibraryUsers prosper = new LibraryUsers("Prosper", "Amalaha", Role.JUNIOR_STUDENT);
        LibraryUsers mark = new LibraryUsers("Mark", "Marve", Role.TEACHER);

        LibraryBook biology = new LibraryBook("Dr Martins", "Modern Biology", 11);
        LibraryBook chemistry = new LibraryBook("Agawa", "New School Chemistry", 6);

        librarian.lendBook(biology, mark);
        librarian.lendBook(chemistry, prosper);

        final int expectedResult = 2;
        final int actualResult = decagonLib.getLentRecords().size();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    @DisplayName("To check available books after lending")
    void updateAvailCopies() {
        Library decagonLib = new Library();
        LibraryUsers prosper = new LibraryUsers("Prosper", "Amalaha", Role.JUNIOR_STUDENT);
        LibraryUsers mark = new LibraryUsers("Mark", "Marve", Role.TEACHER);

//        LibraryBook biology = new LibraryBook("Dr Martins", "Modern Biology", 11);
        LibraryBook chemistry = new LibraryBook("Agawa", "New School Chemistry", 6);

        librarian.addBook(decagonLib.getAvailableBooks(), chemistry);
        librarian.addBook(decagonLib.getAvailableBooks(), chemistry);

        librarian.lendBook(chemistry, mark);
        librarian.lendBook(chemistry, prosper);

        final int expectedResult = 26;
        final int actualResult = decagonLib.getAvailableBooks().get(chemistry.getTitle());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("To collect returned books")
    void acceptReturnedBooks() {
        // Given

        Library decagonLib = new Library();
        LibraryUsers prosper = new LibraryUsers("Prosper", "Amalaha", Role.JUNIOR_STUDENT);
        LibraryUsers mark = new LibraryUsers("Mark", "Marve", Role.TEACHER);

        LibraryBook biology = new LibraryBook("Dr Martins", "Modern Biology", 11);
        LibraryBook chemistry = new LibraryBook("Agawa", "New School Chemistry", 6);

        librarian.addBook(decagonLib.getAvailableBooks(), chemistry);
        librarian.addBook(decagonLib.getAvailableBooks(), chemistry);

        librarian.lendBook(chemistry, mark);
        librarian.lendBook(chemistry, prosper);

        librarian.acceptReturnedBooks(mark, chemistry);

        final int expectedResult = 1;
        final int actualResult = decagonLib.getLentRecords().size();

        assertEquals(expectedResult, actualResult);
    }
}