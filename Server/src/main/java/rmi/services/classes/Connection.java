package rmi.services.classes;

import rmi.exceptions.InvalidCredentialsException;
import rmi.exceptions.SignInFailed;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection {
    public Connection() throws RemoteException{}

    @Override
    public boolean signup(String email, String password) throws SignInFailed, RemoteException {

        return false;
    }

    @Override
    public IVODService login(String email, String password) throws InvalidCredentialsException, RemoteException {

        return null;
    }
}
