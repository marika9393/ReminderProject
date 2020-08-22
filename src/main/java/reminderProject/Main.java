package reminderProject;

import reminderProject.model.EmployeeHandler;
import reminderProject.model.RemiderHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EmployeeHandler employeeHandler = new EmployeeHandler();
        RemiderHandler remiderHandler = new RemiderHandler();

        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.println("Write command: ");
            printAllOption();

            command = scanner.nextLine();
            String[] words = command.split(" ");

            if (words[0].equalsIgnoreCase("employee")) {

                employeeHandler.handleEmployee(words);
            } else if (words[0].equalsIgnoreCase("Reminder")) {
                remiderHandler.handleRemidnder(words);
            }

        } while (!command.equalsIgnoreCase("quit"));
    }

    private static void printAllOption() {
        System.out.println("[Employee List] : ");
        System.out.println("[Employee add] : {name} {surname} {type of contract} {finish contract}");
        System.out.println("[Employee findby]: {id} {name} {surname} {type of contract} {finish contract}");
        System.out.println("[Employee Delet]");
        System.out.println("[Reminder List]: ");
        System.out.println("[Remider add]: {type of remider} {amound} {date of remider} {period}");
        System.out.println("[Reminder findBy] {id} {type of remider} {date of remider}");
        System.out.println("[Remider delete]");
    }
}