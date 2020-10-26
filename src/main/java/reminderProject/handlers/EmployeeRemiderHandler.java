package reminderProject.handlers;

import reminderProject.database.EntityDao;
import reminderProject.database.EmployeeReminderDao;
import reminderProject.model.Employee;
import reminderProject.model.EmployeeReminder;
import reminderProject.model.ReminderPeriod;
import reminderProject.model.EmployeeReminderType;


import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeRemiderHandler {

    private Scanner scanner = new Scanner(System.in);
    private EntityDao<Employee> employeeEntityDao = new EntityDao<>();
    private EntityDao<EmployeeReminder> reminderEmployeeEntityDao = new EntityDao<>();
    private EmployeeReminderDao reminderEmployeeDao = new EmployeeReminderDao();


    public void handleRemidnder() {
        System.out.println("Write command: ");
        printReminderCommand();
        String command = scanner.nextLine();


        if (command.equalsIgnoreCase("list")) {
            showRemider();
        } else if (command.equalsIgnoreCase("add")) {
            addRemider();
        } else if (command.equalsIgnoreCase("findBy")) {
            System.out.println("id reminder \n" +
                    "type \n" +
                    "termination");
            String commandFindBy = scanner.nextLine();

            if (commandFindBy.equalsIgnoreCase("id")) {
                findByIdReminder();
            } else if (commandFindBy.equalsIgnoreCase("type")) {
                findByTypeOfReminder();
            } else if (commandFindBy.equalsIgnoreCase("termination")) {
                findByDateOfReminder();
            }
        } else if (command.equalsIgnoreCase("addedToEmployee")) {
            handleAddReminderToEmployee();
        } else if (command.equalsIgnoreCase("delete")) {
            deleteReminder();
        }

    }

    private void printReminderCommand() {
        System.out.println("Reminder - [List]: ");
        System.out.println("Remider - [add]: ");
        System.out.println("Reminder - [findBy]: ");
        System.out.println("Reminder - [addedToEmployee]: ");
        System.out.println("Remider - [delete]: ");

    }

    private void deleteReminder() {
        EntityDao<EmployeeReminder> entityDao = new EntityDao<>();
        System.out.println("Which car reminder to delete - enter the car reminder id:");
        Long idNumber = Long.parseLong(scanner.nextLine());

        Optional<EmployeeReminder> reminderToDelete = entityDao.findById(EmployeeReminder.class, idNumber);
        if (reminderToDelete.isPresent()) {
            EmployeeReminder reminderEmployee = reminderToDelete.get();
            System.out.println("Employee reminder deleted");
            entityDao.delete(reminderEmployee);
        } else {
            System.out.println("Not found reminder to delete");
        }
    }

    private void findByDateOfReminder() {
        System.out.println("Write date of reminder: write \n[YEAR] \n[MONTH] \n[DAY] ");
        LocalDate data = LocalDate.of(Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));

        List<EmployeeReminder> resultReminderList = reminderEmployeeDao.findByDateOfReminder(data);

        if (resultReminderList.stream().findFirst().isPresent()) {
            System.out.println("Reminder found: ");
            resultReminderList.forEach(System.out::println);
        } else
            System.out.println("Reminder not found");
    }

    private void findByTypeOfReminder() {
        System.out.println("Choose type of reminder:  \n" +
                "UBEZPIECZENIE_ZUS - ZUS,\n" +
                "WYPLATA - wyplata,\n" +
                "PODATEK_OD_WYNAGRODZENIA - podatek,\n" +
                "DELEGACJA - delegacja,\n" +
                "BADANIA_OKRESOWE - badania,\n" +
                "LISTA_OBECNOSCI - lista;");

        boolean error = true;

        do {
            try {
                EmployeeReminderType typeOfReminder = EmployeeReminderType.valueOfShortReminder(scanner.nextLine());

                List<EmployeeReminder> resultReminderList = reminderEmployeeDao.findByTypeOfReminder(typeOfReminder);
                error = false;

                if ((resultReminderList.stream().findFirst().isPresent())) {
                    System.out.println("Reminder found: ");
                    resultReminderList.forEach(System.out::println);
                } else
                    System.out.println("Reminder not found");
            } catch (InputMismatchException e) {
                System.out.println("Write the correct type ");
                scanner.nextLine();
            }
        } while (error);
    }

    private void findByIdReminder() {

        EntityDao<EmployeeReminder> entityDao = new EntityDao<>();

        System.out.println("Write id number: ");
        Long idNumber = Long.parseLong(scanner.nextLine());

        Optional<EmployeeReminder> resultReminderEmployee = entityDao
                .findById(EmployeeReminder.class, idNumber);

        if (resultReminderEmployee.isPresent()) {
            System.out.println("Found remidner: " + resultReminderEmployee);
        } else
            System.out.println("Reminder not found");
    }

    private void addRemider() {
        EntityDao<EmployeeReminder> reminderEntityDao = new EntityDao<>();

        System.out.println("Choose type of reminder: \n" +
                "1- ubezpieczenie ZUS \n" +
                "2 - wypłata \n" +
                "3 - podatek od wynagrodzenia \n" +
                "4 - delegacja \n" +
                "5 - badania okresowe \n" +
                "6- lista obecności");

        int type = Integer.parseInt(scanner.nextLine());
        EmployeeReminderType typeOfReminder;
        switch (type) {
            case 1:
                typeOfReminder = EmployeeReminderType.UBEZPIECZENIE_ZUS;
                break;
            case 2:
                typeOfReminder = EmployeeReminderType.WYPLATA;
                break;
            case 3:
                typeOfReminder = EmployeeReminderType.PODATEK_OD_WYNAGRODZENIA;
                break;
            case 4:
                typeOfReminder = EmployeeReminderType.DELEGACJA;
            case 5:
                typeOfReminder = EmployeeReminderType.BADANIA_OKRESOWE;
                break;
            case 6:
                typeOfReminder = EmployeeReminderType.LISTA_OBECNOSCI;
                break;
            default:
                throw new IllegalStateException("Unexpected value" + type);
        }

        System.out.println("Write amount");
        int timeofAmount = Integer.parseInt(scanner.nextLine());

        System.out.println("Write date: ");
        System.out.println("YEAR");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("MONTH");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("DAY");
        int day = Integer.parseInt(scanner.nextLine());

        System.out.println("Choose period of reminder \n" +
                "1 - YEAR \n" +
                "2 - MONTH \n" +
                "3 - WEEK \n" +
                "4 - DAY \n" +
                "5 - NONE");
        int period = Integer.parseInt(scanner.nextLine());
        ReminderPeriod reminderPeriod;
        switch (period) {
            case 1:
                reminderPeriod = ReminderPeriod.YEAR;
                break;
            case 2:
                reminderPeriod = ReminderPeriod.MOUNTH;
                break;
            case 3:
                reminderPeriod = ReminderPeriod.WEEK;
                break;
            case 4:
                reminderPeriod = ReminderPeriod.DAY;
                break;
            case 5:
                reminderPeriod = ReminderPeriod.NONE;
                break;
            default:
                throw new IllegalStateException("Unexepcted value: " + period);
        }
        String input;
        System.out.println("Do you want add Employee? [y/n] ");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            Employee employee = askUserForEmployee();

            EmployeeReminder reminderEmployee = new EmployeeReminder(typeOfReminder, timeofAmount, LocalDate.of(year, month, day), reminderPeriod);
            reminderEmployee.setEmployee(employee);
            reminderEntityDao.saveOrUpdate(reminderEmployee);
            System.out.println("Remider with employee added");
        } else if (input.equalsIgnoreCase("n")) {
            EmployeeReminder reminderEmployee = new EmployeeReminder(typeOfReminder, timeofAmount, LocalDate.of(year, month, day), reminderPeriod);
            reminderEntityDao.saveOrUpdate(reminderEmployee);
            System.out.println("Remider added");
        }
    }


    private Employee askUserForEmployee() {
        Employee employee = null;

        do {
            System.out.println("This is a list of employee");
            employeeEntityDao
                    .findAll(Employee.class)
                    .forEach(System.out::println);
            System.out.println();
            System.out.println("Provide employee id: ");

            Long idEmployee = Long.parseLong(scanner.nextLine());
            Optional<Employee> optionalEmployee = employeeEntityDao.findById(Employee.class, idEmployee);
            if (optionalEmployee.isPresent()) {
                employee = optionalEmployee.get();
            }
        } while (employee == null);
        return employee;
    }

    private void handleAddReminderToEmployee() {
        Employee employee = null;
        EmployeeReminder reminderEmployee = null;
        do {
            System.out.println("List of employees:");
            employeeEntityDao.findAll(Employee.class)
                    .forEach(System.out::println);

            System.out.println("Podaj id pracownika:");
            Long id = Long.parseLong(scanner.next());

            Optional<Employee> employeeOptional = employeeEntityDao.findById(Employee.class, id);
            if (employeeOptional.isPresent()) {
                employee = employeeOptional.get();
            }
        } while (employee == null);

        System.out.println("Pracownik wybrany");

        do {
            System.out.println("List of reminders:");
            reminderEmployeeEntityDao.findAll(EmployeeReminder.class)
                    .forEach(System.out::println);

            System.out.println("Podaj id:");
            Long id = Long.parseLong(scanner.next());

            Optional<EmployeeReminder> reminderEmployeeOptional = reminderEmployeeEntityDao.findById(EmployeeReminder.class, id);
            if (reminderEmployeeOptional.isPresent()) {
                reminderEmployee = reminderEmployeeOptional.get();
            }
        } while (reminderEmployee == null);
        System.out.println("Reminder wybrany.");

        reminderEmployee.setEmployee(employee);
        reminderEmployeeEntityDao.saveOrUpdate(reminderEmployee);
    }

    private void showRemider() {
        EntityDao<EmployeeReminder> entityDao = new EntityDao<>();

        entityDao
                .findAll(EmployeeReminder.class)
                .stream()
                .forEach(System.out::println);
    }
}
