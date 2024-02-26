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
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name="username", unique = true)
    private String username;
    
    @Column(name="password", nullable = true)
    private String password;
    
    @Column(name="email")
    private String email;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "written_books", joinColumns = @JoinColumn(name="author_id"), inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> writtenBooks= new ArrayList<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "readed_books", joinColumns = @JoinColumn(name="author_id"), inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> readedBooks= new ArrayList<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "commented_books", joinColumns = @JoinColumn(name="author_id"), inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> commentedBooks= new ArrayList<>();

    public Author() {
    }

    public Author(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(List<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }

    public List<Book> getReadedBooks() {
        return readedBooks;
    }

    public void setReadedBooks(List<Book> readedBooks) {
        this.readedBooks = readedBooks;
    }

    public List<Book> getCommentedBooks() {
        return commentedBooks;
    }

    public void setCommentedBooks(List<Book> commentedBooks) {
        this.commentedBooks = commentedBooks;
    }
    
    
}
