package Dao.DaoImplementations;

import Dao.Dao.*;
import Dao.Entities.*;
import Dao.Tools.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

public class PersonDaoImpl implements PersonDao {

    public Person findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Person.class, id);
    }

    public void save(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(person);
        tx1.commit();
        session.close();
    }

    public void update(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(person);
        tx1.commit();
        session.close();
    }

    public void delete(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(person);
        tx1.commit();
        session.close();
    }

    public List<Person> findAll() {
        List<Person> persons = (List<Person>) HibernateUtil.getSessionFactory().openSession().createQuery("From Person").list();
        return persons;
    }
}
