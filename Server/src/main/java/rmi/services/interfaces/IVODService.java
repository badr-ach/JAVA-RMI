package rmi.services.interfaces;

import local.classes.Bill;
import local.classes.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends Remote {
    List<MovieDesc> viewCatalog() throws RemoteException;
    Bill PlayMovie(String isbn, IClientBox box) throws RemoteException;
}
