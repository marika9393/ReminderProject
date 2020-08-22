package reminderProject.model;

import reminderProject.database.EntityDao;
import reminderProject.database.ReminderEmployeeDao;

import java.time.LocalDate;
import java.util.Scanner;

public class RemiderHandler {

    private Scanner scanner = new Scanner(System.in);
    private ReminderEmployeeDao reminderEmployeeDao = new ReminderEmployeeDao();

    public void handleRemidnder(String[] words) {

        System.out.println("Reminder List: ");
        System.out.println("Remider add: {type of remider} {amound} {date of remider} {period}");
        System.out.println("Reminder findBy {id} {type of remider} {date of remider}");
        System.out.println("Remider delete");

        if(words[1].equalsIgnoreCase("list")){
            showRemider();
        } else if (words[1].equalsIgnoreCase("add")) {
            addRemider();
        } else if (words[1].equalsIgnoreCase("findBy")) {
            System.out.println("id reminder \n" +
                    "type \n" +
                    "termination");
            String command = scanner.nextLine();

            if(command.equalsIgnoreCase("id")) {
                findByIdReminder();
            } else if(command.equalsIgnoreCase("type")) {
                findByTypeOfReminder();
            } else if (command.equalsIgnoreCase("termination")) {
                findByDateOfReminder();
            }
        } else if (words[1].equalsIgnoreCase("delete")){
            deleteReminder();
        }

    }

    private void deleteReminder() {
    }

    private void findByDateOfReminder() {
    }

    private void findByTypeOfReminder() {
    }

    private void findByIdReminder() {
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
                typeOfReminder = TypeOfReminder.lISTA_OBECNOSCI;
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
        ReminderEmployee reminderEmployee = new ReminderEmployee(typeOfReminder,timeofAmount, LocalDate.of(year,month,day), periodOfReminder);
        reminderEntityDao.saveOrUpdate(reminderEmployee);
    }

    private void showRemider() {
        EntityDao<ReminderEmployee> entityDao = new EntityDao<>();

        entityDao
                .findAll(ReminderEmployee.class)
                .stream()
                .forEach(System.out::println);
    }
}
