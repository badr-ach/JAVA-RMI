package rmi;

import rmi.contracts.Bill;
import rmi.contracts.MovieDesc;
import rmi.exceptions.InvalidCredentialsException;
import rmi.exceptions.SignUpFailed;
import rmi.services.classes.ClientBox;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Permission;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class MySecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            return;
        }
    }


    public synchronized static void main(String[] args) {
        try {
            System.out.println("Starting ...");

            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new MySecurityManager());
            }
//            System.setProperty("java.rmi.server.codebase", "http://DESKTOP-U4R1V9R:2001/");
//            System.out.println("Connected to the codebade : " +
//                    System.getProperty("java.rmi.server.codebase"));

            System.setProperty("java.rmi.server.useCodebaseOnly","false");
            System.setProperty("java.security.policy","file://./security.policy");

            Registry registry = LocateRegistry.getRegistry(2002);
            IConnection con = (IConnection) registry.lookup("con");

            Menu menu = new Menu();
            menu.start(con);

        }catch (SignUpFailed | RemoteException | NotBoundException e){
            System.err.println("Failed to start");
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
