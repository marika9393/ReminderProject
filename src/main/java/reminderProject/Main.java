package reminderProject;

import reminderProject.handlers.CarHandler;
import reminderProject.handlers.CarReminderHandler;
import reminderProject.handlers.EmployeeHandler;
import reminderProject.handlers.EmployeeRemiderHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EmployeeHandler employeeHandler = new EmployeeHandler();
        EmployeeRemiderHandler employeeRemiderHandler = new EmployeeRemiderHandler();
        CarHandler carHandler = new CarHandler();
        CarReminderHandler carReminderHandler = new CarReminderHandler();

        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("Write command: ");
            printFirstOption();

            command = scanner.nextLine();

            if (command.equalsIgnoreCase("employee")) {
                employeeHandler.handleEmployee();
            } else if (command.equalsIgnoreCase("employeereminder")) {
                employeeRemiderHandler.handleRemidnder();
            } else if (command.equalsIgnoreCase("car")) {
                carHandler.handle();
            } else if (command.equalsIgnoreCase("carreminder")) {
                carReminderHandler.handlerReminder();
            }

        } while (!command.equalsIgnoreCase("quit"));
    }


    private static void printFirstOption() {
        System.out.println("Employee");
        System.out.println("EmployeeReminder");
        System.out.println("Car");
        System.out.println("CarReminder");
        System.out.println("[Quit]");
    }
}