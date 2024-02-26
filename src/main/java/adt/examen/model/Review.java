/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.model;

import jakarta.persistence.*;

/**
 *
 * @author mario
 */
@Entity
@Table(name="comments")
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="valoration", nullable = false)
    private float valoration;
    @Column(name="comment")
    private String comment;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Author author;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Book book;

    public Review() {
    }

    public Review(float valoration, String comment) {
        this.setValoration(valoration);
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getValoration() {
        return valoration;
    }

    public void setValoration(float valoration) {
        if (valoration > 5) {
            valoration = 5;
        }else if (valoration <0){
            valoration = 0;
        }
        this.valoration = valoration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return  valoration + " " + comment;
    }
    
    
    
    
    
    
    
    
    
}
