package reminderProject.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import reminderProject.model.CarReminder;
import reminderProject.model.CarReminderType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarReminderDao {

    public List<CarReminder> findByReminderType(CarReminderType reminderPhrase) {

        List<CarReminder> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CarReminder> criteriaQuery = cb.createQuery(CarReminder.class);
            Root<CarReminder> rootTable = criteriaQuery.from(CarReminder.class);
            criteriaQuery.select(rootTable)
                    .where(cb.equal(rootTable.get("type"),reminderPhrase));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<CarReminder> findByDateOfReminder(LocalDate date) {

        List<CarReminder> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CarReminder> criteriaQuery = cb.createQuery(CarReminder.class);
            Root<CarReminder> reminderRoot = criteriaQuery.from(CarReminder.class);
            criteriaQuery.select(reminderRoot)
                    .where(cb.equal(reminderRoot.get("date"), date));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
