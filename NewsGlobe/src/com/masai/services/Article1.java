package com.masai.services;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale.Category;

import com.masai.entities.Journalist;

public class Article1 implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
    private String content;
    private Journalist author;
    private boolean published;
    private Category category;
    private Date publishedDate;

    public Article1(String title, String content, Journalist author, Category category, Date publishedDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.published = false;
        this.category = category;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Journalist getAuthor() {
        return author;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public Date getPublishedDate() {
        return publishedDate;
    }
}
