package biblioteca;

import java.util.ArrayList;
import java.util.Iterator;

import static biblioteca.Biblioteca.BookReservationStatus.AVAILABLE;
import static biblioteca.Biblioteca.BookReservationStatus.DOES_NOT_EXIST;
import static biblioteca.Biblioteca.BookReservationStatus.NOT_AVAILABLE;

public class Library {
    public ArrayList<Book> books = new ArrayList<Book>();
    public ArrayList<Movie> movies = new ArrayList<Movie>();
    public ArrayList<User> users = new ArrayList<User>();
    public Library(){
    }


     User getValidUser(String userName, String password) {
        if ((userName.equals("")) || (password.equals("")))
            return null;
        for (User user : users) {
            if(user.getUsername().equals(userName) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

     void addBook(String isbn, String title, String author){
        books.add(new Book(isbn, title, author));
    }

     void addUser(String userName, String password){
        users.add(new User(userName, password));
    }

     void addMovie(String name, String director, String rating){
        movies.add(new Movie(name, director, rating));
    }

     Biblioteca.BookReservationStatus setReserved(String isbn){
        for (Book book : books) {
            if(book.getIsbn().equals(isbn))
                if (book.isAvailable())
                {   book.setNotAvailable();
                    return AVAILABLE;
                }
                else
                     return NOT_AVAILABLE;
        }
        return DOES_NOT_EXIST;
    }

     ArrayList getAllBooks() {
        return books;
    }

     ArrayList getAllMovies(){
        return movies;
    }

     ArrayList getAllUsers(){
        return users;
    }

}
