/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.controller;

import adt.examen.implementations.BookImpl;
import adt.examen.model.Author;
import adt.examen.model.Book;
import adt.examen.utils.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author mario
 */
public class BookService {
    private final BookImpl implementation = new BookImpl();
    
    public List<Book> getBooks(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cQuery = cb.createQuery(Book.class);
            Root<Book> root = cQuery.from(Book.class);
            Query<Book> query = session.createQuery(cQuery);
            return query.list();
        }catch(NoResultException nre){
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Book getBook(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cQuery = cb.createQuery(Book.class);
            Root<Book> root = cQuery.from(Book.class);
            cQuery.where(cb.equal(root, id));
            Query<Book> query = session.createQuery(cQuery);
            return query.getSingleResult();
        }catch(NoResultException nre){
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Book getBook(String title){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cQuery = cb.createQuery(Book.class);
            Root<Book> root = cQuery.from(Book.class);
            cQuery.where(cb.equal(root.get("title"), title));
            Query<Book> query = session.createQuery(cQuery);
            return query.getSingleResult();
        }catch(NoResultException nre){
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void createBook(Book book){
        implementation.createAuthor(book);
    }
    
    public void updateBook(Book book){
        implementation.updateAuthor(book);
    }
    
    public void removeBook(Book book){
        implementation.removeAuthor(book);
    }
}
