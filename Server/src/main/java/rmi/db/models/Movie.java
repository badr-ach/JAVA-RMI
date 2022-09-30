package rmi.db.models;

public class Movie {
    private String movieName;
    private String isbn;
    private String synopsis;
    private String teaser;

    public Movie(String movieName, String isbn, String synopsis, String teaser) {
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.teaser = teaser;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }
}
