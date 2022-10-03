package rmi.services.classes;

import net.minidev.json.parser.ParseException;
import rmi.db.dao.Users;
import rmi.db.models.User;
import rmi.exceptions.InvalidCredentialsException;
import rmi.exceptions.SignUpFailed;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection {

    private Users usersDAO;
    public Connection() throws RemoteException {
        try{
        usersDAO = new Users();}
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

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
