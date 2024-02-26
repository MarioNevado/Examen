/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.examen.gui;

import adt.examen.controller.AuthorService;
import adt.examen.controller.BookService;
import adt.examen.model.Author;
import adt.examen.model.Book;
import adt.examen.model.Review;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author mario
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
                case "8":
                    devActions(); //dev
                    break;
                default:
                    System.out.println("Adiós...");
                    break;
            }
        } while (!option.equals("*"));
    }

    public static void devActions() {
        String answer;
        System.out.print("Introducir contraseña: ");
        answer = sc.nextLine();
        if (answer.equals("dev")) {
            System.out.print("Añadir usuario?(y/n): ");
            answer = sc.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                createAuthor();
            }
            System.out.print("Añadir libro?(y/n): ");
            answer = sc.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                createBook();
            }
        }
    }

    private static void createBook() {
        String title;
        long pages;
        System.out.println("Título: ");
        title = sc.nextLine();
        System.out.println("Páginas: ");
        pages = Long.parseLong(sc.nextLine());
        bookController.createBook(new Book(title, pages));
    }

    private static void createAuthor() {
        String name, username, password, email;
        System.out.println("Nombre: ");
        name = sc.nextLine();
        System.out.println("Nombre de usuario: ");
        username = sc.nextLine();
        System.out.println("Contraseña: ");
        password = sc.nextLine();
        System.out.println("Email: ");
        email = sc.nextLine();
        authorController.createAuthor(new Author(name, username, password, email));
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
            System.out.print("Introducir número de páginas: ");
            try {
                pages = Long.parseLong(sc.nextLine());
                flag = true;
            } catch (NumberFormatException nbe) {
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
        String title, comment;
        float valoration;
        Review review;
        System.out.print("Introducir título: ");
        title = sc.nextLine();
        Book book = bookController.getBook(title);
        if (book == null) {
            System.err.println("No existen coincidencias");
        } else {
            System.out.print("Introducir valoración: ");
            valoration = Float.parseFloat(sc.nextLine());
            System.out.println("Introducir commentario: ");
            comment = sc.nextLine();
            review = new Review(valoration, comment);
            review.setAuthor(user);
            review.setBook(book);
            user.getCommentedBooks().add(review);
            book.getCommentedAuthors().add(review);
            authorController.updateAuthor(user);
            bookController.updateBook(book);
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
            System.out.println("8.- Opciones de administrador");
            System.out.println("*.- Salir");
            System.out.print("Introducir opción: ");
            option = sc.nextLine();
            if (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5")
                    && !option.equals("6") && !option.equals("7") && !option.equals("8") && !option.equals("*")) {
                System.err.println("Opción incorrecta");
            } else {
                return option;
            }
        } while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5")
                && !option.equals("6") && !option.equals("7") && !option.equals("8") && !option.equals("*"));

        return null;
    }
}
