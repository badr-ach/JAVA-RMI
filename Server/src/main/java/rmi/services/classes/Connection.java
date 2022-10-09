package rmi.services.classes;

import local.db.dao.Users;
import local.db.models.User;
import local.exceptions.InvalidCredentialsException;
import local.exceptions.SignUpFailed;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Connection class representing user connection to the VOD service
public class Connection extends UnicastRemoteObject implements IConnection {

    private Users usersDAO; // users manager through database
    public Connection() throws RemoteException {
        try{
        usersDAO = new Users();}
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // signup method to add new user to the VOD service
    @Override
    public boolean signup(String email, String password) throws SignUpFailed, RemoteException {
            User user = new User(email, password);
            boolean found = usersDAO.findUser(user);
            if (found) {
                throw new SignUpFailed();
            } else {
                usersDAO.AddUser(user);
                return true;
            }
    }

    // login method to log the user to the VOD service
    @Override
    public IVODService login(String email, String password) throws InvalidCredentialsException, RemoteException {
        User user = new User(email,password);
        boolean found = usersDAO.findUser(user);
        if(!found) {
            throw new InvalidCredentialsException();
        }else{
            try {
                return new VODService();
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Failed to started the service");
            }
        }
    }
}
