package local;

import local.exceptions.SignUpFailed;
import rmi.services.interfaces.IConnection;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Permission;

public class Main {

    /**
     * SecurityManager class
     */
    private static class MySecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            return;
        }
    }


    /**
     * Main method running the client
     */
    public static void main(String[] args) {
        try {
            System.out.println("Starting ...");

            // Setting the system SecurityManager
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new MySecurityManager());
            }

            // Setting the policies of our security manager
            System.setProperty("java.rmi.server.useCodebaseOnly","false");
            System.setProperty("java.security.policy","file://./security.policy");

            // Used to obtain a reference to a remote object registry on specified port
            Registry registry = LocateRegistry.getRegistry(2002);

            // Getting the remote reference bound to the specified name in this registry.
            IConnection con = (IConnection) registry.lookup("con");

            Menu menu = new Menu();

            // Starting Menu with the connection remote reference
            menu.start(con);

        } catch (SignUpFailed | RemoteException | NotBoundException e){
            System.err.println("Failed to start");
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
