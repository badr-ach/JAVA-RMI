package local.classes;

import java.io.Serializable;

/**
 * Movie description class
 */
public class MovieDesc implements Serializable {
    /**
     * Movie name
     */
    private String movieName;
    /**
     * Unique movie identifier : ISBN
     */
    private String ISBN;
    /**
     * Synopsis of the movie
     */
    private String synopsis;

    public MovieDesc(String movieName, String ISBN, String synopsis) {
        this.movieName = movieName;
        this.ISBN = ISBN;
        this.synopsis = synopsis;
    }

    public String getMovieName() {
        return movieName;
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
                "Movie Name : '" + this.movieName + '\'' + '\n' +
                "   ISBN : '" + this.ISBN + '\'' +'\n' +
                "   Synopsis : '" + this.synopsis + '\'';

    }
}
