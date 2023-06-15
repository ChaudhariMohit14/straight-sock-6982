package com.masai.entities;

public class Journalist extends User{
	
	public Journalist(String username, String password, String name, String email) {
        super(username, password, name, email);
    }
	
	public boolean login(String username, String password) {
        if (getUsername().equals(username) && getPassword().equals(password)) {
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
}
