package local.classes;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Bill class representing a bill given after streaming a movie
 */
public class Bill implements Serializable {
    /**
     * Name of the movie that was streamed
     */
    private String movieName;
    /**
     * Outrageoues price of the bill
     */
    private BigInteger outrageousPrice;

    public Bill(String movieName, BigInteger outrageousPrice) {
        this.movieName = movieName;
        this.outrageousPrice = outrageousPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public BigInteger getOutrageousPrice() {
        return outrageousPrice;
    }

    public void setOutrageousPrice(BigInteger outrageousPrice) {
        this.outrageousPrice = outrageousPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "movieName='" + movieName + '\'' +
                ", outrageousPrice=" + outrageousPrice +
                '}';
    }
}
