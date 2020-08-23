package reminderProject.handlers;


import reminderProject.database.CarReminderDao;
import reminderProject.database.EntityDao;
import reminderProject.model.Car;
import reminderProject.model.CarReminder;
import reminderProject.model.ReminderPeriod;
import reminderProject.model.ReminderType;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CarReminderHandler {
    private Scanner scanner = new Scanner(System.in);
    private EntityDao<Car> carEntityDao = new EntityDao<>();
    private CarReminderDao carReminderDao = new CarReminderDao();

    public void handlerReminder() {
        System.out.println("Write command: ");
        printReminderCommend();
        String command = scanner.nextLine();

        CarReminderDao carReminderDao = new CarReminderDao();
        if (command.equalsIgnoreCase("add")) {
            addCarReminder();
        } else if (command.equalsIgnoreCase("show")) {
            showCarReminder();
        } else if (command.equalsIgnoreCase("findby")) {
            findByCarReminder(carReminderDao);
        } else if (command.equalsIgnoreCase("delete")) {
            deleteCarReminder();
        }
    }

    private void printReminderCommend() {
        System.out.println(" - [show]");
        System.out.println(" - [add]");
        System.out.println(" - [findby]");
        System.out.println(" - [delete]");
    }

    private void deleteCarReminder() {
        EntityDao<CarReminder> entityDao = new EntityDao<>();
        System.out.println("Which car reminder to delete - enter the car reminder id:");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<CarReminder> carReminderDelete = entityDao
                .findById(CarReminder.class, id);

        if (carReminderDelete.isPresent()) {
            CarReminder carReminder = carReminderDelete.get();
            entityDao.delete(carReminder);
            System.out.println("Car reminder removed. ");
        } else {
            System.out.println("Not found car reminder");
        }

    }

    private void findByCarReminder(CarReminderDao carReminderDao) {
        System.out.println("Enter the phrase which you want to find the car reminder: \n " +
                "leasing \n " +
                "insurance \n " +
                "review \n " +
                "oil \n " +
                "fire \n " +
                "tacho \n " +
                "wash \n " +
                "calibration");

        boolean error = false;

        do {
            try {
                ReminderType reminderType = ReminderType.valueOfShortReminder(scanner.nextLine().toLowerCase());

                List<CarReminder> resultReminderList = carReminderDao.findByReminder(reminderType);
                error = false;

                if (resultReminderList.size() > 0) {
                    System.out.println("Car reminder found.");
                    resultReminderList.forEach(System.out::println);
                } else
                    System.out.println("Reminder not found");
            } catch (InputMismatchException e) {
                System.out.println("Write the correct type ");
                scanner.nextLine();
            }
        } while (error);
    }


    private void showCarReminder() {
        EntityDao<CarReminder> reminderEntityDao = new EntityDao<>();
        reminderEntityDao
                .findAll(CarReminder.class)
                .forEach(System.out::println);

    }

    private void addCarReminder() {

        EntityDao<CarReminder> carReminderEntityDao = new EntityDao<>();

        System.out.println("Select the type of reminder: \n  +" +
                "1 -  LEASING,\n" +
                "2 -  INSURANCE,\n" +
                "3 -  REVIEW,\n" +
                "4 -  OIL_CHANGE,\n" +
                "5 -  FIRE_EXTINGUISHER_VALIDITY,\n" +
                "6 -  TACHO_LEGALIZATION,\n" +
                "7 -  CAR_WASH,\n" +
                "8 -  THERMOMETER_CALIBRATION");

        int reminder = Integer.parseInt(scanner.nextLine());
        ReminderType reminderType;
        switch (reminder) {
            case 1:
                reminderType = ReminderType.LEASING;
                break;
            case 2:
                reminderType = ReminderType.INSURANCE;
                break;
            case 3:
                reminderType = ReminderType.REVIEW;
                break;
            case 4:
                reminderType = ReminderType.OIL_CHANGE;
                break;
            case 5:
                reminderType = ReminderType.FIRE_EXTINGUISHER_VALIDITY;
                break;
            case 6:
                reminderType = ReminderType.TACHO_LEGALIZATION;
                break;
            case 7:
                reminderType = ReminderType.CAR_WASH;
                break;
            case 8:
                reminderType = ReminderType.THERMOMETER_CALIBRATION;
                break;
            default:
                throw new IllegalStateException("Unexpected value" + reminder);

        }

        System.out.println("Write amount reminder:");
        int amount = Integer.parseInt(scanner.nextLine());

        System.out.println("Write date:");
        System.out.println("YEAR");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("MONTH");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("DAY");
        int day = Integer.parseInt(scanner.nextLine());

        System.out.println("Select the period of reminder: \n +" +
                " 1-- YEAR,\n" +
                " 2 - MOUNTH,\n" +
                " 3 - WEEK,\n" +
                " 4 - DAY,\n" +
                " 5 - NONE;");

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
                throw new IllegalStateException("Unexpected value" + period);
        }

        Car car = askUserForCar();

        CarReminder carReminder = new CarReminder(reminderType, amount, LocalDate.of(year, month, day), reminderPeriod);
        carReminder.setCar(car);
        carReminderEntityDao.saveOrUpdate(carReminder);

        System.out.println("Reminder added");
    }

    private Car askUserForCar() {
        Car car = null;
        do{
            System.out.println("This is a list of cars:");
            carEntityDao
                    .findAll(Car.class)
                    .forEach(System.out::println);
            System.out.println();
            System.out.println("Provide car id:");

            Long carId = Long.parseLong(scanner.nextLine());
            Optional<Car> optionalCar = carEntityDao.findById(Car.class, carId);
            if(optionalCar.isPresent()){
                car = optionalCar.get();
            }
        }while (car == null);
        return car;
    }


}
