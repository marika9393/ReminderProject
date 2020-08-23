package reminderProject.handlers;

import reminderProject.database.CarDao;
import reminderProject.database.EntityDao;
import reminderProject.model.Car;

import java.util.Optional;
import java.util.Scanner;

public class CarHandler {
    private Scanner scanner = new Scanner(System.in);
    private EntityDao<Car> carEntityDao = new EntityDao<>();

    public void handle() {
        System.out.println("Write command");
        printCarCommand();
        String command = scanner.nextLine();

        CarDao carDao = new CarDao();
        if (command.equalsIgnoreCase("add")){
            addCar();
        } else if (command.equalsIgnoreCase("show")) {
            showCars();
        } else if (command.equalsIgnoreCase("findby")) {
            findBy(carDao);
        } else if (command.equalsIgnoreCase("delete")) {
            deleteCar();
        }
    }

    private void printCarCommand() {
        System.out.println(" - [show]");
        System.out.println(" - [add]");
        System.out.println(" - [findby]");
        System.out.println(" - [delete]");
    }

    private void findBy(CarDao carDao) {
        System.out.println("Enter the phrase which you want to find the car:");
        String phrase = scanner.nextLine();
        carDao.findByAny(phrase)
                .forEach(System.out::println);
    }

//    private void findBy(String[] words) {
//        Scanner scanner = new Scanner(System.in);
//        CarDao carDao = new CarDao();
//        System.out.println("id car \n" +
//                "mark \n" +
//                "model \n" +
//                "registration number \n");
//        String commandCar = scanner.nextLine();
//        if (commandCar.equalsIgnoreCase("id")) {
//            carDao.findByAny(commandCar);
//        } else if (commandCar.equalsIgnoreCase("mark")) {
//            carDao.findByAny(commandCar);
//        } else if (commandCar.equalsIgnoreCase("model")) {
//            carDao.findByAny(commandCar);
//        } else if (commandCar.equalsIgnoreCase("registration")) {
//            carDao.findByAny(commandCar);
//        }
//    }


    private void deleteCar() {
        System.out.println("Which car to delete - enter the car id:");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carDelete = carEntityDao
                .findById(Car.class, id);

        if (carDelete.isPresent()) {
            Car car = carDelete.get();
            carEntityDao.delete(car);
            System.out.println("Car removed. ");
        } else {
            System.out.println("Not found car");
        }
    }

    public void showCars() {
        carEntityDao
                .findAll(Car.class)
                .forEach(System.out::println);
    }

    private  void addCar() {
        System.out.println("Enter car mark");
        String mark = scanner.nextLine();

        System.out.println("Enter car model");
        String model = scanner.nextLine();

        System.out.println("Provide the car registration number");
        String regNum = scanner.nextLine();

        Car car = new Car(mark, model, regNum);
        carEntityDao.saveOrUpdate(car);

        System.out.println("Car added");
    }
}
