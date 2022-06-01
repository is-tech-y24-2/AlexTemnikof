package Dao.DaoImplementations;

import Dao.Dao.CatDao;
import Dao.Entities.*;
import org.hibernate.*;
import Dao.Tools.HibernateUtil;
import javax.persistence.Query;
import java.util.*;

public class CatDaoImpl implements CatDao {

    public Cat findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Cat.class, id);
    }

    public void save(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cat);
        tx1.commit();
        session.close();
    }

    public void update(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cat);
        tx1.commit();
        session.close();
    }

    public void delete(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cat);
        tx1.commit();
        session.close();
    }

    public List<Cat> findAll() {
        List<Cat> cats = (List<Cat>) HibernateUtil.getSessionFactory().openSession().createQuery("From Cat").list();
        return cats;
    }
}
