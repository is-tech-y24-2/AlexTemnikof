package Dao.DaoImplementations;

import Dao.Dao.CatToCatDao;
import Dao.Entities.Cat;
import Dao.RelationClasses.CatToCat;
import Dao.Tools.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class CatToCatDaoImpl implements CatToCatDao {
    public CatToCat findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(CatToCat.class, id);
    }

    public void save(CatToCat catToCat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(catToCat);
        tx1.commit();
        session.close();
    }

    public void update(CatToCat catToCat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(catToCat);
        tx1.commit();
        session.close();
    }

    public void delete(CatToCat catToCat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(catToCat);
        tx1.commit();
        session.close();
    }

    public List<Cat> findFriendsOfTheCat(Cat cat){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from CatToCat where firstId = :paramName");
        query.setParameter("paramName", cat.getCatId());
        List list = query.getResultList();
        return list;
    }

    public List<CatToCat> findAll() {
        List<CatToCat> catToCats = (List<CatToCat>) HibernateUtil.getSessionFactory().openSession().createQuery("From CatToCat ").list();
        return catToCats;
    }
}
