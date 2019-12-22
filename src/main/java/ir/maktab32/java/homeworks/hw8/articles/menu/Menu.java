package ir.maktab32.java.homeworks.hw8.articles.menu;

import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.share.AuthenticationService;

public class Menu {
    public static void display(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null) {
            System.out.println("+---------------------------------+");
            System.out.println("|                Menu             |");
            System.out.println("+---------------------------------+");
            System.out.println("| sign in  --> Sign In            |");
            System.out.println("| sign up  --> Sign Up            |");
            System.out.println("| articles --> Articles           |");
            System.out.println("| view     --> Article Details    |");
            System.out.println("+---------------------------------+");
        }
        else{
            System.out.println("+---------------------------------+");
            System.out.println("|                Menu             |");
            System.out.println("+---------------------------------+");
            System.out.println("| sign in  --> Sign In            |");
            System.out.println("| sign up  --> Sign Up            |");
            System.out.println("| sign out --> Sign Out          |");
            System.out.println("| articles --> Articles           |");
            System.out.println("| view     --> Article Details    |");
            System.out.println("+---------------------------------+");
        }
    }
}
