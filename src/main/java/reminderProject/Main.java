package reminderProject;

import reminderProject.model.EmployeeHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


//        System.out.println("Initial version");
//        HibernateUtil.getOurSessionFactory();
        EmployeeHandler employeeHandler = new EmployeeHandler();

        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.println("Write command: ");
            printAllOption();

            command = scanner.nextLine();
            String[] words = command.split(" ");

            if(words[0].equalsIgnoreCase("employee")){
                employeeHandler.handleEmployee(words);
            } else if(words[0].equalsIgnoreCase("Reminder")) {

            }


        } while (!command.equalsIgnoreCase("quit"));
    }

    private static void printAllOption() {
        System.out.println("Employee");
        System.out.println("Remider ");
    }

    private static void printAllOptionEmployee() {

    }

}