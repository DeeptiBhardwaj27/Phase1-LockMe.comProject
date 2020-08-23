package com.examples.lockme.digital.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.examples.model.LoggedInUsersCredentials;
import com.examples.model.Users;


public class LockMeApplication {
	
	private static Scanner keyboard;
	private static Scanner input;  //For database.txt file
	private static Scanner lockerInput;// For lockerfile.txt file
	
	
	private static PrintWriter output; //For database.txt file
	private static PrintWriter lockerOutput;// For lockerfile.txt file
	
	private static Users users;
	private static LoggedInUsersCredentials userCredentials;
	
	public static void main(String[] args) throws IOException,FileNotFoundException{
		
		System.out.println(" *  Welcome to lockme.com  *  ");
		System.out.println("======================================");
		File databaseFile = new File("database.txt");
		File lockerFile = new File("lockerfile.txt");
		
		input= new Scanner(databaseFile);
		lockerInput = new Scanner(lockerFile);
		keyboard = new Scanner(System.in);
		output = new PrintWriter(new FileWriter(databaseFile , true));
		lockerOutput = new PrintWriter(new FileWriter(lockerFile , true));
		
		users = new Users();
		userCredentials = new LoggedInUsersCredentials();
	
		userSignInOptions();
		
		}
	 public static void userSignInOptions ()
	 
	{  
		 System.out.println("Select option 1 for new user registration");
		 System.out.println("Select option 2 to login");
		 
		 System.out.println("1. Registration");
		 System.out.println("2. Login");
		 
		 int choice =keyboard.nextInt();
		 switch(choice)
		 {
		 case 1:
			 registerUser();
		     break;
		     
		  case 2:
			  login();
		       break;
		       
		  default: System.out.println("Select valid option");
		         break;
		 }
		 keyboard.close();
		 
		 input.close();
	 }
	
	 
	 public static void userLockerOptions( String inputUsername)
	 {   
		   
		 System.out.println(" * Please use lockerFile for your credentilas * ");
		 System.out.println("========================================================");
		 System.out.println("Select option 1 for storing  new user credentials");
		 System.out.println("Select option 2 for retrieving user credentials");
		 
		 System.out.println("========================================================");
		 System.out.println("1. Store User Credentials");
		 System.out.println("2. Retrieve Stored Credentials");
		
		 int choice = keyboard.nextInt();
		 
		 switch (choice) {
		  case 1: 
			  storeUserCredentials(inputUsername);
			  break;
			  
		  case 2: 
		       retrieveUserCredentials(inputUsername);
		       break;
		 
		default: System.out.println("Select valid option");
			break;
		}
		 lockerInput.close();
		 
	 }
	 
	

	 public static void registerUser ()
	 {
		 File databaseFile = new File("database.txt");
		 
		 System.out.println(" * Welcome to Registration Page * ");
		 System.out.println("========================================= ");
		try {
		 System.out.println("Enter UserName: ");
		 String username = keyboard.next();
		 
		 //Check if username is already present in the database.txt file
		 Scanner usernameCheck = new Scanner(databaseFile);
         while(usernameCheck.hasNext()){
         String existingUsername = usernameCheck.next();
         
       if(username.equalsIgnoreCase(existingUsername)){
     	
         System.out.println("Username already exists");
         System.out.println("Please enter a username: ");
         username = keyboard.next();
         users.setUsername(existingUsername);
     }
       }}catch(FileNotFoundException e)
		{
    	   System.out.println(e.getMessage());
		}
		 System.out.println("Enter Password: ");
		 String password = keyboard.next();
		 users.setPassword(password);
		 output.println(users.getUsername());
		 output.println(users.getPassword());
		 System.out.println("User is registered successfully");
		 output.close();
	 }
	 
	 public static void login()
	 {
		 System.out.println(" * Welcome to Login Page * ");
		 System.out.println("========================================= ");
		 System.out.println("Enter UserName: ");
		 String inputUser = keyboard.next();
		 boolean flag =false;
		 while(input.hasNext() && !flag)
		 {
			 if(input.next().equals(inputUser))
			 {
				 System.out.println("Enter password: ");
				 String inputPassword = keyboard.next();
				 
				 if(input.next().equals(inputPassword))
				 {
					 System.out.println("Login Successful");
					 flag=true;
					 userLockerOptions(inputUser);
					 break;
				 }
			 }
		 }
		 if(!flag)
		 {
			 System.out.println("User not found .... Login Failure");
		 }
	 }
	 
	 public static void storeUserCredentials(String loggedInUser)
	 {
		 System.out.println(" * Store User Credentials * ");
		 System.out.println("======================================");
		 userCredentials.setLoggedInUser(loggedInUser);
		 System.out.println("Enter website name : ");
		 String siteName = keyboard.next();
		 userCredentials.setSiteName(siteName);
		 System.out.println("Enter user name : ");
		 String username = keyboard.next();
		 userCredentials.setUsername(username);
		 System.out.println("Enter password : ");
		 String password = keyboard.next();
		 userCredentials.setPassword(password);
		 
		 lockerOutput.println(userCredentials.getLoggedInUser());
		 lockerOutput.println(userCredentials.getSiteName());
		 lockerOutput.println(userCredentials.getUsername());
		 lockerOutput.println(userCredentials.getPassword());
		 
		 System.out.println("Credentials are stored");
		 lockerOutput.close();
		 
	 }
	 
	 public static void retrieveUserCredentials(String inputUsername)
	 {
		 System.out.println(" * Reterieve User Credentials * ");
		 System.out.println("======================================");
		 System.out.println(inputUsername);
		 
		 while(lockerInput.hasNext())
		 {
			 if(lockerInput.next().equals(inputUsername))
			 {
				 System.out.println("WebSite Name: "+ lockerInput.next());
				 System.out.println("UserName: "+ lockerInput.next());
				 System.out.println("Password: "+lockerInput.next());
			 }
		 }
	 }
	 
	 

	 }

