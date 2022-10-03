package rmi.services.classes;

import rmi.services.interfaces.IClientBox;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    public ClientBox() throws RemoteException {
    }

    @Override
    public synchronized void stream(byte[] chunck) throws RemoteException {
        try {
            // just to give the loading effect
            wait(100);
            System.out.print(new String(chunck));
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Failed to stream, retrying");
        }
    }
}
