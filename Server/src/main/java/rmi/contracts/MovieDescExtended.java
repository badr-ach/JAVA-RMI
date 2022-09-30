package rmi.contracts;

public class MovieDescExtended extends MovieDesc{
    private byte[] teaser;

    public MovieDescExtended(String movieName, String ISBN, String synopsis, byte[] teaser) {
        super(movieName, ISBN, synopsis);
        this.teaser = teaser;
    }

    public byte[] getTeaser() {
        return teaser;
    }

    public void setTeaser(byte[] teaser) {
        this.teaser = teaser;
    }
}
