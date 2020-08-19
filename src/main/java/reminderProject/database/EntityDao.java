package reminderProject.database;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import reminderProject.model.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityDao<T> {


    public void saveOrUpdate(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Optional<T> findById(Class<T> classType, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // istnieje prawdopodobieństwo, że rekord nie zostanie odnaleziony
            return Optional.ofNullable(session.get(classType, id));
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // instrukcja która służy do usuwania z bazy danych
            session.delete(entity);

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<T> findAll(Class<T> classType) {
        List<T> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // narzędzie do tworzenia zapytań i kreowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // obiekt reprezentujący zapytanie
            CriteriaQuery<T> criteriaQuery = cb.createQuery(classType);

            // obiekt reprezentujący tabelę bazodanową.
            // do jakiej tabeli kierujemy nasze zapytanie?
            Root<T> rootTable = criteriaQuery.from(classType);

            // wykonanie zapytania
            criteriaQuery.select(rootTable);

            // specification
            list.addAll(session.createQuery(criteriaQuery).list());

            // poznanie uniwersalnego rozwiązania które działa z każdą bazą danych
            // używanie klas których będziecie używać na JPA (Spring)

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<Employee> findByName(Class<Employee> classType, String name) {
        List<Employee> listByName = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = cb.createQuery(classType);
            Root<Employee> employeeRoot = criteriaQuery.from(classType);
            criteriaQuery.select(employeeRoot)
                    .where(cb.equal(employeeRoot.get("name"), name));
            listByName.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException he) {
            he.printStackTrace();
        }
        return listByName;
    }

    public List<Employee> findBySurname(Class<Employee> classType, String surname) {
        List<Employee> listBySurname = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = cb.createQuery(classType);
            Root<Employee> employeeRoot = criteriaQuery.from(classType);
            criteriaQuery.select(employeeRoot)
                    .where(cb.equal(employeeRoot.get("surname"), surname));
            listBySurname.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException he) {
            he.printStackTrace();
        }
        return listBySurname;
    }
}
