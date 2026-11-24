/*
 * CODE ATTRIBUTIONS
 *
 * Title: Do-While Loop in Programming
 * Author: GeeksforGeeks
 * Date: 09/04/2026
 * Version: 1
 * Availability: https://www.geeksforgeeks.org/dsa/do-while-loop-in-programming/
 *
 * Title: Java Variables
 * Author: DataCamp
 * Date: 23/03/2026
 * Version: 1
 * Availability: https://www.datacamp.com/doc/java/java-variables
 *
 * Title: Scanner Class in Java
 * Author: GeeksforGeeks
 * Date: 23/04/2026
 * Version: 1
 * Availability: https://www.geeksforgeeks.org/java/scanner-class-in-java/
 *
 * Title: Java Constructors
 * Author: W3Schools
 * Date: 04/04/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/java_constructors.asp
 */

package com.mycompany.quickchatprogram;
import java.util.Scanner;

/**
 * The Registration class is responsible for collecting and validating user details during the registration process.
 * It collects the user's first name, last name, username, password, and cell phone number, validating each field using the Login class.
 * Once all fields are validated, it returns a Login object containing the registered user's details for use in the main application.
 *
 * @author Amahle Lisah Khumalo
 */

public class Registration {
    

// INSTANCE VARIABLES
    // Store the user's details collected during registration
    private String firstName;
    private String lastName;

    // CONSTRUCTOR - Constructs a Registration object.
    // The first and last names are collected here as they are needed
     
    public Registration(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // GETTER METHODS
    // Allows main class to access the names after registration

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // REGISTRATION METHOD
    // Collects and validates username, password and cell number, handles the full reg process

    public Login registerUser(Scanner scanner) {

        String username;
        String password;
        String cellPhoneNumber;

        // USERNAME LOOP
        // Keeps asking until the username contains _ and is 5 chars or less
        Login tempUser = new Login("", "", "", firstName, lastName);

        do {
            System.out.print("Enter your username (must contain _ and be 5 characters or less): ");
            username = scanner.nextLine();
            tempUser = new Login(username, "", "", firstName, lastName);

            if (!tempUser.checkUserName()) {
                // Username failed validation - display error and loop again
                System.out.println("Username is not correctly formatted; please ensure that your "
                        + "username contains an underscore and is no more than five characters in length.");
            }
        } while (!tempUser.checkUserName());

        System.out.println("Username successfully captured.\n");

        // PASSWORD LOOP
        // Keeps asking until the password meets all complexity requirements
        do {
            System.out.print("Enter your password (min 8 chars, capital, number, special character): ");
            password = scanner.nextLine();
            tempUser = new Login(username, password, "", firstName, lastName);

            if (!tempUser.checkPasswordComplexity()) {
                // Password failed complexity check - display error and loop again
                System.out.println("Password is not correctly formatted; please ensure that the password "
                        + "contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!tempUser.checkPasswordComplexity());

        System.out.println("Password successfully captured.\n");

        // CELL PHONE LOOP
        // Keeps asking until the number starts with +27 and is correctly formatted
        do {
            System.out.print("Enter your cell phone number (e.g. +27831234567): ");
            cellPhoneNumber = scanner.nextLine();
            tempUser = new Login(username, password, cellPhoneNumber, firstName, lastName);

            if (!tempUser.checkCellPhoneNumber()) {
                // Number failed regex validation - display error and loop again
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        } while (!tempUser.checkCellPhoneNumber());

        System.out.println("Cell phone number successfully added.\n");

        // Create and return the final Login object with all validated details
        // This object is passed back to PROGPART1 for use during login
        return new Login(username, password, cellPhoneNumber, firstName, lastName);
    }
} // end of class