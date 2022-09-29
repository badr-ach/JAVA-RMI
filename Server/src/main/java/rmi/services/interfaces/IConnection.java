package rmi.services.interfaces;

import rmi.exceptions.InvalidCredentialsException;
import rmi.exceptions.SignInFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {
    boolean signup(String email,String password) throws SignInFailed, RemoteException;
    IVODService login(String email, String password) throws InvalidCredentialsException, RemoteException;
}
