package rmi.services.interfaces;

import rmi.contracts.Bill;
import rmi.contracts.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends Remote {
    List<MovieDesc> viewCatalog();
    Bill PlayMovie(String isbn, IClientBox box) throws RemoteException;
}
