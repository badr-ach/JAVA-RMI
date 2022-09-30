package rmi;

import net.minidev.json.parser.ParseException;
import rmi.db.dao.Users;
import rmi.services.classes.Connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        try{
        Registry reg = LocateRegistry.createRegistry(2002);
        reg.rebind("con",new Connection());
        System.out.println("Server Ready.");
        }catch(Exception e){
                System.err.println("Server failed to start : " + e.getMessage());
        }

        Users u = new Users();
        u.lookUpUser("","");
    }
}
