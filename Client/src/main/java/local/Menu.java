package local;

import local.classes.Bill;
import local.classes.MovieDesc;
import local.exceptions.InvalidCredentialsException;
import rmi.services.classes.ClientBox;
import rmi.services.interfaces.IConnection;
import rmi.services.interfaces.IVODService;

import java.io.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Menu class representing the menu that the user
 * will interact with to sign-in, choose a movie ...
 */
public class Menu {
    /**
     * boolean set to check if user is logged-in
     */
    private boolean LoggedIn = false;
    /**
     * Attempts number to Sign In
     */
    private int tries = 3;

    /**
     * Vod Service that will hold the distant object
     */
    private IVODService vodService = null;
    /**
     * Current Client Box wishing to use the service
     * and which will be used as a distant object too
     */
    private final ClientBox box = new ClientBox();

    public Menu() throws RemoteException {
    }


    /**
     * Start method launching the Menu for user with connection remote reference
     * @param con Connection stub obtained from the registry
     * @throws RemoteException
     * @throws InterruptedException
     */
    void start(IConnection con) throws RemoteException, InterruptedException, FileNotFoundException, UnsupportedEncodingException {
            int choice = 0; // user's choice between sign in or sign up

            Scanner scan = new Scanner(System.in);

            String email = "";
            String password = "";
            String isbn = "";

            System.out.println("Welcome to VOD!\n");
            System.out.println("Please choose an option : \n1 - Sign up \n2 - Sign In");
            System.out.print("Your choice is : ");

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
                Bill bill = vodService.PlayMovie(isbn,box); // getting the bill associated to selected movie from VOD service and playing movie

                System.out.println("\n=================================== Thanks for watching ===================================\n");
                saveBill(bill);

            } else {
                System.out.println("Failed to login, please try again later.");
            }
    }

    /**
     * Saves the bill to a .csv file in the project directory
     * @param bill the bill to be saved
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private void saveBill(Bill bill) throws FileNotFoundException, UnsupportedEncodingException {
        String savestr = "../Factures.csv";
        File f = new File(savestr);

        PrintWriter out = null;
        if ( f.exists() && !f.isDirectory() ) {
            out = new PrintWriter(new FileOutputStream(new File(savestr), true));
        }
        else {
            out = new PrintWriter(savestr);
            out.append("Movie Name;Price\n");
        }
        out.append(bill.getMovieName()+";"+bill.getOutrageousPrice()+"\n");
        out.close();
    }
}
