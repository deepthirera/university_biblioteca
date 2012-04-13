package biblioteca;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Biblioteca {
    private User current_user = null;
    private Library library;
    private static Biblioteca biblioteca;
    private static IConsole console;
    private static BufferedReader br;


    public Biblioteca(IConsole console, BufferedReader br) throws IOException
    {
        this.br = br;
        this.console = console;
        library = new Library();
        initialiseLibrary();
    }                                                                

    public static void main(String[] args) throws IOException {
        console = new ConsoleImpl();
        biblioteca = new Biblioteca(console,new BufferedReader(new InputStreamReader(System.in)));
        biblioteca.printWelcomeScreen();
        biblioteca.selectMenuOptions();
    }


    void printWelcomeScreen() {
        console.writeLine("welcome");
    }

    void listMenuOptions() {
        console.writeLine("         Menu         ");
        console.writeLine("======================");
        console.writeLine("1. View All Books " +
                        "\n2. View All Movies" +
                            "\n3. Reserve a book" +
                            "\n4. Check library number" +
                            "\n5. Login User" +
                            "\n6. Log out" +
                            "\n7. Exit");
    }

     void selectMenuOptions() throws IOException {
        boolean status=true;
        while (status == true) {
            console.writeLine(" Please enter your choice ");
            int option = getUserInput();
            switch (option) {
                case 1:
                    console.writeLine("Books of the library");
                    viewAllBooks();
                    break;
                case 2:
                    console.writeLine("Movies of the library");
                    console.writeLine(" Movie  " + "  Director  " + "  Rating");
                    viewAllMovies();
                    break;
                case 3:
                    console.writeLine("Enter the ISBN of the book to reserve");
                    String book_title = br.readLine();
                    reserveBook(book_title);
                    break;
                case 4:
                    notifyCustomer();
                    break;
                case 5:
                    if(current_user != null)
                        console.writeLine("Already logged in");
                    else
                    {
                        console.writeLine("Enter proper username and password");
                        String userName = br.readLine();
                        String password = br.readLine();
                        loginNow(userName, password);
                    }

                    break;
                case 6: current_user = null;
                        console.writeLine("Logged out");
                    break;
                case 7:
                    status = false;
                    console.writeLine("Exiting................");
                    break;
                default:
                    console.writeLine("Invalid option");
            }
        }
    }


   private void viewAllBooks(){
        ArrayList<Book> books = library.getAllBooks();
        Iterator itr = books.iterator();
        while(itr.hasNext()) {
            Book book =(Book)itr.next();
            console.writeLine("Book isbn: " + book.getIsbn()  +"\n" +
                    "Title: " + book.getTitle() + "\n" +
                    "Author: " + book.getAuthor() + "\n" +
                    "Status: "+ book.getStatus() + "\n" );
        }
    }

    private void viewAllMovies(){
        ArrayList<Movie> movies = library.getAllMovies();
        Iterator itr = movies.iterator();
        while(itr.hasNext()){
            Movie movie = (Movie)itr.next();
            console.writeLine( "--------------------------------------------------" + "\n" +
                    "  " + movie.getName() + "  " + "  " + movie.getDirector() + "  " + "  " + movie.getRating()
                    );
        }
    }


   private void reserveBook(String isbn) throws IOException{
        BookReservationStatus bookStatus = library.setReserved(isbn);
        console.writeLine(bookStatus.getOutput());
        if(bookStatus.getOutput().equals("Enter a valid book name"))
            reserveBook(br.readLine());
    }

    private void notifyCustomer() throws IOException{
        try{
        console.writeLine(current_user.getUsername());
        }
        catch (Exception e)  {
        console.writeLine("Please talk to Librarian. Thank you.");
    }
    }

    void loginNow(String userName, String password) throws IOException{

         current_user = library.getValidUser(userName, password);
            if(current_user==null)
                console.writeLine("Login failure");
            else
                console.writeLine("Log in successful!");
    }

    private int getUserInput() throws IOException {
        listMenuOptions();
        console.writeLine(" Please enter your choice ");
        String input = br.readLine();
        try{
            return Integer.parseInt(input);
        }
        catch(Exception n)
        {
            console.writeLine("Should be an integet input");
            return getUserInput();
        }
    }
    private void initialiseLibrary(){
        library.addBook("a111", "C", "yashwanth");
        library.addBook("a112", "C++", "yashwanth");
        library.addBook("a113", "java", "kathe");
        library.addBook("a114", "ruby", "laura");
        library.addUser("111-1111", "aaaa");
        library.addUser("111-1112", "aaaa");
        library.addUser("111-1113", "aaaa");
        library.addUser("111-1114", "aaaa");
        library.addUser("111-1115", "aaaa");
        String[] name = {"MyLittleBride","naan mahan","pollathavan","pink panther","twilight","tangled","iceage","kungfupanda","aankaen","pokiri","innocentsteps","santhosh","ho my friend","indian","devar magan"};
        String[] director = {"Korean","1111","2222","3333","4444","5555","6666","7777","8888","9999","0000","zzzz","qwwr","asdf","zxcv"};
        String[] rating = {"1","10","N/A","N/A","1","4","6","10","9","2","3","N/A","2","2","5"};
        for(int i=0; i<name.length; i++){
            library.addMovie(name[i], director[i], rating[i]);
        }
    }
    enum BookReservationStatus{
        AVAILABLE("Available", "Thankyou! Enjoy the book!"),
        NOT_AVAILABLE("Not Available", "Sorry we don't have that book yet"),
        DOES_NOT_EXIST("Such a book does not exist", "Enter a valid book name");
        private String value;
        private String output;


        BookReservationStatus(String value, String output) {
            this.value = value;
            this.output = output;
        }

        public String getOutput() {
            return output;
        }
    }
}