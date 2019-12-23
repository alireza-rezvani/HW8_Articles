package ir.maktab32.java.homeworks.hw8.articles;

import ir.maktab32.java.homeworks.hw8.articles.commandsmanager.Command;

import java.util.Scanner;

public class ArticlesApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = "";
        while (!command.equals("exit")){

            System.out.println("\n\u29bf What Do You Want?\t(Type menu to See Menu)");
            command = scanner.nextLine();

            if (command.equals("menu")){
                Command.displayMenu();
            }
            else if (command.equals("sign up")){
                Command.signUp();
            }
            else if (command.equals("sign in")){
                Command.signIn();
            }
            else if (command.equals("sign out")){
                Command.signOut();
            }
            else if (command.equals("all")){
                Command.displayAllArticles();
            }
            else if (command.equals("article item")){
                Command.displayArticleItem();
            }
            else if (command.equals("settings")){
                Command.settings();
            }
            else if (command.equals("my articles")){
                Command.displayUserArticles();
            }
            else if (command.equals("edit")){
                Command.editArticle();
            }
            else if (command.equals("add")){
                Command.addArticle();
            }
            else if (command.equals("publish")){
                Command.publishArticle();
            }
            else if (command.equals("search")){
                Command.searchArticle();
            }
            else if (command.equals("dashboard")){
                Command.displayDashboard();
            }
            else if (command.equals("exit")){
                System.out.println("\t\u2705 Bye!");
            }
            else {
                System.out.println("\t\u26a0 Invalid Command!");
            }
        }
    }
}
