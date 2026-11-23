/*
 * CODE ATTRIBUTIONS
 *
 * Title: JavaScript - Username Validation using Regex
 * Author: GeeksforGeeks
 * Date: 28/03/2026 
 * Version: 1
 * Availability: https://www.geeksforgeeks.org/javascript/username-validation-in-js-regex/
 *
 * Title: Java Tutorial 
 * Author: W3Schools
 * Date: 28/03/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/default.asp
 *
 * Title: Java Constructors
 * Author: W3Schools
 * Date: 04/04/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/java_constructors.asp
 */

package com.mycompany.quickchatprogram;

/**
 * The login class is responsible for storing user information and handling all validation and authentication login.
 * This class provides methods to validate a username, password and cellphone number during registration and return appropriate status messages for login attempts. 
 * 
 * @author Amahle Lisah Khumalo
 */
public class Login {
     
    //Declare variables to store user information
    String username; // User's chosen username
    String cellphoneNumber; //SA cellphone number
    String password; //User's chosen password
    String firstName; //User's first name
    String lastName; //User's last name
 
    //------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------------------------------------------------------------
    
    // Constructs a login object and assigns the details provided by the user
    public Login(String username, String password, String cellphoneNumber, String firstName, String lastName) {
        
    
        this.username = username; 
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    } 
     
    /* 
    *CODE ATTRIBUTION
    *Title: Java Methods
    *Author: Geeksforgeeks
    *Date: 06/04/26
    *Version:1
    *Availability: https://www.geeksforgeeks.org/java/methods-in-java/
    */
    
    // VALIDATION METHODS
    // Validates the username requirements 
    // Returns true if valid, false otherwise
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    } 
    
    //Checks that the password meets all complexity requirements 
    //Return true if valid, false otherwise 
    public boolean checkPasswordComplexity() {
        
        //Immediately returns false if the password is shorter than 8 characters
        if (password.length() < 8) 
        {
            return false;
        }
        
        //Boolean flags to track which complexity requirements have been met
        boolean hasCapital = false; 
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        //Loop through each character in the password and check its type
        for (char c : password.toCharArray()) {
            
        /*  
         *CODE ATTRIBUTION
         *Title: Java Conditions and If Statements
         *Author: W3schools
         *Date:11/04/2
         *Version:1
         *Availability: https://www.w3schools.com/java/java_conditions.asp
        */
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }else if (Character.isDigit(c)) {
                hasNumber = true;
            }else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
        }
    }
        
        //All three flags must be tru for the password to pass
        return hasCapital && hasNumber && hasSpecial; 
    } 
     
     
    /* CODE ATTRIBUTTION
    *TITLE: Regex Pattern
    *AUTHOR: Baeldung
    *DATE: 10/04/26
    *VERSION:1
    *AVAILABILITY: https://www.baeldung.com/java-regex-validate-phone-numbers
    */
    // Checks that cell phone number is correctly formatted.
    public boolean checkCellPhoneNumber() {
        String regex = "^\\+27[0-9]{9}$";
            return cellphoneNumber.matches(regex);
    }
    
    //-------------------------------------------------------------------------------------------------------
    //REGISTRATION METHOD 
    //-------------------------------------------------------------------------------------------------------
    
    //Registers the user and returns the appropriate status message
    public String registerUser() {
        //String builder is used to build the result line by line
        StringBuilder result = new StringBuilder();
        
        //Checks username and append the appropriate message
         if (checkUserName()) {
            result.append("Username successfully captured.\n");
        } else {
            result.append("Username is not correctly formatted, please ensure that your "
                    + "username contains an underscore and is no more than five characters in length.\n");
        }

         //Check passoword and append the appropriate message
        if (checkPasswordComplexity()) {
            result.append("Password successfully captured.\n");
        } else {
            result.append("Password is not correctly formatted, please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number, and a special character.\n");
        }

        //Check cellphone number and append the appropriate message
        if (checkCellPhoneNumber()) {
            result.append("Cell phone number successfully added.\n");
        } else {
            result.append("Cell phone number incorrectly formatted or does not contain international code.\n");
        }

        //Returns the full registration ststus
        return result.toString(); 
    }
    
    //------------------------------------------------------------------------------------------------------
    //LOGIN METHODS
    //------------------------------------------------------------------------------------------------------
    
    //loginUser Method that checks if enetered credentials match stored credentials 
    //Returns true if they match, false otherwise
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }
    
    //returnLoginStatus method that returns the appropriate login message if login was successful
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome "  + firstName + " " + lastName + ", it is great to see you.";
                    } else { 
            return "Username or password is incorrect, please try again.";
        }
    }
} //End of Class   
    
