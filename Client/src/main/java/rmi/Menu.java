package rmi;

import rmi.contracts.Bill;
import rmi.contracts.MovieDesc;
import rmi.exceptions.InvalidCredentialsException;
import rmi.services.classes.ClientBox;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    private boolean LoggedIn = false;
    private int tries = 3;

    private IVODService vodService = null;
    private final ClientBox box = new ClientBox();

    public Menu() throws RemoteException {
    }

    synchronized void start(IConnection con) throws RemoteException, InterruptedException {
            int choice = 0;
            Scanner scan = new Scanner(System.in);

            String email = "";
            String password ="";
            String isbn="";

            System.out.println("Welcome to VOD!\n");
            System.out.println("Please choose an option : \n1 - Sign up \n2 - Sign In");
            System.out.print("Choosing... ");
            choice = scan.nextInt();

            while(tries!=0 && !LoggedIn) {
                System.out.print("\nEmail : ");
                email = scan.next();

                System.out.print("Password : ");
                password = scan.next();

                switch(choice){
                    case 1 :
                        LoggedIn = con.signup(email,password);
                        if(LoggedIn)
                            vodService = con.login(email,password);
                        break;
                    case 2 :
                        try{
                            vodService = con.login(email,password);
                            LoggedIn = true;
                        }catch(InvalidCredentialsException ex){
                            System.err.println("Invalid Credentials");
                        }
                }
                tries--;
            }

            if(LoggedIn){
                System.out.println("\n=================================== Successfully authenticated ===================================\n");
                List<MovieDesc> movies = vodService.viewCatalog();
                int i = 1;
                for (MovieDesc m : movies) {
                    System.out.print(i + "- ");
                    System.out.println(m);
                    i++;
                }
                System.out.print("Pick a movie (ISBN) : ");
                isbn = scan.next();

                Bill bill = vodService.PlayMovie(isbn,box);


                System.out.println("\n=================================== Thanks for watching ===================================\n");
                System.out.println("That was : " + bill);
            }else{
                System.out.println("Failed to login, please try again later.");
            }
    }
}
