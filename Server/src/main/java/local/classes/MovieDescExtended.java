package local.classes;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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

    @Override
    public String toString() {
        System.out.println(super.toString());
        System.out.println("   Trailer is playing : ");
        ByteArrayInputStream in = new ByteArrayInputStream(teaser);
        byte[] buffer = new byte[516];
        int len;
        while (true) {
            try {
                if (!((len = in.read(buffer)) > 0)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
                System.out.print("   "+new String(buffer).trim());
        }
        return "";
    }
}
