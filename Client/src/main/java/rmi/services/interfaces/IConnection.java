package rmi.services.interfaces;

import local.exceptions.InvalidCredentialsException;
import local.exceptions.SignUpFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  The Connection interface offering two services
 *  sign up and log in to use the VOD service
 */
public interface IConnection extends Remote {
    boolean signup(String email,String password) throws SignUpFailed, RemoteException;
    IVODService login(String email, String password) throws InvalidCredentialsException, RemoteException;
}
