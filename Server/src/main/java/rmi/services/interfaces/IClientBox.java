package rmi.services.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ClientBox Interface exposing the service stream
 * to send the byte streams to the client
 */
public interface IClientBox extends Remote {
    void stream(byte[] chunck) throws RemoteException;
}
