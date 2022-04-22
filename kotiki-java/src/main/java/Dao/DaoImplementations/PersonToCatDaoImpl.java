package Dao.DaoImplementations;

import Dao.Dao.PersonToCatDao;
import Dao.Entities.Cat;
import Dao.Entities.Person;
import Dao.RelationClasses.CatToCat;
import Dao.RelationClasses.PersonToCat;
import Dao.Tools.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class PersonToCatDaoImpl implements PersonToCatDao {

    public PersonToCat findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(PersonToCat.class, id);
    }

    public void save(PersonToCat personToCat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(personToCat);
        tx1.commit();
        session.close();
    }

    public void update(PersonToCat personToCat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(personToCat);
        tx1.commit();
        session.close();
    }

    public void delete(PersonToCat personToCat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(personToCat);
        tx1.commit();
        session.close();
    }

    public List<Cat> findCatsOfThePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from PersonToCat where personId = :paramName");
        query.setParameter("paramName", person.getPersonId());
        List list = query.getResultList();
        return list;
    }

    public List<PersonToCat> findAll() {
        List<PersonToCat> personToCats = (List<PersonToCat>) HibernateUtil.getSessionFactory().openSession().createQuery("From PersonToCat ").list();
        return personToCats;
    }
}
