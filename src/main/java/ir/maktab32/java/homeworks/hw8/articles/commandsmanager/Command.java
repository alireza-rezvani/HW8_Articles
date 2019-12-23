package ir.maktab32.java.homeworks.hw8.articles.commandsmanager;

import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.entities.Category;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.impl.*;
import ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.impl.AddCategoryByUserUseCaseImpl;
import ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.impl.FindAllCategoriesUseCaseImpl;
import ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.impl.ChangePasswordUseCaseImpl;
import ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.impl.SignInUseCaseImpl;
import ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.impl.SignUpUseCaseImpl;
import ir.maktab32.java.homeworks.hw8.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw8.articles.utilities.IsNumeric;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Command {
    private static Scanner scanner = new Scanner(System.in);
    public static void displayMenu(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|                     Menu                  |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| sign in        -->  Sign In               |");
            System.out.println("| sign up        -->  Sign Up               |");
            System.out.println("| all            -->  all Articles          |");
            System.out.println("| article item   -->  Article Details       |");
            System.out.println("| search         -->  Search Articles       |");
            System.out.println("+-------------------------------------------+");
        }
        else{
            System.out.println("+-------------------------------------------+");
            System.out.println("|                     Menu                  |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| all            -->  all Articles          |");
            System.out.println("| article item   -->  Article Details       |");
            System.out.println("| my articles    -->  My Articles           |");
            System.out.println("| add            -->  Add Article           |");
            System.out.println("| edit           -->  Edit Article          |");
            System.out.println("| publish        -->  Publish Article       |");
            System.out.println("| dashboard      -->  Dashboard             |");
            System.out.println("| search         -->  Search Articles       |");
            System.out.println("| settings       -->  Settings              |");
            System.out.println("| sign out       -->  Sign Out              |");
            System.out.println("+-------------------------------------------+");
        }
    }
    public static void signUp(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser != null)
            System.out.println("\t\u26a0 Sign Out First!");
        else{
            System.out.print("\t\u29bf Username: ");
            String username = scanner.nextLine();

            String nationalCode = "";
            while ((!IsNumeric.run(nationalCode)) || nationalCode.length() != 10) {
                System.out.print("\t\u29bf National Code: ");
                nationalCode = scanner.nextLine();
                if ((!IsNumeric.run(nationalCode)) || nationalCode.length() != 10)
                    System.out.println("\t\t\u26a0 Invalid National Code!");
            }

            System.out.print("\t\u29bf Birth Date: ");
            String birthDate = scanner.nextLine();

            try {
                User user = new SignUpUseCaseImpl().signUp(
                        new User(null, username, nationalCode, nationalCode, birthDate, null));
                if (user != null) {
                    System.out.println("\t\t\u2705 Sign Up Successful!");
                    System.out.println("\t\t\u2705 Your Password: National Code\t(Go to Settings to Change the Password)");
                    AuthenticationService.getInstance().setSignedInUser(user);
                }
                else
                    System.out.println("\t\t\u26a0 Sign Up Failed!");
            }catch (Exception e) {
                System.out.println("\t\t\u26a0 Sign Up Failed!");
                System.out.println("\t\t\u26a0 " + e.getMessage());
            }
        }
    }
    public static void signIn(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser != null)
            System.out.println("\t\u26a0 Sign Out First!");
        else{
            System.out.print("\t\u29bf Username: ");
            String username = scanner.nextLine();
            System.out.print("\t\u29bf Password: ");
            String password = scanner.nextLine();
            try {
                User user = new SignInUseCaseImpl().signIn(username, password);
                if (user != null) {
                    System.out.println("\t\t\u2705 Sign In Successful!");
                    AuthenticationService.getInstance().setSignedInUser(user);
                }
                else
                    System.out.println("\t\t\u26a0 Sign In Failed!");
            }catch (Exception e){
                System.out.println("\t\t\u26a0 Sign In Failed!");
                System.out.println("\t\t\u26a0 " + e.getMessage());
            }
        }
    }
    public static void signOut(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null)
            System.out.println("\t\u26a0 You Are not Signed In!");
        else {
            AuthenticationService.getInstance().setSignedInUser(null);
            System.out.println("\t\u2705 Sign out Successful!");
        }
    }
    public static void displayAllArticles(){
        try {
            List articles = new FindAllArticlesUseCaseImpl().articleList();
            if (articles.size() == 0)
                System.out.println("\t\u26a0 There is No Article to Show!");
            else
                for (Object i : articles){
                    Article articleItem = (Article) i;
                    System.out.println("\t\u2705 Id: " + articleItem.getId()
                            + "\tTitle: " + articleItem.getTitle() + "\tBrief: " + articleItem.getBrief());
                }
            System.out.println("\n\t\t\u2705 Go to 'article item' for More Details!");
        }catch (Exception e){
            System.out.println("\t\u26a0 Finding Articles Failed!");
            System.out.println("\t\u26a0 " + e.getMessage());
        }
    }
    public static void displayArticleItem(){
        String id = "";
        while (!IsNumeric.run(id)) {
            System.out.print("\t\u29bf Article Id: ");
            id = scanner.nextLine();
            if (!IsNumeric.run(id))
                System.out.println("\t\t\u26a0 Invalid Article Id!");
        }
        try {
            Article article = new FindArticleByIdUseCaseImpl().find(Integer.parseInt(id));
            System.out.println("\t\t\u2705 " + article);
        }catch (Exception e){
            System.out.println("\t\t\u26a0 " + e.getMessage());
        }
    }
    public static void displayUserArticles(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null)
            System.out.println("\t\u26a0 Sign In First!");
        else {
            List<Article> articleList = new FindArticlesByUserUseCaseImpl().articlesList(signedInUser);
            if (articleList.size() == 0)
                System.out.println("\t\u26a0 You Have No Articles!");
            else {
                for (Article i : articleList)
                    System.out.println("\t\u2705 " + i);
            }
        }
    }
    public static void addArticle(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null)
            System.out.println("\t\u26a0 Sign In First!");
        else {
            try {
                System.out.print("\t\u29bf Title: ");
                String title = scanner.nextLine();
                System.out.print("\t\u29bf Brief: ");
                String brief = scanner.nextLine();
                System.out.print("\t\u29bf Content: ");
                String content = scanner.nextLine();
                System.out.println("\t\u29bf Choose Category:\t(Press n to Create a New Category)");
                Category category = null;
                List<Category> categories = new FindAllCategoriesUseCaseImpl().list();
                for (int i = 0; i < categories.size(); i++) {
                    System.out.println((i + 1) + ". " + categories.get(i));
                }
                String categoryChoice = scanner.nextLine();
                if (categoryChoice.equals("n")) {
                    System.out.println("\t\t\u29bf Category Title: ");
                    String categoryTitle = scanner.nextLine();
                    System.out.print("\t\t\u29bf Category Description: ");
                    String categorydesc = scanner.nextLine();
                    category = new AddCategoryByUserUseCaseImpl().add(new Category(null, categoryTitle, categorydesc, null));
                } else {
                    if (IsNumeric.run(categoryChoice) && Integer.parseInt(categoryChoice) <= categories.size())
                        category = categories.get(Integer.parseInt(categoryChoice) - 1);
                    else
                        System.out.println("\t\t\u26a0 Invalid Input!");
                }
                Article article = new Article(null, title, brief, content, new Date(), null, null, false, signedInUser, category);
                new AddArticleByUserUseCaseImpl().add(article);
                System.out.println("\t\t\u2705 Article Added Successfully!");
            }catch (Exception e){
                System.out.println("\t\t\u26a0 Adding Article Failed!");
                System.out.println("\t\t\u26a0 " + e.getMessage());
            }
        }
    }
    public static void editArticle(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null)
            System.out.println("\t\u26a0 Sign In First!");
        else {
            String id = "";
            while (!IsNumeric.run(id)) {
                System.out.print("\t\u29bf Article Id: ");
                id = scanner.nextLine();
                if (!IsNumeric.run(id))
                    System.out.println("\t\t\u26a0 Invalid Article Id!");
            }
            try {
                Article article = new FindArticleByIdUseCaseImpl().find(Integer.parseInt(id));
                if (!article.getAuthor().getUsername().equals(signedInUser.getUsername())){
                    System.out.println("\t\t\u26a0 You aren't allowed to edit this article!");
                }
                else {
                    boolean isEdited = false;
                    System.out.println("\t\t\u29bf New Title:\t(Press * to Skip)");
                    String title = scanner.nextLine();
                    if (!title.equals("*")) {
                        article.setTitle(title);
                        isEdited = true;
                    }
                    System.out.println("\t\t\u29bf New Brief:\t(Press * to Skip)");
                    String brief = scanner.nextLine();
                    if (!brief.equals("*")) {
                        article.setBrief(brief);
                        isEdited = true;
                    }
                    System.out.println("\t\t\u29bf New Content:\t(Press * to Skip)");
                    String content = scanner.nextLine();
                    if (!content.equals("*")) {
                        article.setBrief(content);
                        isEdited = true;
                    }

                    System.out.println("\t\t\u29bf Choose New Category:\t(Press n to Create a New Category | Press * to Skip)");
                    Category category = null;
                    List<Category> categories = new FindAllCategoriesUseCaseImpl().list();
                    for (int i = 0; i< categories.size(); i++)
                        System.out.println((i+1) + ". " + categories.get(i));
                    String categoryChoice = scanner.nextLine();
                    if (!categoryChoice.equals("*")) {

                        Category oldCategory = article.getCategory();
                        if (categoryChoice.equals("n")) {
                            System.out.print("\t\t\t\u29bf Category Title: ");
                            String categoryTitle = scanner.nextLine();
                            System.out.print("\t\t\t\u29bf Category Description: ");
                            String categorydesc = scanner.nextLine();
                            category = new AddCategoryByUserUseCaseImpl().add(new Category(null, categoryTitle, categorydesc, null));
                        } else {
                            if (IsNumeric.run(categoryChoice) && Integer.parseInt(categoryChoice)<= categories.size()) {
                                category = categories.get(Integer.parseInt(categoryChoice) - 1);
                            }
                            else
                                System.out.println("\t\t\t\u26a0 Invalid Input!");
                        }
                        if (!oldCategory.equals(category)){
                            article.setCategory(category);
                            isEdited = true;
                        }
                    }

                    boolean oldIsPublished = article.getIsPublished();
                    System.out.println("\t\t\u29bf New Publish Status:\t(t = True | f = False | other keys = Skip)");
                    String isPublished = scanner.nextLine();
                    if (isPublished.equals("t"))
                        article.setIsPublished(true);
                    else if (isPublished.equals("f"))
                        article.setIsPublished(false);
                    boolean newIsPublished = article.getIsPublished();
                    if (oldIsPublished != newIsPublished){
                        isEdited = true;
                        if (newIsPublished == true)
                            article.setPublishDate(new Date());
                    }
                    if (isEdited == true) {
                        article.setLastUpdateDate(new Date());
                        new UpdateArticleByUserUseCaseImpl().update(Integer.parseInt(id), article);
                        System.out.println("\t\t\t\u2705 Article Edited Successfully!");
                    }
                    else
                        System.out.println("\t\t\t\u26a0 There is No Change to Update!");
                }
            }catch (Exception e){
                System.out.println("\t\t\u26a0 Edit Failed!");
                System.out.println("\t\t\u26a0 " + e.getMessage());
            }
        }
    }
    public static void publishArticle(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null)
            System.out.println("\t\u26a0 Sign In First!");
        else {
            System.out.print("\t\u29bf Article Id: ");
            String id = scanner.nextLine();
            if (IsNumeric.run(id)) {
                try {
                    Article article = new FindArticleByIdUseCaseImpl().find(Integer.parseInt(id));
                    if (!article.getAuthor().getUsername().equals(signedInUser.getUsername())) {
                        System.out.println("\t\t\u26a0 You aren't allowed to publish this article!");
                    }
                    else if (article.getIsPublished() == false){
                        article.setIsPublished(true);
                        article.setPublishDate(new Date());
                        article.setLastUpdateDate(new Date());
                        new UpdateArticleByUserUseCaseImpl().update(Integer.parseInt(id), article);
                        System.out.println("\t\t\u2705 Article Published Successfully!");
                    }
                    else
                        System.out.println("\t\t\u26a0 Article Is Published Already!");
                } catch (Exception e) {
                    System.out.println("\t\t\u26a0 Publish Failed!");
                    System.out.println("\t\t\u26a0 " + e.getMessage());
                }
            }
            else
                System.out.println("\t\t\u26a0 Invalid Input!");
        }
    }
    public static void displayDashboard(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null){
            System.out.println("\t\u26a0 Sign In First!");
        }
        else {
            try {
                int allArticlesCount = new FindAllArticlesUseCaseImpl().articleList().size();
                int userArticlesCount = new FindArticlesByUserUseCaseImpl().articlesList(signedInUser).size();
                System.out.println("\t\u2705 Number of All Articles: " + allArticlesCount);
                System.out.println("\t\u2705 Number of Your Articles: " + userArticlesCount);
            }catch (Exception e){
                System.out.println("\t\u26a0 " + e.getMessage());
            }
        }
    }
    public static void searchArticle(){
        System.out.println("1. Search By Author");
        System.out.println("2. Search By Title");
        String choice = scanner.nextLine();
        try {
            if (choice.equals("1")) {
                System.out.print("\t\u29bf Author: ");
                String author = scanner.nextLine();
                List<Article> articles = new FindArticleByAuthorUseCaseImpl().list(author);
                if (articles.size() == 0)
                    System.out.println("\t\t\u26a0 No Matches!");
                else
                    for (Article i : articles)
                        System.out.println("\t\t\u2705 " + i);
            } else if (choice.equals("2")) {
                System.out.print("\t\u29bf Title: ");
                String title = scanner.nextLine();
                List<Article> articles = new FindArticleByTitleUseCaseImpl().list(title);
                if (articles.size() == 0)
                    System.out.println("\t\t\u26a0 No Matches!");
                else
                    for (Article i : articles)
                        System.out.println("\t\t\u2705 " + i);
            } else {
                System.out.println("Invalid Choice!");
            }
        }catch (Exception e){
            System.out.println("\t\t\u26a0 Search Failed!");
            System.out.println("\t\t\u26a0 " + e.getMessage());
        }
    }
    public static void settings(){
        User signedInUser = AuthenticationService.getInstance().getSignedInUser();
        if (signedInUser == null)
            System.out.println("\t\u26a0 Sign In First!");
        else {
            System.out.println("1. Change Password");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.print("\u29bf Enter Your New Password: ");
                String password = scanner.nextLine();
                try {
                    new ChangePasswordUseCaseImpl().setNewPass(password);
                    System.out.println("\t\u2705 Password Changed Successfully!");
                } catch (Exception e) {
                    System.out.println("\t\u26a0 Failed to Change Password!");
                    System.out.println("\t\u26a0 " + e.getMessage());
                }
            } else {
                System.out.println("\t\u26a0 Invalid Choice!");
            }
        }
    }
}
