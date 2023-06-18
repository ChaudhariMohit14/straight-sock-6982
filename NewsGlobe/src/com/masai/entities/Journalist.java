package com.masai.entities;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import com.masai.services.Article;

public class Journalist extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Article> articles;

	public Journalist(String username, String password, String name, String email) {
        super(username, password, name, email);
        this.articles = new ArrayList<>();
    }
	
	public boolean login(String username, String password) {
        if (getUsername().equals("journalist") && getPassword().equals("12345")) {
            System.out.println("Journalist logged in.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }
	
	public void logout() {
        System.out.println("Journalist logged out.");
    }
	
	public void createArticle(String title, String content) {
        Article article = new Article(title, content, this, null, null);
        articles.add(article);
        System.out.println("Article created: " + article.getTitle());
    }
	
	public void addArticle(Article article) {
	    articles.add(article);
	    System.out.println("Article added successfully!");
	}
	
	public List<Article> getArticles() {
        return articles;
    }

    public void publishArticle(Article article) {
    	if (article.isPublished()) {
            System.out.println("Article is already published.");
        } else {
            // Publish the article
            article.setPublished(true);
            System.out.println("Article published: " + article.getTitle());
        }
    }

    public void editArticle(Article article, String newContent) {
    	if (!article.isPublished()) {
            // Update the article content
            article.setContent(newContent);
            System.out.println("Article edited: " + article.getTitle());
        } else {
            System.out.println("Cannot edit a published article.");
        }
    }
}
