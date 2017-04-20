import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String KEYWORD_FOR_EXIT = "--stop";

    public static void main(String[] args) {
        System.out.println("Persons loading...");
        PersonsExtracter personsExtracter = new PersonsExtracter().invoke();
        if (personsExtracter.wasError()) {
            System.out.println("App was stopped");
            return;
        }
        System.out.println("Persons were loaded.");
        System.out.println("App was started. Enter '" + KEYWORD_FOR_EXIT + "' to exit.");
        processCommand(personsExtracter);
        System.out.println("App was stopped");
    }

    private static void processCommand(PersonsExtracter personsExtracter) {
        System.out.println("Enter one of these commands '-f' '-s' '-a'...");
        List<Person> persons = personsExtracter.getPersons();
        try (Scanner sc = new Scanner(System.in);) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (isStopTheApp(line)) {
                    break;
                }
                if (line.equals("-f")) {
                    Collections.sort(persons, new FirstNameComparator());
                } else if (line.equals("-s")) {
                    Collections.sort(persons, new SecondNameComparator());
                } else if (line.equals("-a")) {
                    Collections.sort(persons, new AgeComparator());
                }
                printList(persons);
                System.out.println("Enter one of these commands '-f' '-s' '-a'...");
            }
        }
    }

    private static void printList(List<Person> persons) {
        persons.forEach(System.out::println);
    }

    private static boolean isStopTheApp(String line) {
        return line.equals(KEYWORD_FOR_EXIT);
    }
}