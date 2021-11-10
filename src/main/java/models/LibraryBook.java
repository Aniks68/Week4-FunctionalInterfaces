package models;

import java.util.Map;
import java.util.Objects;


public class LibraryBook {
    private String author;
    private String title;
    private  int copies;

    public LibraryBook(String author, String title, int copies) {
        this.author = author;
        this.title = title;
        this.copies = copies;


    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getCopies() {
        return copies;
    }

    private int copiesCheck(Map<String, Integer> bookList) {
        if (bookList.containsKey(this.title)) {
            int availCopy = bookList.get(this.title);
            bookList.replace(this.title, availCopy+=copies);
        } else bookList.put(this.title, copies);
        return bookList.get(this.title);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryBook)) return false;
        LibraryBook libraryBook = (LibraryBook) o;
        return copies == libraryBook.copies && getAuthor().equals(libraryBook.getAuthor()) && getTitle().equals(libraryBook.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getTitle(), copies);
    }

    @Override
    public String toString() {
        return "LibraryBook{" +
                "title='" + title + '\'' +
                '}';
    }
}
