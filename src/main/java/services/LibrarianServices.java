package services;

import models.LibraryBook;
import services.serviceimplementation.LibraryUsers;
import java.util.Map;
import java.util.function.BiConsumer;

public interface LibrarianServices {

    BiConsumer<Map<String,Integer>, LibraryBook> addBook = (bookList, book) -> {
        if (bookList.containsKey(book.getTitle())) {
            int availCopy = bookList.get(book.getTitle());
            bookList.replace(book.getTitle(), availCopy+=book.getCopies());
        } else bookList.put(book.getTitle(), book.getCopies());
        System.out.println(book.getTitle() + " has been added.");
    };

    BiConsumer<Map<String,Integer>, LibraryBook> updateAvailCopies = (bookList, book) -> {
        try {
            if (bookList.containsKey(book.getTitle())) {
                int availCopy = bookList.get(book.getTitle());
                bookList.replace(book.getTitle(), availCopy-1);
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    };

    void lendBook();

    void acceptReturnedBooks();
}
