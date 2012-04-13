package biblioteca;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    Library library;
    @Before
    public void initialize(){
        library = new Library();
    }
    @Test
    public void testAddBooksToLibrary(){
        library.addBook("a111", "C", "yashwanth");
        library.addBook("a112", "C++", "yashwanth");
        assertEquals(2, library.books.size());
    }

    @Test
    public void testAddUsersToLibrary(){
        library.addUser("111-1111", "aaaa");
        assertEquals(1, library.users.size());
    }

    @Test
    public void testAddMoviesToLibrary(){
        library.addMovie("twilight", "Catherine Hardwicke", "10");
        assertEquals(1, library.movies.size());
    }

    @Test
    public void testBlankUser() throws IOException {
        assertEquals(null,library.getValidUser("", "aaaa"));
    }

    @Test
    public void testNotBlankUser() throws IOException {
        library.addUser("111-1111", "aaaa");
        assertNotNull(library.getValidUser("111-1111", "aaaa"));
    } 

    @Test
    public void testReturnAllItems() {
        library.addBook("a111", "C", "yashwanth");
        library.addUser("111-1111", "aaaa");
        library.addMovie("twilight", "Catherine Hardwicke", "10");
        ArrayList<Book> allBooks;
        allBooks = library.getAllBooks();
        assertEquals(1, allBooks.size());
        ArrayList<Movie> allMovies;
        allMovies = library.getAllMovies();
        assertEquals(1, allMovies.size());
        ArrayList<User> allUsers = null;
        allUsers = library.getAllUsers();
        assertEquals(1, allUsers.size());
    }
    
    @Test
    public void testAvailableBookStatus(){
        library.addBook("a111", "C", "yashwanth");
        assertEquals(Biblioteca.BookReservationStatus.AVAILABLE, library.setReserved("a111"));
    }

    @Test
    public void testGetAvailableBookStatus(){
        library.addBook("a111", "C", "yashwanth");
        Book book = (Book) library.books.get(0);
        assertEquals(Biblioteca.BookReservationStatus.AVAILABLE, library.setReserved(book.getIsbn()));
    }

    @Test
    public void testGetBookNotAvailableStatus(){
        library.addBook("a111", "C", "yashwanth");
        Book book = (Book) library.books.get(0);
        book.setNotAvailable();
        library.books.set(0, book);
        assertEquals(Biblioteca.BookReservationStatus.NOT_AVAILABLE, library.setReserved(book.getIsbn()));
    }

    @Test
    public void testCheckBookExists(){
        library.addBook("a111", "C", "yashwanth");
        assertEquals(Biblioteca.BookReservationStatus.DOES_NOT_EXIST, library.setReserved("11"));
    }
}
    

