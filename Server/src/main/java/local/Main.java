package local;

import net.minidev.json.parser.ParseException;
import rmi.services.classes.Connection;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Main Method that starts the server and the RMIRegistry then
 * registers the distant objects and sets the @httpserver which will be the codebase
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        try{
            // Creates the RMIRegistry and exports it on the localhost to accept request on the specified port
            Registry reg = LocateRegistry.createRegistry(2002);

            // binding the remote reference from the specified name in this registry.
            reg.rebind("con",new Connection());

            System.out.println("Connected to the codebase : " +
                    System.getProperty("java.rmi.server.codebase"));

            System.out.println("Server Ready.");
        }catch(Exception e){
                System.err.println("Server failed to start : " + e.getMessage());
        }
    }
}
