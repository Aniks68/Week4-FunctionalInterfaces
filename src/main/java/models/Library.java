package models;

import models.LibraryBook;
import models.Person;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import services.LibrarianServices;
import services.serviceimplementation.LibraryUsers;

import java.time.LocalDateTime;
import java.util.*;

public class Library implements Comparable<LibraryUsers.Role> {
    private static Map<LibraryUsers, Set<LibraryBook>> lentRecords = new HashMap<>();
    private static Map<String, Integer> availableBooks = new HashMap<>();
    private static HashMap<LibraryUsers, LibraryBook> appliedList = new HashMap<LibraryUsers, LibraryBook>();
    private static LinkedHashMap<LocalDateTime, LibraryUsers> applyTime = new LinkedHashMap<>();
    private static Map<LibraryUsers, LibraryBook> returningBooks = new HashMap<>();


    public Library() {
    }

    public static Map<LibraryUsers, Set<LibraryBook>> getLentRecords() {
        return lentRecords;
    }

    public static Map<String, Integer> getAvailableBooks() {
        return availableBooks;
    }

    public static HashMap<LibraryUsers, LibraryBook> getAppliedList() {
        return appliedList;
    }

    public static LinkedHashMap<LocalDateTime, LibraryUsers> getApplyTime() {
        return applyTime;
    }

    public static Map<LibraryUsers, LibraryBook> getReturningBooks() {
        return returningBooks;
    }

    @Override
    public int compareTo(LibraryUsers.Role o) {
        return 0;
    }



}

