/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.implementations;

import adt.examen.dao.AuthorDAO;
import adt.examen.model.Author;
import adt.examen.utils.HibernateUtil;
import org.hibernate.*;

/**
 *
 * @author mario
 */
public class AuthorImpl implements AuthorDAO{

    @Override
    public void createAuthor(Author author) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            if (author != null) {
                tx = session.beginTransaction();
                session.persist(author);
                tx.commit();
            }
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateAuthor(Author author) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            if (author != null) {
                tx = session.beginTransaction();
                session.merge(author);
                tx.commit();
            }
        }catch(Exception e){
            if(tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeAuthor(Author author) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            if (author != null) {
                tx = session.beginTransaction();
                session.remove(author);
                tx.commit();
            }
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    
}
