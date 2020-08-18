package ReminderProject;

import ReminderProject.database.EntityDao;
import ReminderProject.database.HibernateUtil;
import ReminderProject.model.Employee;
import ReminderProject.model.TypeOfContract;
import ReminderProject.model.TypeOfReminder;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("Initial version");
        HibernateUtil.getOurSessionFactory();
        System.out.println("Tested hibernate");

        Employee FirstEmployee = new Employee("Mariolka", "Blask");

        EntityDao<Employee> employeeEntityDao = new EntityDao<>();
        employeeEntityDao.saveOrUpdate(FirstEmployee);
    }
}