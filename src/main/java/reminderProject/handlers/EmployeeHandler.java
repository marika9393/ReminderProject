package reminderProject.handlers;

import reminderProject.database.EmployeeDao;
import reminderProject.database.EntityDao;
import reminderProject.database.ReminderEmployeeDao;
import reminderProject.model.Employee;
import reminderProject.model.TypeOfContract;

import java.time.LocalDate;
import java.util.*;

public class EmployeeHandler {

    private Scanner scanner = new Scanner(System.in);
    private EmployeeDao employeeDao = new EmployeeDao();
    private EntityDao<Employee> entityDaoEmployee = new EntityDao<>();

    String command;

    public void handleEmployee() {
        System.out.println("Write command: ");
        printEmployeeCommand();
        command = scanner.nextLine();


        if (command.equalsIgnoreCase("list")) {
            showEmployees();
        } else if (command.equalsIgnoreCase("add")) {
            addEmployee();

        } else if (command.equalsIgnoreCase("findby")) {
            System.out.println("id employee \n" +
                    "firstname \n" +
                    "surname \n" +
                    "contract \n" +
                    "termination \n");
            String commandFindBy = scanner.nextLine();

            if (commandFindBy.equalsIgnoreCase("id")) {
                findByIdEmployee();
            } else if (commandFindBy.equalsIgnoreCase("firstname")) {
                findByNameEmployee();
            } else if (commandFindBy.equalsIgnoreCase("surname")) {
                findBySurname();
            } else if (commandFindBy.equalsIgnoreCase("contract")) {
                findByTypeOfContractEmployee();
            } else if (commandFindBy.equalsIgnoreCase("termination")) {
                findByFinishContract();
            }

        } else if (command.equalsIgnoreCase("delete")) {
            deleteEmployee();
        }
    }

    private static void printEmployeeCommand() {
        System.out.println("[Employee [list] : ");
        System.out.println("[Employee [add] : ");
        System.out.println("[Employee [findby]:");
        System.out.println("[Employee[delete]");
    }


    private void deleteEmployee() {
        EntityDao<Employee> entityDao = new EntityDao<>();
        System.out.println("Which employee do you want delete ?");
        Long idNumber = Long.parseLong(scanner.nextLine());

        Optional<Employee> employeeToDelete = entityDao.findById(Employee.class, idNumber);
        if (employeeToDelete.isPresent()) {
            Employee employee = employeeToDelete.get();
            System.out.println("Delete employee: ");
            entityDao.delete(employee);
        } else {
            System.out.println("Not found the employee");
        }
    }


    private void findByFinishContract() {
        System.out.println("Choose employee with the date of finish contract to find: ");
        LocalDate data = LocalDate.of(Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));

        List<Employee> resultEmployeeList = employeeDao.findByFinishContract(data);
        if (resultEmployeeList.stream().findFirst().isPresent()) {
            System.out.println("Employee found: ");
            resultEmployeeList.forEach(System.out::println);
        } else
            System.out.println("Employee not found");
    }

    private void findByTypeOfContractEmployee() {
        System.out.println("Choose type of contract to find: \n" +
                "umowa o pracę na okres próbny - probny \n" +
                "umowa o pracę na czas określony - okreslony\n" +
                "umowa o pracę na czas nieokreślony - nieokreslony");
        boolean error = true;

        do {
            try {
                TypeOfContract typeOfContract = TypeOfContract.valueOfShort(scanner.nextLine().trim().toLowerCase());

                List<Employee> resultEmployeeList = employeeDao.findByTypeOfContract(typeOfContract);
                error = false;
                if (resultEmployeeList.stream().findFirst().isPresent()) {
                    System.out.println("Found employee: ");
                    resultEmployeeList.forEach(System.out::println);
                } else
                    System.out.println("Employee not found");
            } catch (InputMismatchException e) {
                System.out.println("Write the correct type: ");
                scanner.nextLine();
            }
        } while (error);
    }

    private void findBySurname() {
        System.out.println("Write surname: ");
        String surname = scanner.nextLine();

        List<Employee> resulEmployeeList = employeeDao.findBySurname(surname);
        if (resulEmployeeList
                .stream()
                .findFirst()
                .isPresent()) {
            System.out.println("Found employee");
            resulEmployeeList.forEach(System.out::println);
        } else
            System.out.println("Employee not found");
    }

    private void findByNameEmployee() {
        System.out.println("Write firstname: ");
        String name = scanner.nextLine();

        // TODO: zapytanie do bd w dao
        List<Employee> resulEmployeeList = employeeDao.findByName(name);
        if (resulEmployeeList
                .stream()
                .findFirst()
                .isPresent()) {
            System.out.println("Found employee: ");
            resulEmployeeList.forEach(System.out::println);
        } else
            System.out.println("Employee not found");
    }

    private void findByIdEmployee() {
        EntityDao<Employee> entityDao = new EntityDao<>();

        System.out.println("Write id number: ");
        Long idNumber = Long.parseLong(scanner.nextLine());

        Optional<Employee> resultEmployeeOptional = entityDao
                .findById(Employee.class, idNumber);

        if (resultEmployeeOptional.isPresent()) {
            System.out.println("Found employee: " + resultEmployeeOptional.get());
        } else
            System.out.println("Employee not found");
    }


    private void addEmployee() {
        EntityDao<Employee> employeeEntityDao = new EntityDao<>();


        System.out.println("Write firstname");
        String firstName = scanner.nextLine();

        System.out.println("Write surname");
        String surname = scanner.nextLine();

        System.out.println("Choose type of contract: \n" +
                "1 - umowa o pracę na okres próbny \n" +
                "2 - umowa o pracę na czas określony \n" +
                "3 - umowa o pracę na czas nieokreślony \n ");
        int contract = Integer.parseInt(scanner.nextLine());
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

        Employee employee;

        if (typeOfContract != TypeOfContract.UMOWA_O_PRACE_NA_CZAS_NIEOKRESLONY) {
            System.out.println("Write year");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.println("Write month");
            int month = Integer.parseInt(scanner.nextLine());
            System.out.println("Write day");
            int day = Integer.parseInt(scanner.nextLine());
            employee = new Employee(firstName, surname, typeOfContract, LocalDate.of(year, month, day));
        } else {
            employee = new Employee(firstName, surname, typeOfContract, null);
        }

        employeeEntityDao.saveOrUpdate(employee);


    }


    private void showEmployees() {
        EntityDao<Employee> employeeEntityDao = new EntityDao<>();
        employeeEntityDao
                .findAll(Employee.class)
                .stream()
                .forEach(System.out::println);

    }


}
