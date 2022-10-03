package local;

import local.exceptions.SignUpFailed;
import rmi.services.interfaces.IConnection;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Permission;

public class Main {

    private static class MySecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            return;
        }
    }


    public static void main(String[] args) {
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
