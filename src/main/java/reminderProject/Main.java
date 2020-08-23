package reminderProject;

import reminderProject.database.EntityDao;
import reminderProject.model.Employee;
import reminderProject.model.EmployeeHandler;
import reminderProject.model.RemiderHandler;
import reminderProject.model.ReminderEmployee;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EmployeeHandler employeeHandler = new EmployeeHandler();
        RemiderHandler remiderHandler = new RemiderHandler();

        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("Write command: ");
            printFirstOption();

            command = scanner.nextLine();

            if (command.equalsIgnoreCase("employee")) {
                employeeHandler.handleEmployee();
            } else if (command.equalsIgnoreCase("reminder")) {
                remiderHandler.handleRemidnder();
            }

        } while (!command.equalsIgnoreCase("quit"));
    }



    private static void printFirstOption() {
        System.out.println("Employee");
        System.out.println("Reminder");
        System.out.println("[Quit]");
    }
}