import enums.Role;
import models.Library;
import services.serviceimplementation.Librarian;
import services.serviceimplementation.LibraryBook;
import services.serviceimplementation.LibraryUsers;

//import static models.Library.availableBooks;

public class Main {
    public static void main(String[] args) {

        Library decagonLib = new Library();
        LibraryUsers ikay = new LibraryUsers("Ikechukwu", "Anene", Role.SENIOR_STUDENT);
        LibraryUsers emekus = new LibraryUsers("Emeka", "Chukwudozie", Role.SENIOR_STUDENT);
        LibraryUsers prosper = new LibraryUsers("Prosper", "Amalaha", Role.JUNIOR_STUDENT);
        LibraryUsers mark = new LibraryUsers("Mark", "Marve", Role.TEACHER);
        Librarian joe = new Librarian("Joe", "Matthew");



        LibraryBook javaNote = new LibraryBook("Elvis", "Java for JJC", 8);
        LibraryBook ios = new LibraryBook("Usman", "iOS for JJC", 11);
        LibraryBook node = new LibraryBook("Chima", "Node.js for JJC", 4);
        LibraryBook javaAdvanced = new LibraryBook("Prosper", "Java for JJC", 5);

        mark.borrowABook(javaNote);
        prosper.borrowABook(javaAdvanced);
        ikay.borrowABook(node);
        emekus.borrowABook(ios);
        mark.borrowABook(node);
        joe.lendBook(javaNote, mark);
        joe.lendBook(node, prosper);


        decagonLib = new Library();



    }
}
