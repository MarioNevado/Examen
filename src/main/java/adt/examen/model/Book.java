/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mario
 */
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="pages", nullable = false)
    private long pages;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "written_books", joinColumns = @JoinColumn(name="book_id"), inverseJoinColumns = @JoinColumn(name="author_id"))
    private List<Author> writtenAuthors = new ArrayList<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "readed_books", joinColumns = @JoinColumn(name="book_id"), inverseJoinColumns = @JoinColumn(name="author_id"))
    private List<Author> readedAuthors= new ArrayList<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "commented_books", joinColumns = @JoinColumn(name="book_id"), inverseJoinColumns = @JoinColumn(name="author_id"))
    private List<Author> commentedAuthors= new ArrayList<>();

    public Book() {
    }

    public Book(String title, long pages) {
        this.title = title;
        this.pages = pages;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getWrittenAuthors() {
        return writtenAuthors;
    }

    public void setWrittenAuthors(List<Author> writtenAuthors) {
        this.writtenAuthors = writtenAuthors;
    }

    public List<Author> getReadedAuthors() {
        return readedAuthors;
    }

    public void setReadedAuthors(List<Author> readedAuthors) {
        this.readedAuthors = readedAuthors;
    }

    public List<Author> getCommentedAuthors() {
        return commentedAuthors;
    }

    public void setCommentedAuthors(List<Author> commentedAuthors) {
        this.commentedAuthors = commentedAuthors;
    }

    @Override
    public String toString() {
        return  title + ", " + pages + " páginas";
    }
    
    
    
}
