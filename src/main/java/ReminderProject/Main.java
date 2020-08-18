package ReminderProject;

import ReminderProject.database.EntityDao;
import ReminderProject.database.HibernateUtil;
import ReminderProject.model.Employee;
import ReminderProject.model.TypeOfContract;
import ReminderProject.model.TypeOfReminder;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Initial version");
        HibernateUtil.getOurSessionFactory();

        Scanner scanner = new Scanner(System.in);

        String employeeCommand;

        do {
            System.out.println("Write command ");
            System.out.println("Employee add \n " +
                    "Employee show \n " +
                    "Employee find by \n " +
                    "Employee delete \n" +
                    "Quit");

            employeeCommand = scanner.nextLine();
            String[] words = employeeCommand.split(" ");


            employeeCommand = scanner.nextLine();

            if(words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("add")) {
                addEmployee(words);
            } else if (words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("show")) {
                showEmployee(words);
            } else if (words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("find")
                    && words[2].equalsIgnoreCase("by")){
                findByEmployee(words);
            } else if (words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("delete")) {
                deleteEmployee(words);
            }
        } while (!employeeCommand.equalsIgnoreCase("quit"));

//        Employee FirstEmployee = new Employee("Mariolka", "Blask");
//
//        EntityDao<Employee> employeeEntityDao = new EntityDao<>();
//        employeeEntityDao.saveOrUpdate(FirstEmployee);
    }

    private static void deleteEmployee(String[] words) {
    }

    private static void findByEmployee(String[] words) {
    }

    private static void showEmployee(String[] words) {
    }

    private static void addEmployee(String[] words) {

        EntityDao<Employee> employeeEntityDao = new EntityDao<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write firstname");
        String firstName = scanner.nextLine();

        System.out.println("Write surname");
        String surname = scanner.nextLine();

        System.out.println("Choose type of contract: \n" +
                "1 - umowa o pracę na okres próbny \n" +
                "2 - umowa o pracę na czas określony \n" +
                "3 - umowa o pracę na czas nieokreślony \n ");
        int contract = scanner.nextInt();
        TypeOfContract typeOfContract;
        switch (contract) {
            case 1:
                typeOfContract = TypeOfContract.UMOWA_O_PRACE_OKRES_PRÓBNY;
                break;
            case 2:
                typeOfContract = TypeOfContract.UMOWA_O_PRACE_NA_CZAS_OKRESLONY;
                break;
            case 3:
                typeOfContract = TypeOfContract.UMOWA_O_PRACE_NA_CZAS_NIEOKRESLONY;
            default:
                throw new IllegalStateException("Unexpected value: " + contract);
        }

        System.out.println("Write date of finish the contract");
        System.out.println("Write year");
        int year = scanner.nextInt();
        System.out.println("Write month");
        int month = scanner.nextInt();
        System.out.println("Write day");
        int day = scanner.nextInt();

        employeeEntityDao.saveOrUpdate(new Employee(firstName, surname, typeOfContract, LocalDate.of(year, month,day)));

    }


}