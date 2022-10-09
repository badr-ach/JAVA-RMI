package local.classes;

import java.io.Serializable;
import java.math.BigInteger;

// Bill class representing a bill given after buying a movie
public class Bill implements Serializable {
    private String movieName; // movie name associated to the bill
    private BigInteger outrageousPrice; // bill price

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
        return
                movieName + '\'' +
                ", for $" + outrageousPrice ;
    }
}
