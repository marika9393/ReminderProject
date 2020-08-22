package reminderProject.database;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import reminderProject.model.ReminderEmployee;
import reminderProject.model.TypeOfReminder;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReminderEmployeeDao {

    public List<ReminderEmployee> findByTypeOfReminder(TypeOfReminder typeOfReminder) {

        List<ReminderEmployee> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ReminderEmployee> criteriaQuery = cb.createQuery(ReminderEmployee.class);
            Root<ReminderEmployee> reminderEmployeeRoot = criteriaQuery.from(ReminderEmployee.class);
            criteriaQuery.select(reminderEmployeeRoot)
                    .where(cb.equal(reminderEmployeeRoot.get("typeOfReminder"), typeOfReminder));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<ReminderEmployee> findBydateOdReminder (LocalDate date) {

        List<ReminderEmployee> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ReminderEmployee> criteriaQuery = cb.createQuery(ReminderEmployee.class);
            Root<ReminderEmployee> reminderEmployeeRoot = criteriaQuery.from(ReminderEmployee.class);
            criteriaQuery.select(reminderEmployeeRoot)
                    .where(cb.equal(reminderEmployeeRoot.get("dateOfRemider"), date));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
