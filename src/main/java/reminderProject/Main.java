package reminderProject;

import reminderProject.database.EntityDao;
import reminderProject.database.HibernateUtil;
import reminderProject.model.Employee;
import reminderProject.model.TypeOfContract;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Initial version");
        HibernateUtil.getOurSessionFactory();

        Scanner scanner = new Scanner(System.in);

        String employeeCommand;


        do {
            System.out.println("Write command ");
            System.out.println("Employee add \n" +
                    "Employee show \n" +
                    "Employee find by \n" +
                    "Employee delete \n" +
                    "Quit");

            employeeCommand = scanner.nextLine();
            String[] words = employeeCommand.split(" ");


            if (words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("add")) {
                addEmployee(words);
            } else if (words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("show")) {
                showEmployee(words);
            } else if (words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("find")
                    && words[2].equalsIgnoreCase("by")) {


                System.out.println("id employee \n" +
                        "firstname \n" +
                        "surname \n" +
                        "type of contract \n" +
                        "finish contract \n");
                employeeCommand = scanner.nextLine();
                String[] words2 = employeeCommand.split(" ");

                if (words2[0].equalsIgnoreCase("id")) {
                    findById(words2);
                } else if (words2[0].equalsIgnoreCase("firstname")) {
                    findByName(words2);
                } else if (words2[0].equalsIgnoreCase("surname")) {
                    findBySurname(words2);
                } else if (words2[0].equalsIgnoreCase("type") && words2[1].equalsIgnoreCase("of")
                        && words2[2].equalsIgnoreCase("contract")) {
                    findByTypeOfContract(words2);
                } else if (words2[0].equalsIgnoreCase("finish") && words2[1].equalsIgnoreCase("contract")) {
                    findByFinishContract(words2);
                }




            } else if (words[0].equalsIgnoreCase("employee") && words[1].equalsIgnoreCase("delete")) {
                deleteEmployee(words);
            }
        } while (!employeeCommand.equalsIgnoreCase("quit"));
    }


    private static void deleteEmployee(String[] words) {

        EntityDao<Employee> entityDao = new EntityDao<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which employee do you want to remove");

        System.out.println("name: ");
        String name = scanner.nextLine();

        System.out.println("surname: ");
        String surname = scanner.nextLine();

        Employee employeeToDelete = new Employee(name, surname);

        entityDao
                .delete(employeeToDelete);
    }


    private static void findByFinishContract(String[] words2) {
    }

    private static void findByTypeOfContract(String[] words2) {
    }

    private static void findBySurname(String[] words2) {

        EntityDao<Employee> entityDao = new EntityDao<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write surname: ");
        String surname = scanner.nextLine();

        entityDao
                .findBySurname(Employee.class, surname)
                .stream()
                .forEach(System.out::println);
    }

    private static void findByName(String[] words2) {

        EntityDao<Employee> entityDao = new EntityDao<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write firstname: ");
        String name = scanner.nextLine();
        entityDao
                .findByName(Employee.class, name)
                .stream()
                .forEach(System.out::println);
    }

    private static void findById(String[] words2) {

        EntityDao<Employee> entityDao = new EntityDao<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write id number: ");
        Long idNumber = scanner.nextLong();
        entityDao
                .findById(Employee.class, idNumber)
                .stream()
                .forEach(System.out::println);
    }


    private static void showEmployee(String[] words) {

        EntityDao<Employee> employeeEntityDao = new EntityDao<>();
        employeeEntityDao
                .findAll(Employee.class)
                .stream()
                .forEach(System.out::println);

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
                typeOfContract = TypeOfContract.UMOWA_O_PRACE_OKRES_PROBNY;
                break;
            case 2:
                typeOfContract = TypeOfContract.UMOWA_O_PRACE_NA_CZAS_OKRESLONY;
                break;
            case 3:
                typeOfContract = TypeOfContract.UMOWA_O_PRACE_NA_CZAS_NIEOKRESLONY;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + contract);
        }

        System.out.println("Write date of finish the contract");
        scanner.nextLine();

        System.out.println("Write year");
        int year = scanner.nextInt();
        System.out.println("Write month");
        int month = scanner.nextInt();
        System.out.println("Write day");
        int day = scanner.nextInt();

        Employee employee = new Employee(firstName, surname, typeOfContract, LocalDate.of(year, month, day));
        employeeEntityDao.saveOrUpdate(employee);

    }


}