package biblioteca;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BibliotecaTest {
    Biblioteca bibli;
    FakeConsole _console;
    FakeReader fr;
    @Before
    public void initialise() throws IOException {
        _console = new FakeConsole();
        fr = new FakeReader(new InputStreamReader(System.in), 1000);
        bibli = new Biblioteca(_console, fr);
    }

   @Test
   public void testWelcomeScreen(){
       bibli.printWelcomeScreen();
       assertThat(_console.lastLine(), containsString("welcome"));
   }

    @Test
    public void testListMenuOptions() throws IOException {
        bibli.listMenuOptions();
        assertThat(_console.writeda(1), containsString("1. View All Books \n" +
                "2. View All Movies\n" +
                "3. Reserve a book\n" +
                "4. Check library number\n" +
                "5. Login User\n" +
                "6. Log out\n" +
                "7. Exit"));
    }

    @Test
    public void testViewAllBooks() throws IOException {
        fr.setUserInput("7");
        fr.setUserInput("1");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("Status: available"));
    }

    @Test
    public void testViewAllMovies() throws IOException{
        fr.setUserInput("7");
        fr.setUserInput("2");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("devar magan    zxcv    5"));
    }

    @Test
    public void testGetAvailableBook() throws IOException {
        fr.setUserInput("7");
        fr.setUserInput("a111");
        fr.setUserInput("3");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("Thankyou! Enjoy the book!"));
    }

    @Test
    public void testNotavailableBook() throws IOException {
        fr.setUserInput("7");
        fr.setUserInput("a111");
        fr.setUserInput("3");
        fr.setUserInput("a111");
        fr.setUserInput("3");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("Sorry we don't have that book yet"));
    }

    @Test
    public void testBooknotexist() throws IOException {
        fr.setUserInput("7");
        fr.setUserInput("a111");
        fr.setUserInput("a");
        fr.setUserInput("3");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(8), containsString("Enter a valid book name"));
    }

    @Test
    public void testNotifyBeforeLogin() throws IOException {
        fr.setUserInput("7");
        fr.setUserInput("4");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("Please talk to Librarian. Thank you."));
    }

    @Test
    public void testNotifyCustomerAfterLogin() throws IOException {
        bibli.loginNow("111-1111", "aaaa");
        fr.setUserInput("7");
        fr.setUserInput("4");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("111-1111"));
    }

    @Test
    public void testloggingIn() throws IOException{
        fr.setUserInput("7");
        fr.setUserInput("aaaa");
        fr.setUserInput("111-1111");
        fr.setUserInput("5");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("Log in successful"));
    }

    @Test
    public void testlogInFailureStatus() throws IOException {
        fr.setUserInput("7");
        fr.setUserInput("aa#r");
        fr.setUserInput("111-1");
        fr.setUserInput("5");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("Login failure"));
    }

    @Test
    public void testloginFailure() throws IOException{
        fr.setUserInput("7");
        fr.setUserInput("");
        fr.setUserInput("");
        fr.setUserInput("5");
        bibli.selectMenuOptions();
        assertThat(_console.writeda(7), containsString("Login failure"));
    }

    @Test
    public void testCaseExit() throws IOException{
        fr.setUserInput("7");
        fr.setUserInput("1");
        bibli.selectMenuOptions();
        assertThat(_console.lastLine(), containsString("Exiting................"));
    }
}

