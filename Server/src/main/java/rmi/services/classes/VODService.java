package rmi.services.classes;

import net.minidev.json.parser.ParseException;
import rmi.contracts.Bill;
import rmi.contracts.MovieDesc;
import rmi.contracts.MovieDescExtended;
import rmi.db.dao.Movies;
import rmi.db.models.Movie;
import rmi.services.interfaces.IClientBox;
import rmi.services.interfaces.IVODService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;

public class VODService extends UnicastRemoteObject implements IVODService {

    Movies moviesDAO;

    protected VODService() throws RemoteException {
        try {
            moviesDAO = new Movies();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<MovieDesc> viewCatalog() throws RemoteException {
        List<MovieDesc> catalog = new ArrayList<>();
        for(Movie m : moviesDAO.getCatalogue()){
            System.out.println("Looping through the catalog...");
            if(m.getTeaser().isEmpty()){
                catalog.add(new MovieDesc(m.getMovieName(),m.getIsbn(),m.getSynopsis()));
            }else{
                byte[] teaser = m.getTeaser().getBytes();
                catalog.add(new MovieDescExtended(m.getMovieName(),m.getIsbn(),m.getSynopsis(),teaser));
            }
        }
        return catalog;
    }

    @Override
    public synchronized Bill PlayMovie(String isbn, IClientBox box) throws RemoteException {
        box.stream(("\nNow streaming : " + isbn + "\n").getBytes());
        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteArrayInputStream in = moviesDAO.getMovieBytes(isbn);
                byte[] buffer = new byte[1024];
                int len;
                while (true) {
                    try {
                        if (!((len = in.read(buffer)) > 0)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        box.stream(buffer);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        return new Bill(moviesDAO.getMovieName(isbn),new BigInteger(100,new Random()));
    }
}
