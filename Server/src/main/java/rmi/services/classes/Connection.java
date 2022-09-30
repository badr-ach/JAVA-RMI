package rmi.services.classes;

import rmi.db.dao.Users;
import rmi.db.models.User;
import rmi.exceptions.InvalidCredentialsException;
import rmi.exceptions.SignUpFailed;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection {

    private Users usersDAO;
    public Connection() throws RemoteException{}

    @Override
    public boolean signup(String email, String password) throws SignUpFailed, RemoteException {
        User user = new User(email,password);
        boolean found = usersDAO.findUser(user);
        if(found) {
            throw new SignUpFailed();
        }else{
            usersDAO.AddUser(user);
            return true;
        }
    }

    @Override
    public IVODService login(String email, String password) throws InvalidCredentialsException, RemoteException {
        User user = new User(email,password);
        boolean found = usersDAO.findUser(user);
        if(!found) {
            throw new InvalidCredentialsException();
        }else{
            return new VODService();
        }
    }
}
