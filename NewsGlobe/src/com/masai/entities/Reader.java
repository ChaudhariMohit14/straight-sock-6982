package com.masai.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reader extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<String> registeredUsernames = new ArrayList<>();
	
	public Reader(String username, String password, String name, String email) {
        super(username, password, name, email);
    }
	
	public static Reader signUp(String username, String password, String name, String email) {
        // Check if the username already exists
        if (checkUsernameExists(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return null;
        }

        // Create a new Reader object
        Reader reader = new Reader(username, password, name, email);
        registeredUsernames.add(username); 
        System.out.println("Reader signed up successfully.");

        return reader;
    }
	
	private static boolean checkUsernameExists(String username) {
        // Check if the username exists in the list
        return registeredUsernames.contains(username);
    }
	
	
	
	public boolean login(String username, String password) {
        if (getUsername().equals(username) && getPassword().equals(password)) {
            System.out.println("Reader logged in.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public void logout() {
        System.out.println("Reader logged out.");
    }
}
