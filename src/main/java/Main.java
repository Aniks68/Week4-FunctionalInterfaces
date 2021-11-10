import models.Library;
import models.LibraryBook;
import services.serviceimplementation.Librarian;
import services.serviceimplementation.LibraryUsers;

import static services.serviceimplementation.LibraryUsers.Role.*;

//import static models.Library.availableBooks;

public class Main {
    public static void main(String[] args) {

        Library decagonLib = new Library();
        LibraryUsers ikay = new LibraryUsers("Ikechukwu", "Anene", SENIOR_STUDENT);
        LibraryUsers emekus = new LibraryUsers("Emeka", "Chukwudozie", SENIOR_STUDENT);
        LibraryUsers prosper = new LibraryUsers("Prosper", "Amalaha", JUNIOR_STUDENT);
        LibraryUsers mark = new LibraryUsers("Mark", "Marve", TEACHER);
        Librarian joe = new Librarian("Joe", "Matthew");

        LibraryBook javaNote = new LibraryBook("Elvis", "Java for JJC", 8);
        LibraryBook ios = new LibraryBook("Usman", "iOS for JJC", 11);
        LibraryBook node = new LibraryBook("Chima", "Node.js for JJC", 3);
        LibraryBook javaAdvanced = new LibraryBook("Prosper", "Java for Pros", 5);

        joe.addABook(javaNote);
        System.out.println(decagonLib.getAvailableBooks().size());
        joe.addABook(node);
        joe.addABook(ios);
        joe.addABook(javaAdvanced);
        System.out.println("========================");

        prosper.borrowABook(node, joe);
        mark.borrowABook(node, joe);
        emekus.borrowABook(node, joe);

        ikay.borrowABook(node, joe);
        joe.lendBook();


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

//        for (LibraryBook s : ft) System.out.println(s);
        System.out.println("This is the old set; " + prosper.getBorrowedBooks());

        System.out.println(decagonLib.getLentRecords());
        prosper.returnBook(node);
        joe.acceptReturnedBooks();
        System.out.println("This is the new set; " + prosper.getBorrowedBooks());
        System.out.println(decagonLib.getLentRecords());
        prosper.borrowABook(javaNote, joe);
        joe.lendBook();
        System.out.println("This is the new set; " + prosper.getBorrowedBooks());
        System.out.println(decagonLib.getLentRecords());
//        System.out.println(decagonLib.getAvailableBooks().get(javaNote.getTitle()));
//        System.out.println(decagonLib.getLentRecords().size());
//        System.out.println(prosper.getBorrowedBooks().size());



    }
}
