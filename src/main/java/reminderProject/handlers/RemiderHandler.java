package reminderProject.handlers;

import com.mysql.cj.xdevapi.SchemaImpl;
import reminderProject.database.EntityDao;
import reminderProject.database.ReminderEmployeeDao;
import reminderProject.model.Employee;
import reminderProject.model.PeriodOfReminder;
import reminderProject.model.ReminderEmployee;
import reminderProject.model.TypeOfReminder;

import java.sql.PseudoColumnUsage;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RemiderHandler {

    private Scanner scanner = new Scanner(System.in);
    private EntityDao<Employee> employeeEntityDao = new EntityDao<>();
    private EntityDao<ReminderEmployee> reminderEmployeeEntityDao = new EntityDao<>();
    private ReminderEmployeeDao reminderEmployeeDao = new ReminderEmployeeDao();


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
        } else if (command.equalsIgnoreCase("delete")) {
            deleteReminder();
        }

    }

    private void printReminderCommand() {
        System.out.println("Reminder [List]: ");
        System.out.println("Remider [add]: ");
        System.out.println("Reminder [findBy] ");
        System.out.println("Remider [delete]");

    }

    private void deleteReminder() {
        EntityDao<ReminderEmployee> entityDao = new EntityDao<>();
        Long idNumber = Long.parseLong(scanner.nextLine());

        Optional<ReminderEmployee> reminderToDelete = entityDao.findById(ReminderEmployee.class, idNumber);
        if (reminderToDelete.isPresent()) {
            ReminderEmployee reminderEmployee = reminderToDelete.get();
            System.out.println("Delete Reminder");
            entityDao.delete(reminderEmployee);
        } else {
            System.out.println("Not found reminder to delete");
        }
    }

    private void findByDateOfReminder() {
        System.out.println("Choose date of reminder: ");
        LocalDate data = LocalDate.of(Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));

        List<ReminderEmployee> resultRemiderList = reminderEmployeeDao.findBydateOdReminder(data);

        if (resultRemiderList.stream().findFirst().isPresent()) {
            System.out.println("Reminder found: ");
            resultRemiderList.forEach(System.out::println);
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
                TypeOfReminder typeOfReminder = TypeOfReminder.valueOfShortReminder(scanner.nextLine());

                List<ReminderEmployee> resultReminderList = reminderEmployeeDao.findByTypeOfReminder(typeOfReminder);
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

        EntityDao<ReminderEmployee> entityDao = new EntityDao<>();

        System.out.println("Write id number: ");
        Long idNumber = Long.parseLong(scanner.nextLine());

        Optional<ReminderEmployee> resultReminderEmployee = entityDao
                .findById(ReminderEmployee.class, idNumber);

        if (resultReminderEmployee.isPresent()) {
            System.out.println("Found remidner: " + resultReminderEmployee);
        } else
            System.out.println("Reminder not found");
    }

    private void addRemider() {
        EntityDao<ReminderEmployee> reminderEntityDao = new EntityDao<>();

        System.out.println("Choose type of reminder: \n" +
                "1- ubezpieczenie ZUS \n" +
                "2 - wypłata \n" +
                "3 - podatek od wynagrodzenia \n" +
                "4 - delegacja \n" +
                "5 - badania okresowe \n" +
                "6- lista obecności");

        int type = Integer.parseInt(scanner.nextLine());
        TypeOfReminder typeOfReminder;
        switch (type) {
            case 1:
                typeOfReminder = TypeOfReminder.UBEZPIECZENIE_ZUS;
                break;
            case 2:
                typeOfReminder = TypeOfReminder.WYPLATA;
                break;
            case 3:
                typeOfReminder = TypeOfReminder.PODATEK_OD_WYNAGRODZENIA;
                break;
            case 4:
                typeOfReminder = TypeOfReminder.DELEGACJA;
            case 5:
                typeOfReminder = TypeOfReminder.BADANIA_OKRESOWE;
                break;
            case 6:
                typeOfReminder = TypeOfReminder.LISTA_OBECNOSCI;
                break;
            default:
                throw new IllegalStateException("Unexpected value" + type);
        }

        System.out.println("Write amount");
        int timeofAmount = Integer.parseInt(scanner.nextLine());

        System.out.println("Write date of reminder");
        System.out.println("Write year");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Write month");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("Write day");
        int day = Integer.parseInt(scanner.nextLine());

        System.out.println("Choose period of reminder \n" +
                "1 - year \n" +
                "2 - month \n" +
                "3 - week \n" +
                "4 - day \n" +
                "5 - none");
        int period = Integer.parseInt(scanner.nextLine());
        PeriodOfReminder periodOfReminder;
        switch (period) {
            case 1:
                periodOfReminder = PeriodOfReminder.YEAR;
                break;
            case 2:
                periodOfReminder = PeriodOfReminder.MONTH;
                break;
            case 3:
                periodOfReminder = PeriodOfReminder.WEEK;
                break;
            case 4:
                periodOfReminder = PeriodOfReminder.DAY;
                break;
            case 5:
                periodOfReminder = PeriodOfReminder.NONE;
                break;
            default:
                throw new IllegalStateException("Unexepcted value: " + period);
        }
        ReminderEmployee reminderEmployee = new ReminderEmployee(typeOfReminder, timeofAmount, LocalDate.of(year, month, day), periodOfReminder);

//        Employee employee = askUserForEmployee();

        reminderEntityDao.saveOrUpdate(reminderEmployee);
    }

    private void handleAddReminderToEmployee() {
        Employee employee = null;
        ReminderEmployee reminderEmployee = null;
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
            reminderEmployeeEntityDao.findAll(ReminderEmployee.class)
                    .forEach(System.out::println);

            System.out.println("Podaj id:");
            Long id = Long.parseLong(scanner.next());

            Optional<ReminderEmployee> reminderEmployeeOptional = reminderEmployeeEntityDao.findById(ReminderEmployee.class, id);
            if (reminderEmployeeOptional.isPresent()) {
                reminderEmployee = reminderEmployeeOptional.get();
            }
        } while (reminderEmployee == null);
        System.out.println("Reminder wybrany.");

        reminderEmployee.setEmployee(employee);
        reminderEmployeeEntityDao.saveOrUpdate(reminderEmployee);
    }

    private void showRemider() {
        EntityDao<ReminderEmployee> entityDao = new EntityDao<>();

        entityDao
                .findAll(ReminderEmployee.class)
                .stream()
                .forEach(System.out::println);
    }
}
