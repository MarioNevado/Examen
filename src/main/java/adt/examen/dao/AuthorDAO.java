/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt.examen.dao;

import adt.examen.model.Author;

/**
 *
 * @author mario
 */
public interface AuthorDAO {
    public void createAuthor(Author author);
    public void updateAuthor(Author author);
    public void removeAuthor(Author author);
    
}
