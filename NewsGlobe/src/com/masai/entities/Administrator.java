package com.masai.entities;

import com.masai.services.Article;

public class Administrator extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrator(String username, String password, String name, String email) {
        super(username, password, name, email);
    }
	
	public boolean login(String username, String password) {
        if (getUsername().equals("admin") && getPassword().equals("admin")) {
            System.out.println("Administrator logged in.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }
	
	public void logout() {
        System.out.println("Administrator logged out.");
    }
	
	public void removeArticle(Article article) {
        // Remove the article from the platform based on the provided guidelines
        System.out.println("Article removed: " + article.getTitle());
    }

    public void editArticle(Article article) {
        // Edit the article based on the provided guidelines
        System.out.println("Article edited: " + article.getTitle());
    }
}
