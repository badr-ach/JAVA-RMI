package rmi.services.interfaces;

import rmi.exceptions.InvalidCredentialsException;
import rmi.exceptions.SignUpFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {
    boolean signup(String email,String password) throws SignUpFailed, RemoteException;
    IVODService login(String email, String password) throws InvalidCredentialsException, RemoteException;
}
