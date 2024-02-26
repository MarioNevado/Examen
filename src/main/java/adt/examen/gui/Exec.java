/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.gui;

import adt.examen.controller.AuthorService;
import adt.examen.controller.BookService;
import adt.examen.model.Author;
import adt.examen.model.Book;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author mario
 */
/*
Para la siguiente tarea tienes que diseñar un programa que haciendo uso de Java e Hibernate se cumplan los siguiente requisitos:
    Una persona puede escribir uno o varios libros y un libro puede ser escrito por una o varias personas.
    Una persona puede leer varios libros y un libro puede ser leído por varias personas.
    Una persona puede comentar un libro o varios y un libro puede ser comentado por varias personas.
    De una persona queremos saber su nombre, su nombre de usuario, su contraseña y su correo electrónico.
    De un libro queremos saber su titulo y numero de páginas.
    ?- Cuando una persona comenta un libro, le da una valoración entre 0 y 5 y le añade un comentario.
    Un usuario tiene que poder hacer login (username y contraseña correctas)
    ?- Un usuario tiene que poder marcar como leido un libro.
    ? Un usuario tiene que poder indicar que ha escrito un libro.
    - Un usuario tiene que poder comentar/valorar un libro.
    Cuando un usuario haga login, se tiene que actualizar la última vez que lo hizo.
    Un usuario puede ver todos los libros que ha leido.
    Un usuario puede ver todos los libros disponibles
    El main tiene que demostrar que cada una de estas partes son usables.
    Se debe de utilizar una arquitectura DAO.
    La entrega se realizará a través del aula virtual y github. 

Todos los puntos menos el último (que no puntúa) puntúan lo mismo, te recomiendo que dediques solo 3 horas a realizar esta tarea.
 */
public class Exec {

    static final Scanner sc = new Scanner(System.in);
    static final AuthorService authorController = new AuthorService();
    static final BookService bookController = new BookService();
    static Author user;

    public static void main(String[] args) {
        String option;
        //authorController.createAuthor(new Author("anonymous", "guest", "1234", null));
        do {
            option = menu();
            switch (option) {
                case "1":
                    logIn();
                    break;
                case "2":
                    readBook();
                    break;
                case "3":
                    writeBook();
                    break;
                case "4":
                    commentBook();
                    break;
                case "5":
                    getReadedBooks();
                    break;
                case "6":
                    getBooks();
                    break;
                case "7":
                    getLastLogIn();
                    break;
                default:
                    System.out.println("Adiós...");
                    break;
            }
        } while (!option.equals("*"));
    }

    public static void getLastLogIn() {
        try {
            System.out.println("Última vez: " + user.getFormattedLogIn());
        } catch (NullPointerException nullException) {
            System.err.println("No hay login que mostrar");
        }
    }

    public static void getBooks() {
        for (Book book : bookController.getBooks()) {
            System.out.println(book);
        }
    }

    public static void getReadedBooks() {
        for (Book readedBook : user.getReadedBooks()) {
            System.out.println(readedBook);
        }
    }

    public static void readBook() {
        String title;
        System.out.print("Introducir título: ");
        title = sc.nextLine();
        Book book = bookController.getBook(title);
        if (book == null) {
            System.err.println("No existen coincidencias");
        } else {
            user.getReadedBooks().add(book);
            book.getReadedAuthors().add(user);
            authorController.updateAuthor(user);
        }

    }

    public static void writeBook() {
        String title;
        long pages = 0;
        boolean flag = false;
        System.out.print("Introducir título: ");
        title = sc.nextLine();
        do {
            System.out.println("Introducir número de páginas: ");
            try{
                pages = Long.parseLong(sc.nextLine());
                flag = true;
            }catch(NumberFormatException nbe){
                System.err.println("Eso no es un número...");
            }
            
        } while (!flag);
        Book book = new Book(title, pages);
        bookController.createBook(book);
        user.getWrittenBooks().add(book);
        book.getWrittenAuthors().add(user);
        authorController.updateAuthor(user);

    }

    public static void commentBook() {
        String title;
        System.out.print("Introducir título: ");
        title = sc.nextLine();
        Book book = bookController.getBook(title);
        if (book == null) {
            System.err.println("No existen coincidencias");
        } else {
            user.getCommentedBooks().add(book);
            book.getCommentedAuthors().add(user);
            authorController.updateAuthor(user);
            //TODO tema de valoraciones de 0 a 5
        }

    }

    public static boolean logIn() {
        String username, password;
        Author author;
        System.out.print("Introducir nombre de usuario: ");
        username = sc.nextLine();
        System.out.print("Introducir contraseña: ");
        password = sc.nextLine();
        author = authorController.getAuthor(username, password);
        if (author == null) {
            System.err.println("Usuario o contraseña inválidos");
        } else {
            author.setLastLogIn(LocalDateTime.now());
            user = author;
            System.out.println("Sesión iniciada con éxito");
        }
        return false;
    }

    public static String menu() {

        String option;
        do {
            System.out.println("1.- Log In");
            System.out.println("2.- Marcar libro como leído");
            System.out.println("3.- Marcar libro como escrito");
            System.out.println("4.- Comentar libro");
            System.out.println("5.- Ver libros leídos");
            System.out.println("6.- Ver libros disponibles");
            System.out.println("7.- Ver último inicio de sesión");
            System.out.println("*.- Salir");
            System.out.print("Introducir opción: ");
            option = sc.nextLine();
            if (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5")
                    && !option.equals("6") && !option.equals("7") && !option.equals("*")) {
                System.err.println("Opción incorrecta");
            } else {
                return option;
            }
        } while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5")
                && !option.equals("6") && !option.equals("7") && !option.equals("*"));

        return null;
    }
}
