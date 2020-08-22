package reminderProject.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import reminderProject.model.Employee;
import reminderProject.model.TypeOfContract;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {



    public List<Employee> findByName(String name) {
        List<Employee> listByName = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            criteriaQuery.select(employeeRoot)
                    .where(cb.equal(employeeRoot.get("firstname"), name));
            listByName.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return listByName;
    }

    public List<Employee> findBySurname(String surname) {
        List<Employee> listBySurname = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            criteriaQuery.select(employeeRoot)
                    .where(cb.equal(employeeRoot.get("surname"), surname));
            listBySurname.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return listBySurname;
    }

    public List<Employee> findByTypeOfContract(TypeOfContract typeOfContract) {

        List<Employee> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            criteriaQuery.select(employeeRoot)
                    .where(cb.equal(employeeRoot.get("typeOfContract"), typeOfContract));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<Employee> findByFinishContract ( LocalDate data) {
        List<Employee> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            criteriaQuery.select(employeeRoot)
                    .where(cb.equal(employeeRoot.get("finishContract"), data ));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
