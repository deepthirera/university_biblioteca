package biblioteca;
class Book{
    private String title, author, isbn, status;


    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = "available";
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getStatus(){
        return this.status;
    }

    public boolean isAvailable(){
        if (this.status.equals("available"))
        return true;
        return false;
    }

    public void setNotAvailable(){
        setStatus("not_available");
    }
    
    public void setStatus(String value){
        this.status = value;
    }
}
