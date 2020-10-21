package reminderProject.database;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import reminderProject.model.EmployeeReminder;
import reminderProject.model.EmployeeReminderType;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReminderDao {

    public List<EmployeeReminder> findByTypeOfReminder(EmployeeReminderType typeOfReminder) {

        List<EmployeeReminder> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EmployeeReminder> criteriaQuery = cb.createQuery(EmployeeReminder.class);
            Root<EmployeeReminder> reminderEmployeeRoot = criteriaQuery.from(EmployeeReminder.class);
            criteriaQuery.select(reminderEmployeeRoot)
                    .where(cb.equal(reminderEmployeeRoot.get("typeOfReminder"), typeOfReminder));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<EmployeeReminder> findBydateOdReminder (LocalDate date) {

        List<EmployeeReminder> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EmployeeReminder> criteriaQuery = cb.createQuery(EmployeeReminder.class);
            Root<EmployeeReminder> reminderEmployeeRoot = criteriaQuery.from(EmployeeReminder.class);
            criteriaQuery.select(reminderEmployeeRoot)
                    .where(cb.equal(reminderEmployeeRoot.get("dateOfRemider"), date));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
