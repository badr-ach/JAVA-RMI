package rmi.services.interfaces;

import local.classes.Bill;
import local.classes.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * IVOD Service Interface representing the services offered
 * including : viewing the catalog and playing a movie
 */
public interface IVODService extends Remote {
    List<MovieDesc> viewCatalog() throws RemoteException;
    Bill PlayMovie(String isbn, IClientBox box) throws RemoteException;
}
