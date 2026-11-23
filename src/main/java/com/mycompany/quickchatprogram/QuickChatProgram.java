/*
 * CODE ATTRIBUTIONS
 *
 * Title: Java Variables
 * Author: DataCamp
 * Date: 13/05/2026
 * Version: 1
 * Availability: https://www.datacamp.com/doc/java/java-variables 
 *
 * Title: Java Tutorial
 * Author: W3Schools
 * Date: 13/05/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/default.asp
 *
 * Title: Scanner Class in Java
 * Author: GeeksforGeeks
 * Date: 13/05/2026
 * Version: 1
 * Availability: https://www.geeksforgeeks.org/java/scanner-class-in-java/
 *
 * Title: Switch Statement in Java
 * Author: GeeksforGeeks
 * Date: 17/05/2026
 * Version: 1
 * Availability: https://www.geeksforgeeks.org/java/switch-statement-in-java/
 *
 * Title: Do-While Loop
 * Author: Baeldung
 * Date: 17/05/2026
 * Version: 1
 * Availability: https://www.baeldung.com/java-do-while-loop
 *
 * Title: Java Conditions and If Statements
 * Author: W3Schools
 * Date: 17/05/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/java_conditions.asp
 *
 * Title: Java Arrays
 * Author: W3Schools
 * Date: 01/06/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/java_arrays.asp
 */

package com.mycompany.quickchatprogram;
import java.util.Scanner;

/**
 * This is the main class and entry point for the QuickChat application.
 * It handles login and messaging functionality.
 * @author Amahle Lisah Khumalo
 */

public class QuickChatProgram { // Beginning of main class

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Welcome message displayed when the application starts
        System.out.println("=== Welcome to QuickChat ===");
        System.out.println("-----------------------------\n");

        // REGISTRATION SECTION
        // Handled by the Registration class
        System.out.println("** Registration **\n");

        // Collect first and last name here before passing to Registration
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // Create a Registration object with the user's name
        Registration registration = new Registration(firstName, lastName);

        // Call registerUser() which handles username, password and cell validation
        // Returns a fully validated Login object once all fields pass
        Login newUser = registration.registerUser(scanner);

        // LOGIN SECTION
        System.out.println("----------------------------");
        System.out.println("** Login **\n");

        // loggedIn is used as a flag to control the login loop
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter your username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.nextLine();

            // returnLoginStatus() compares credentials and returns appropriate message
            System.out.println("\n" + newUser.returnLoginStatus(enteredUsername, enteredPassword));

            if (newUser.loginUser(enteredUsername, enteredPassword)) {
                // Credentials matched - set flag to true to exit the loop
                loggedIn = true;
            } else {
                System.out.print("Would you like to try again? (yes/no): ");
                String tryAgain = scanner.nextLine();

                if (!tryAgain.equalsIgnoreCase("yes")) {
                    System.out.println("Thank You for Using QuickChat!");
                    scanner.close();
                    return;
                }
            }
        }

        // MESSAGING SECTION
        // Only runs after successful login
        System.out.println("\nWelcome to QuickChat.");
        System.out.println("---------------------------\n");

        // numMessages tracks the limit set by the user when they choose option 1
        int numMessages = 0;

        // messageCount tracks how many messages have been sent or stored
        int messageCount = 0;

        // messageLimitSet tracks whether the user has already set their message limit
        boolean messageLimitSet = false;

        // allMessages array tracks every Message object created this session
        // Used by the stored messages sub-menu for searching, deleting and reporting
        Message[] allMessages = new Message[50];
        int allMessagesCount = 0;

        // MAIN MENU LOOP
        // Keeps running until the user selects Quit
        boolean running = true;

        while (running) {

            // Display the main menu
            System.out.println("\n---------------------------");
            System.out.println("** QuickChat Menu **");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages");
            System.out.println("4) Quit");
            System.out.println("-----------------------------");
            System.out.print("Choose an option: ");

            // ERROR HANDLING FOR MENU CHOICE
            int menuChoice;
            try {
                menuChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (menuChoice) {

                case 1:
                    
                    // SEND MESSAGES
                    // Ask how many messages only the FIRST time option 1 is selected
                    if (!messageLimitSet) {
                        System.out.print("How many messages would you like to send? ");
                        try {
                            numMessages = Integer.parseInt(scanner.nextLine());
                            messageLimitSet = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            break;
                        }
                    }

                    // Check if the user has reached their message limit
                    if (messageCount >= numMessages) {
                        System.out.println("You have reached your message limit of "
                                + numMessages + " messages.");
                        break;
                    }

                    // Show the user their current progress e.g. "Message 1 of 2"
                    System.out.println("\nMessage " + (messageCount + 1)
                            + " of " + numMessages);

                    // RECIPIENT LOOP
                    String recipient;
                    Message tempMessage;

                    do {
                        System.out.print("Enter recipient cell number (e.g. +27831234567): ");
                        recipient = scanner.nextLine();
                        tempMessage = new Message(recipient, "", messageCount);

                        if (!tempMessage.checkRecipientCell().equals(
                                "Cell phone number successfully captured.")) {
                            System.out.println("Cell phone number is incorrectly formatted or does not contain "
                                    + "an international code. Please correct the number and try again.");
                        }
                    } while (!tempMessage.checkRecipientCell().equals(
                            "Cell phone number successfully captured."));

                    System.out.println("Cell phone number successfully captured.");

                    // MESSAGE INPUT LOOP
                    String messageText;

                    do {
                        System.out.print("Enter your message (max 250 characters): ");
                        messageText = scanner.nextLine();
                        tempMessage = new Message(recipient, messageText, messageCount);

                        if (!tempMessage.checkMessageLength().equals("Message sent")) {
                            System.out.println("Please enter a message of less than 250 characters.");
                        }
                    } while (!tempMessage.checkMessageLength().equals("Message sent"));

                    // Create the final Message object with all valid details
                    Message newMessage = new Message(recipient, messageText, messageCount);

                    // Generate and display the Message ID
                    System.out.println("Message ID generated: " + newMessage.getMessageID());

                    // Generate and display the Message Hash
                    String hash = newMessage.createMessageHash();
                    System.out.println("Message Hash: " + hash);

                    // Send options   
                    System.out.println("\nWhat would you like to do with this message?");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    System.out.print("Choose an option: ");

                    // ERROR HANDLING FOR SEND CHOICE
                    int sendChoice;
                    try {
                        sendChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        break;
                    }

                    // sentMessage() handles all three options and populates arrays
                    System.out.println(newMessage.sentMessage(sendChoice));

                    // Add message to allMessages array for use in sub-menu
                    allMessages[allMessagesCount] = newMessage;
                    allMessagesCount++;

                    if (sendChoice == 1 || sendChoice == 3) {
                        messageCount++;
                    } else if (sendChoice == 2) {
                        int remaining = numMessages - messageCount;
                        System.out.println("You have " + remaining
                                + " message(s) remaining out of " + numMessages + ".");
                    }

                    // Check if all messages have been sent
                    if (messageCount >= numMessages && messageLimitSet) {
                        System.out.println("\nAll messages have been sent.");
                        System.out.println(newMessage.printMessages());
                        System.out.println("Total messages sent: "
                                + newMessage.returnTotalMessages());
                    }

                    break;

                case 2:
                  // Display all recently sent and stored messages
                  if (allMessagesCount == 0) {
                  System.out.println("No messages have been sent yet.");
                     } else {
                  System.out.println(allMessages[0].printMessages());
                  }
                   break;

                case 3: 
                    // STORED MESSAGES SUB-MENU
                    if (allMessagesCount == 0) {
                        System.out.println("No messages available yet. Please send some messages first.");
                        break;
                    }

                    // Use first message object to call sub-menu methods
                    Message menuMessage = allMessages[0];

                    boolean subMenuRunning = true;

                    while (subMenuRunning) {  

                        System.out.println("\n============================");
                        System.out.println("** Stored Messages Menu **");
                        System.out.println("a) Display sender and recipient of all stored messages");
                        System.out.println("b) Display the longest message");
                        System.out.println("c) Search for a message ID and display the corresponding recipient and message");
                        System.out.println("d) Search for all the messages stored for a particular recipient");
                        System.out.println("e) Delete a message using message hash");
                        System.out.println("f) Display a report that lists the full details of all the stored messages");
                        System.out.println("g) Back to main menu");
                        System.out.println("============================");
                        System.out.print("Choose an option: ");

                        String subChoice = scanner.nextLine().trim().toLowerCase();

                        switch (subChoice) {

                            case "a":
                                // Display sender and recipient of all stored messages
                                System.out.println(menuMessage.displayStoredMessageRecipients(
                                        firstName + " " + lastName));
                                break;

                            case "b":
                                // Display the longest message
                                System.out.println("\nLongest Message: "
                                        + menuMessage.getLongestMessage());
                                break;

                            case "c":
                                // Search for a message by ID
                                System.out.print("Enter Message ID to search: ");
                                String searchID = scanner.nextLine();
                                System.out.println(menuMessage.searchByMessageID(
                                        searchID, allMessages));
                                break;

                            case "d":
                                // Search for all messages for a particular recipient
                                System.out.print("Enter recipient number to search: ");
                                String searchRecipient = scanner.nextLine();
                                System.out.println(menuMessage.searchByRecipient(
                                        searchRecipient, allMessages));
                                break;

                            case "e":
                                // Delete a message using message hash
                                System.out.print("Enter message hash to delete: ");
                                String deleteHash = scanner.nextLine();
                                System.out.println(menuMessage.deleteByHash(
                                        deleteHash, allMessages));
                                break;

                            case "f":
                                // Display full report of all messages
                                System.out.println(menuMessage.displayReport(allMessages));
                                break;

                            case "g":
                                // Return to main menu
                                subMenuRunning = false;
                                break;

                            default:
                                System.out.println("Invalid option. Please choose a, b, c, d, e, f, or g.");
                                break;
                        }
                    }

                    break;

                case 4:
                    // Quit/Exit the application
                    System.out.println("Thank You for Using QuickChat!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please input a valid number.");
                    break;
            }
        }

        scanner.close(); // Close scanner 

    } 
}// End of main method
