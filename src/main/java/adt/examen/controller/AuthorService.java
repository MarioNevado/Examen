/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.controller;

import adt.examen.implementations.AuthorImpl;
import adt.examen.model.Author;
import adt.examen.utils.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author mario
 */
public class AuthorService {
    
    private final AuthorImpl implementation = new AuthorImpl();
    
    public Author getAuthor(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Author> cQuery = cb.createQuery(Author.class);
            Root<Author> root = cQuery.from(Author.class);
            cQuery.where(cb.equal(root, id));
            Query<Author> query = session.createQuery(cQuery);
            return query.getSingleResult();
        }catch(NoResultException nre){
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Author getAuthor(String username, String password){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Author> cQuery = cb.createQuery(Author.class);
            Root<Author> root = cQuery.from(Author.class);
            cQuery.where(cb.and(cb.equal(root.get("username"), username), cb.equal(root.get("password"), password)));
            Query<Author> query = session.createQuery(cQuery);
            return query.getSingleResult();
        }catch(NoResultException nre){
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void createAuthor(Author author){
        implementation.createAuthor(author);
    }
    
    public void updateAuthor(Author author){
        implementation.updateAuthor(author);
    }
    
    public void removeAuthor(Author author){
        implementation.removeAuthor(author);
    }
}
