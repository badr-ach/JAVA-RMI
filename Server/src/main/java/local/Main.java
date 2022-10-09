package local;

import net.minidev.json.parser.ParseException;
import rmi.services.classes.Connection;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    // Main method running the server
    public static void main(String[] args) throws IOException, ParseException {
        try{
            Registry reg = LocateRegistry.createRegistry(2002); // obtains a reference to a remote object registry on specified port
            reg.rebind("con",new Connection()); // binding the remote reference from the specified name in this registry.

            System.setProperty("java.rmi.server.codebase", "http://Ptah:2001/"); // specifies device and port for the server
            System.out.println("Connected to the codebase : " +
                    System.getProperty("java.rmi.server.codebase"));

            System.out.println("Server Ready.");
        }catch(Exception e){
                System.err.println("Server failed to start : " + e.getMessage());
        }
    }
}
