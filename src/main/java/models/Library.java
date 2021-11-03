package models;

import enums.Role;
import services.serviceimplementation.LibraryBook;
import services.serviceimplementation.LibraryUsers;

import java.time.LocalDateTime;
import java.util.*;

public class Library implements Comparable<Role> {
    public static Map<LibraryUsers, String> lentRecords = new HashMap<>();
    public static Map<String, Integer> availableBooks = new HashMap<>();
    public static HashMap<LibraryUsers, LibraryBook> appliedList = new HashMap<LibraryUsers, LibraryBook>();
    public static LinkedHashMap<LocalDateTime, LibraryUsers> applyTime = new LinkedHashMap<>();
    public static Map<LibraryUsers, String> returningBooks = new HashMap<>();

    public Library() {
    }

    public static Map<LibraryUsers, String> getLentRecords() {
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

    public static Map<LibraryUsers, String> getReturningBooks() {
        return returningBooks;
    }

    @Override
    public int compareTo(Role o) {
        return 0;
    }

    private static Queue<Role> bookRequestPriorityQueue =
            new PriorityQueue<>(new Comparator<Role>() {
                @Override
                public int compare(Role o1, Role o2) {
                    final int user1Priority = o1.getPriority();
                    final int user2Priority = o2.getPriority();
                    if (user1Priority < user2Priority) {
                        return -1;
                    } else if (user1Priority > user2Priority) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
}
