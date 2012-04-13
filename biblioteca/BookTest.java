package biblioteca;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BookTest {
    Library library = new Library();
    @Test
    public void testisAvailable(){
        Book book = new Book("a111", "C", "yashwanth");
        assertEquals(true, book.isAvailable());
    }

    @Test
    public void testSetNotAvailable(){
        Book book = new Book("a111", "C", "yashwanth");
        book.setNotAvailable();
        assertEquals(false, book.isAvailable());
    }
    
}
