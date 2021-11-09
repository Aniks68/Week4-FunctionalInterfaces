import services.serviceimplementation.Library;
import services.serviceimplementation.LibraryBook;
import services.serviceimplementation.LibraryUsers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static services.serviceimplementation.LibraryUsers.Role.*;

//import static services.serviceimplementation.Library.availableBooks;

public class Main {
    public static void main(String[] args) {

        Library decagonLib = new Library();
        LibraryUsers ikay = new LibraryUsers("Ikechukwu", "Anene", SENIOR_STUDENT);
        LibraryUsers emekus = new LibraryUsers("Emeka", "Chukwudozie", SENIOR_STUDENT);
        LibraryUsers prosper = new LibraryUsers("Prosper", "Amalaha", JUNIOR_STUDENT);
        LibraryUsers mark = new LibraryUsers("Mark", "Marve", TEACHER);
        Library.Librarian joe = new Library.Librarian("Joe", "Matthew");

        LibraryBook javaNote = new LibraryBook("Elvis", "Java for JJC", 8);
        LibraryBook ios = new LibraryBook("Usman", "iOS for JJC", 11);
        LibraryBook node = new LibraryBook("Chima", "Node.js for JJC", 3);
        LibraryBook javaAdvanced = new LibraryBook("Prosper", "Java for Pros", 5);

        joe.addBook(decagonLib.getAvailableBooks(), javaNote);
        System.out.println(decagonLib.getAvailableBooks().size());
        joe.addBook(decagonLib.getAvailableBooks(), node);
        joe.addBook(decagonLib.getAvailableBooks(), ios);
        joe.addBook(decagonLib.getAvailableBooks(), javaAdvanced);
        System.out.println(decagonLib.getAvailableBooks().size());
//
        System.out.println(decagonLib.getAvailableBooks().get(javaNote.getTitle()));
//        System.out.println(prosper.getBorrowedBooks().size());
        prosper.borrowABook(node, joe);
        mark.borrowABook(node, joe);
        emekus.borrowABook(node, joe);
        ikay.borrowABook(node, joe);
//        System.out.println("The first person is: " + joe.requestQueue().poll());
        joe.lendBook();
//        joe.lendBook();
//        joe.lendBook();
//        joe.lendBook();
//        System.out.println(decagonLib.getLentRecords().size());


        System.out.println("========================");
//        joe.lendBook(javaNote, prosper);
//        joe.lendBook(ios, emekus);
//        joe.lendBook(node, mark);
//        joe.lendBook(javaAdvanced, ikay);


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

//        for (LibraryBook s : ft) System.out.println(s);
        System.out.println("This is the old set; " + prosper.getBorrowedBooks());

        System.out.println(decagonLib.getLentRecords());
        joe.acceptReturnedBooks(prosper, javaNote);
        System.out.println("This is the new set; " + prosper.getBorrowedBooks());
        System.out.println(decagonLib.getLentRecords());
//        System.out.println(decagonLib.getAvailableBooks().get(javaNote.getTitle()));
//        System.out.println(decagonLib.getLentRecords().size());
//        System.out.println(prosper.getBorrowedBooks().size());

//        prosper.borrowABook(javaAdvanced, joe);
//        mark.borrowABook(javaNote, joe);
//        ikay.borrowABook(node, joe);
//        emekus.borrowABook(ios, joe);
//        mark.borrowABook(node, joe);
//        System.out.println("=============");
//        System.out.println(joe.requestQueue());
//        joe.lendBook(javaNote, mark);
//        joe.lendBook(node, prosper);

    }
}
