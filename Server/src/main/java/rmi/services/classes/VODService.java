package rmi.services.classes;

import rmi.contracts.Bill;
import rmi.contracts.MovieDesc;
import rmi.contracts.MovieDescExtended;
import rmi.db.dao.Movies;
import rmi.db.models.Movie;
import rmi.services.interfaces.IClientBox;
import rmi.services.interfaces.IVODService;

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
    }

    @Override
    public List<MovieDesc> viewCatalog() {
        List<MovieDesc> catalog = new ArrayList<>();
        for(Movie m : moviesDAO.getCatalogue()){
            if(!m.getTeaser().isEmpty()){
                catalog.add(new MovieDesc(m.getMovieName(),m.getIsbn(),m.getSynopsis()));
            }else{
                byte[] teaser = m.getTeaser().getBytes();
                catalog.add(new MovieDescExtended(m.getMovieName(),m.getIsbn(),m.getSynopsis(),teaser));
            }
        }
        return catalog;
    }

    @Override
    public Bill PlayMovie(String isbn, IClientBox box) throws RemoteException {
        box.stream(("Now streaming : " + isbn).getBytes());
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = moviesDAO.getMovieBytes(isbn).read(buffer)) > 0) {
                    box.stream(moviesDAO.getMovieBytes(isbn).getBytes());
                }
            }
        }).start();
        return new Bill(moviesDAO.getMovieName(isbn),new BigInteger(10000,new Random()));
    }
}
