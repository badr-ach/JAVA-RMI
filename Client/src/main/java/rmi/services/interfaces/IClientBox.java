package rmi.services.interfaces;

import java.rmi.Remote;

public interface IClientBox extends Remote {
    void stream(byte[] chunck);
}
