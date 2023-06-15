package com.masai.entities;

public class Administrator extends User{
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
}
