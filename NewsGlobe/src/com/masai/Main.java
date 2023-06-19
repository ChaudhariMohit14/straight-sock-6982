package com.masai;

import java.util.ArrayList;


import java.util.List;
import java.util.Scanner;
import java.io.*;

import com.masai.entities.*;
import com.masai.entities.Reader;
import com.masai.services.*;

public class Main implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<User> users = new ArrayList<>();
    private static List<Article1> articles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Create some initial users for testing
            User admin = new Administrator("admin", "admin", "Admin User", null);
            User journalist = new Journalist("journalist", "12345", "Journalist User", null);
            users.add(admin);
            users.add(journalist);

            boolean exit = false;
            User loggedInUser = null;

            while (!exit) {
                System.out.println("Main Menu:");
                System.out.println("1. Reader Login");
                System.out.println("2. Reader Signup");
                System.out.println("3. Journalist Login");
                System.out.println("4. Admin Login");
                System.out.println("5. Exit");

                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        loggedInUser = readerLogin(scanner);
                        break;
                    case 2:
                        readerSignup(scanner);
                        break;
                    case 3:
                        loggedInUser = journalistLogin(scanner);
                        break;
                    case 4:
                        loggedInUser = adminLogin(scanner);
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

                if (loggedInUser != null) {
                    if (loggedInUser instanceof Reader) {
                        readerMenu(scanner, (Reader) loggedInUser);
                    } else if (loggedInUser instanceof Journalist) {
                        journalistMenu(scanner, (Journalist) loggedInUser);
                    } else if (loggedInUser instanceof Administrator) {
                        adminMenu(scanner);
                    }
                    loggedInUser = null; // Reset the logged-in user after returning from the specific role's functionality
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            // Handle the exception or display an error message
        } finally {
            // Code to be executed regardless of whether an exception occurred or not
            scanner.close(); 
            
         // serialization (finally always executed)
            try {
				ObjectOutputStream joos = new ObjectOutputStream(new FileOutputStream("Journalist.ser"));
				joos.writeObject(articles);
				ObjectOutputStream roos = new ObjectOutputStream(new FileOutputStream("Reader.ser"));
				roos.writeObject(articles);

				ObjectOutputStream aoos = new ObjectOutputStream(new FileOutputStream("Article.ser"));
				aoos.writeObject(articles);
				
				ObjectOutputStream Aoos = new ObjectOutputStream(new FileOutputStream("Administrator.ser"));
				Aoos.writeObject(articles);
			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
        }
    }

    private static User readerLogin(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = login(username, password);

        if (loggedInUser != null && loggedInUser instanceof Reader) {
            System.out.println("Reader login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

        return loggedInUser;
    }

    private static void readerSignup(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        // Check if the username already exists
        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        try {
            Reader reader = new Reader(username, password, name, email);
            users.add(reader);
            System.out.println("Reader signup successful!");
            // Continue with the rest of the signup logic or operations for the newly signed-up reader
        } catch (Exception e) {
            System.out.println("Error occurred during signup: " + e.getMessage());
            // Handle the exception or display an error message
        }
    }

    private static boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    private static User journalistLogin(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = login(username, password);

        if (loggedInUser != null && loggedInUser instanceof Journalist) {
            System.out.println("Journalist login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

        return loggedInUser;
    }


    private static User adminLogin(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = login(username, password);

        if (loggedInUser != null && loggedInUser instanceof Administrator) {
            System.out.println("Admin login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

        return loggedInUser;
    }

    private static User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void readerMenu(Scanner scanner, Reader reader) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nReader Menu");
            System.out.println("1. Browse News Articles");
            System.out.println("2. Logout");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    browseNewsArticles(articles);
                    break;
                case 2:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void browseNewsArticles(List<Article1> articles) {
        System.out.println("\nBrowse News Articles");

        if (articles.isEmpty()) {
            System.out.println("No articles available.");
        } else {
            for (Article1 article : articles) {
                System.out.println("- " + article.getTitle() + " (" + article.getCategory() + ")");
                System.out.println("Published on: " + article.getPublishedDate());
                System.out.println();
            }
        }
    }


    private static void journalistMenu(Scanner scanner, Journalist journalist) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nJournalist Menu");
            System.out.println("1. Create Article");
            System.out.println("2. Edit Article");
            System.out.println("3. Publish Article");
            System.out.println("4. Logout");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    createArticle(scanner, journalist);
                    break;
                case 2:
                    editArticle(scanner, journalist);
                    break;
                case 3:
                    publishArticle(scanner, journalist);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createArticle(Scanner scanner, Journalist journalist) {
        System.out.println("\nCreate Article");
        System.out.println("Enter title: ");
        String title = scanner.nextLine();

        System.out.println("Enter content: ");
        String content = scanner.nextLine();

        Article1 article = new Article1(title, content, journalist, null, null);
        journalist.addArticle(article);

        System.out.println("Article created successfully!");
    }

    private static void editArticle(Scanner scanner, Journalist journalist) {
        System.out.println("\nEdit Article");
        System.out.println("Enter the index of the article you want to edit:");

        // Display the articles written by the journalist
        List<Article1> articles = journalist.getArticles();

        for (int i = 0; i < articles.size(); i++) {
            System.out.println(i + 1 + ". " + articles.get(i).getTitle());
        }

        int articleIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (articleIndex >= 0 && articleIndex < articles.size()) {
            Article1 selectedArticle = articles.get(articleIndex);

            System.out.println("Enter new content: ");
            String newContent = scanner.nextLine();

            selectedArticle.setContent(newContent);

            System.out.println("Article edited successfully!");
        } else {
            System.out.println("Invalid article index. Please try again.");
        }
    }

    private static void publishArticle(Scanner scanner, Journalist journalist) {
        System.out.println("\nPublish Article");
        System.out.println("Enter the index of the article you want to publish:");

        // Display the articles written by the journalist
        List<Article1> articles = journalist.getArticles();

        for (int i = 0; i < articles.size(); i++) {
            System.out.println(i + 1 + ". " + articles.get(i).getTitle());
        }

        int articleIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (articleIndex >= 0 && articleIndex < articles.size()) {
            Article1 selectedArticle = articles.get(articleIndex);

            if (!selectedArticle.isPublished()) {
                selectedArticle.setPublished(true);
                System.out.println("Article published successfully!");
            } else {
                System.out.println("Article is already published.");
            }
        } else {
            System.out.println("Invalid article index. Please try again.");
        }
    }


    private static void adminMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Remove Article");
            System.out.println("2. Edit Article");
            System.out.println("3. Logout");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    removeArticle(scanner);
                    break;
                case 2:
                    editArticle(scanner);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void removeArticle(Scanner scanner) {
        System.out.println("\nRemove Article");
        System.out.println("Enter the index of the article you want to remove:");

        // Display all articles
        for (int i = 0; i < articles.size(); i++) {
            System.out.println(i + 1 + ". " + articles.get(i).getTitle());
        }

        int articleIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (articleIndex >= 0 && articleIndex < articles.size()) {
            Article1 removedArticle = articles.remove(articleIndex);
            System.out.println("Article removed successfully:");
            System.out.println(removedArticle.getTitle());
        } else {
            System.out.println("Invalid article index. Please try again.");
        }
    }

    private static void editArticle(Scanner scanner) {
        System.out.println("\nEdit Article");
        System.out.println("Enter the index of the article you want to edit:");

        // Display all articles
        for (int i = 0; i < articles.size(); i++) {
            System.out.println(i + 1 + ". " + articles.get(i).getTitle());
        }

        int articleIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (articleIndex >= 0 && articleIndex < articles.size()) {
            Article1 selectedArticle = articles.get(articleIndex);

            System.out.println("Enter new content: ");
            String newContent = scanner.nextLine();

            selectedArticle.setContent(newContent);

            System.out.println("Article edited successfully!");
        } else {
            System.out.println("Invalid article index. Please try again.");
        }
    }
   

}

