package biblioteca;
public class Movie {
    private String name, director, rating;

    public Movie(String name, String director, String rating){
        this.name = name;
        this.director = director;
        this.rating = rating;
    }

    public String getName() {
        return this.name;
    }

    public String getDirector() {
        return this.director;
    }

    public String getRating() {
        return this.rating;
    }
}

