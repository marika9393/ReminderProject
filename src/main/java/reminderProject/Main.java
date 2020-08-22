package reminderProject;

import reminderProject.database.HibernateUtil;
import reminderProject.model.EmployeeHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Initial version");
        HibernateUtil.getOurSessionFactory();
        EmployeeHandler employeeHandler = new EmployeeHandler();
        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.println("Write command: ");
            printAllOption();

            command = scanner.nextLine();
            String[] words = command.split(" ");

            if(words[0].equalsIgnoreCase("employee")){
                employeeHandler.handle(words);
            }

        } while (!command.equalsIgnoreCase("quit"));
    }

    private static void printAllOption() {
        System.out.println("Employee List : ");
        System.out.println("Employee add : {name} {surname} {type of contract} {finish contract}");
        System.out.println("Employee findby: {id} {name} {surname} {type of contract} {finish contract}");
        System.out.println("Quit");
    }

}