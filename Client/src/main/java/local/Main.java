package local;

import local.exceptions.SignUpFailed;
import rmi.services.interfaces.IConnection;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Permission;

public class Main {

    // SecurityManager class, has to check permissions
    private static class MySecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            return;
        }
    }


    // Main method running the client
    public static void main(String[] args) {
        try {
            System.out.println("Starting ..."); // Displayed starting client message

            // Setting the system SecurityManager
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new MySecurityManager());
            }
//            System.setProperty("java.rmi.server.codebase", "http://DESKTOP-U4R1V9R:2001/");
//            System.out.println("Connected to the codebade : " +
//                    System.getProperty("java.rmi.server.codebase"));

            System.setProperty("java.rmi.server.useCodebaseOnly","false");
            System.setProperty("java.security.policy","file://./security.policy");

            Registry registry = LocateRegistry.getRegistry(2002); // Used to obtain a reference to a remote object registry on specified port
            IConnection con = (IConnection) registry.lookup("con"); // Getting the remote reference bound to the specified name in this registry.

            Menu menu = new Menu();
            menu.start(con); // Starting Menu with the connection remote reference

        // Catching exceptions
        } catch (SignUpFailed | RemoteException | NotBoundException e){
            System.err.println("Failed to start");
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
