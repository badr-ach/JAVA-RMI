package rmi.services.interfaces;

import rmi.contracts.Bill;

import java.rmi.RemoteException;

public interface IVODService {

    Bill PlayMovie(String isbn, IClientBox box) throws RemoteException;
}
