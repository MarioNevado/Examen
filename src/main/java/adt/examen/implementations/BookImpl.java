/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.implementations;

import adt.examen.dao.BookDAO;
import adt.examen.model.Book;
import adt.examen.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mario
 */
public class BookImpl implements BookDAO {

    @Override
    public void createAuthor(Book book) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (book != null) {
                tx = session.beginTransaction();
                session.persist(book);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateAuthor(Book book) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            if (book != null) {
                tx = session.beginTransaction();
                session.merge(book);
                tx.commit();
            }
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void removeAuthor(Book book) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            if (book != null) {
                tx = session.beginTransaction();
                session.remove(book);
                tx.commit();
            }
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

}
