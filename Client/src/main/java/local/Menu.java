package local;

import local.classes.Bill;
import local.classes.MovieDesc;
import local.exceptions.InvalidCredentialsException;
import rmi.services.classes.ClientBox;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

// Menu class representing the menu customer will interact with
// to sign-in, choose a movie ...
public class Menu {
    private boolean LoggedIn = false; // boolean set to check if user is logged-in
    private int tries = 3; // attempts number to sign-in

    private IVODService vodService = null; // default vod service
    private final ClientBox box = new ClientBox();

    public Menu() throws RemoteException {
    }

    // start method launching the Menu for user with connection remote reference
    void start(IConnection con) throws RemoteException, InterruptedException {
            int choice = 0; // user's choice between signing in or up

            Scanner scan = new Scanner(System.in);

            String email = "";
            String password = "";
            String isbn = "";

            System.out.println("Welcome to VOD!\n");
            System.out.println("Please choose an option : \n1 - Sign up \n2 - Sign In");
            System.out.print("Choosing... ");
            choice = scan.nextInt(); // asking user to enter a number according to his choice

            // running loop while user is not logged in and has remaining attempts
            while(tries!=0 && !LoggedIn) {
                System.out.print("\nEmail : ");
                email = scan.next(); // asking user to enter an email

                System.out.print("Password : ");
                password = scan.next(); // asking user to enter a password

                // switch depending on user's previous choice
                switch(choice){
                    case 1 :
                        LoggedIn = con.signup(email,password); // call signup method from Connection class got from remote reference
                        if(LoggedIn)
                            vodService = con.login(email,password); // if signup succeeded, user is automatically logged in
                        break;
                    case 2 :
                        try{
                            vodService = con.login(email,password); // call login method from Connection class got from remote reference
                            LoggedIn = true; // if previous method didn't throw exception then login succeeded
                        }catch(InvalidCredentialsException ex){
                            System.err.println("Invalid Credentials");
                        }
                }
                tries--; // remove one try for user
            }

            // if user got logged in with previous steps then continue to display
            if(LoggedIn){
                System.out.println("\n=================================== Successfully authenticated ===================================\n");
                List<MovieDesc> movies = vodService.viewCatalog(); // getting movies' descriptions from VOD service

                int i = 1;
                // loop displaying movies' descriptions from the list
                for (MovieDesc m : movies) {
                    System.out.print(i + "- ");
                    System.out.println(m);
                    i++;
                }

                System.out.print("Pick a movie (ISBN) : ");
                isbn = scan.next(); // asking user to choose a movie by typing an ISBN

                System.out.println(box.getClass());
                Bill bill = vodService.PlayMovie(isbn,box); // getting the bill associated to selected movie from VOD service and playing movie

                System.out.println("\n=================================== Thanks for watching ===================================\n");
                System.out.println("That was : " + bill); // displaying the bill

            } else {
                System.out.println("Failed to login, please try again later.");
            }
    }
}
