package models;

import services.serviceimplementation.LibraryUsers;

import java.time.LocalDateTime;
import java.util.Comparator;

public class BookRequest {
    private LibraryUsers lender;
    private String bookName;

    private LocalDateTime timeBorrowed;

    public BookRequest(LibraryUsers lender, LocalDateTime timeBorrowed) {
        this.lender = lender;
        this.timeBorrowed = LocalDateTime.now();
    }

    public LibraryUsers getLender() {
        return lender;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDateTime getTimeBorrowed() {
        return timeBorrowed;
    }

    class UserTimeCompare implements Comparator<BookRequest> {

        @Override
        public int compare(BookRequest bk1, BookRequest bk2) {
            return bk1.getTimeBorrowed().compareTo(bk2.getTimeBorrowed());
        }
    }


}
