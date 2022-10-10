package rmi.services.classes;

import local.db.dao.Users;
import local.db.models.User;
import local.exceptions.InvalidCredentialsException;
import local.exceptions.SignUpFailed;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * Remote Object Class Connection used to instantiate a distant object
 * that will allow the users to connect and use the VOD service in the server
 */
public class Connection extends UnicastRemoteObject implements IConnection {

    /**
     * Data Access Object to interact with users data stored in json db
     */
    private Users usersDAO;

    /**
     * Default Constructor used to initialize
     * the Data Access Object for users
     */
    public Connection() throws RemoteException {
        try{
        usersDAO = new Users();}
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Sign up method to add a new user to the VOD service
     * @param email     the email of the user which shouldn't exist in the db already.
     * @param password  the password that the user wishes to use
     * @return boolean will be always returning true in case of a successful signup
     *                 or else throwing an Exception
     */
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

    /**
     * Login method to log the user to the VOD service
     * @param email     the email of the user wishing to connect
     * @param password  the password of the user
     * @return IVODService the VODService object that the client will use to access the services provided
     */
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
