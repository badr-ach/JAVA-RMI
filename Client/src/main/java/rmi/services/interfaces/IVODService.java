package rmi.services.interfaces;

import local.classes.Bill;
import local.classes.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// VOD Service interface representing the VOD Service
// displays movies catalog and play movies
public interface IVODService extends Remote {
    List<MovieDesc> viewCatalog() throws RemoteException; // displaying movies' descriptions list
    Bill PlayMovie(String isbn, IClientBox box) throws RemoteException; // playing movie with specified ISBN
}
