package local.classes;

import java.io.Serializable;

// Movie description class
public class MovieDesc implements Serializable {
    private String movieName;
    private String ISBN; // Unique identification number
    private String synopsis;

    public MovieDesc(String movieName, String ISBN, String synopsis) {
        this.movieName = movieName;
        this.ISBN = ISBN;
        this.synopsis = synopsis;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return
                "Movie Name='" + this.movieName + '\'' +
                ", ISBN='" + this.ISBN + '\'' +
                ", Synopsis='" + this.synopsis + '\'';

    }
}
