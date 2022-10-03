package rmi.contracts;

import java.io.Serializable;

public class MovieDesc implements Serializable {
    private String movieName;
    private String ISBN;
    private String Synopsis;

    public MovieDesc(String movieName, String ISBN, String synopsis) {
        this.movieName = movieName;
        this.ISBN = ISBN;
        Synopsis = synopsis;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getSynopsis() {
        return Synopsis;
    }

    public void setSynopsis(String synopsis) {
        Synopsis = synopsis;
    }

    @Override
    public String toString() {
        return
                "Movie Name='" + movieName + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", Synopsis='" + Synopsis + '\'';

    }
}
