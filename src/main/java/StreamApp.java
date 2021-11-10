import java.util.*;

public class StreamApp {



    public static void main(String[] args) {

        List<Person> people = new ArrayList<>(List.of(
                new Person("Macualy", 3),
                new Person("Macualy", 5),
                new Person("Ade", 2),
                new Person("Chidinma", 4),
                new Person("Abu", 5),
                new Person("Tobi", 6)
        ));


        people.stream().sorted(Comparator.comparing(Person::getId))
                .dropWhile(person -> person.getId()<5)
                .forEach(System.out::println);



    }
}
