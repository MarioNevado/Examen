/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt.examen.dao;

import adt.examen.model.Book;

/**
 *
 * @author mario
 */
public interface BookDAO {
    public void createAuthor(Book author);
    public void updateAuthor(Book author);
    public void removeAuthor(Book author);
}
