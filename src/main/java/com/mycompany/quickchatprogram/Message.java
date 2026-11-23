/*
 * CODE ATTRIBUTIONS
 *
 * Title: Generating Random Numbers in Java
 * Author: GeeksforGeeks
 * Date: 16/05/2026
 * Version: 1
 * Availability: https://www.geeksforgeeks.org/generating-random-numbers-in-java/
 *
 * Title: JSON in Java
 * Author: Baeldung
 * Date: 16/05/2026
 * Version: 1
 * Availability: https://www.baeldung.com/java-json
 *
 * Title: Java StringBuilder
 * Author: GeeksforGeeks
 * Date: 17/05/2026
 * Version: 1
 * Availability: https://www.geeksforgeeks.org/stringbuilder-class-in-java/
 *
 * Title: Java Arrays
 * Author: W3Schools
 * Date: 01/06/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/java_arrays.asp
 *
 * Title: Java String Methods
 * Author: W3Schools
 * Date: 16/05/2026
 * Version: 1
 * Availability: https://www.w3schools.com/java/java_ref_string.asp
 *
 * Title: JavaScript Iterator find()
 * Author: W3Schools
 * Date: 23/06/2026
 * Version: 1
 * Availability: https://www.w3schools.com/JSREF/jsref_iterator_find.asp
 */

package com.mycompany.quickchatprogram;

/**
 * The Message class handles all messaging functionality for the QuickChat
 * application.
 *
 * This class is responsible for:
 * Generating a unique Message ID for each message and validating use input
 * Creating a Message Hash using String manipulation
 * Allowing the user to send, store, or disregard a message
 * Printing all sent messages and returning the total number of messages sent
 * Storing messages in a JSON file and managing sent, stored, disregarded, hash and ID arrays
 * Searching, deleting and reporting on stored messages
 *
 * @author Amahle Lisah Khumalo
 */

public class Message {
    // ---------------------------------------------------------------------------------------------------------
    // INSTANCE VARIABLES
    // ---------------------------------------------------------------------------------------------------------
    private String messageID;       // Randomly generated 10 digit message ID
    private String recipient;       // Recipient cell phone number
    private String messageText;     // The actual message content
    private String messageHash;     // Auto generated hash for the message
    private int messageNumber;      // The number of this message in the session

    // --------------------------------------------------------------------------------------------------------
    // CLASS-LEVEL (STATIC) VARIABLES
    // Shared across all Message objects to track the session totals
    // --------------------------------------------------------------------------------------------------------

    // Stores all sent message details as one concatenated String (Part 2)
    private static String allSentMessages = "";

    // Tracks the total number of messages sent this session
    private static int totalMessagesSent = 0;

    // --------------------------------------------------------------------------------------------------------
    // PART 3 - ARRAYS
    // Five arrays to store message data populated as the user interacts with the app
    // --------------------------------------------------------------------------------------------------------

    // Array to store all sent message texts
    private static String[] sentMessagesArray = new String[50];

    // Array to store all disregarded message texts
    private static String[] disregardedMessagesArray = new String[50];

    // Array to store all stored message texts (from JSON / store option)
    private static String[] storedMessagesArray = new String[50];

    // Array to store all message hashes generated during the session
    private static String[] messageHashesArray = new String[50];

    // Array to store all message IDs generated during the session
    private static String[] messageIDsArray = new String[50];

    // Counters to track how many items are in each array
    private static int sentCount = 0;
    private static int disregardedCount = 0;
    private static int storedCount = 0;
    private static int hashCount = 0;
    private static int idCount = 0;

    // --------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // --------------------------------------------------------------------------------------------------------

    /*
     * CODE ATTRIBUTION
     * Title: Java Constructors
     * Author: GeeksforGeeks
     * Date: 17/05/2026
     * Version: 1
     * Availability: https://www.geeksforgeeks.org/java/constructors-in-java/
     */
    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }

    // --------------------------------------------------------------------------------------------------------
    // MESSAGE ID METHODS
    // --------------------------------------------------------------------------------------------------------

    // Generates a random 10 digit number as the message ID
    private String generateMessageID() {
        Random random = new Random();
        long id = (long) (random.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }

    // Checks that the message ID is not more than 10 characters long
    // Returns true if valid, false otherwise
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Returns the message ID generated for this message
    public String getMessageID() {
        return messageID;
    }

    // Returns the recipient for this message
    public String getRecipient() {
        return recipient;
    }

    // Returns the message text for this message
    public String getMessageText() {
        return messageText;
    }

    // Returns the message hash for this message
    public String getMessageHash() {
        return messageHash;
    }

    // --------------------------------------------------------------------------------------------------------
    // RECIPIENT VALIDATION METHOD
    // --------------------------------------------------------------------------------------------------------

    // Validates the recipient cell phone number
    // Must start with +27 followed by exactly 9 digits
    // Returns appropriate success or failure message
    public String checkRecipientCell() {
        String regex = "^\\+27[0-9]{9}$";
        if (recipient.matches(regex)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                    + "an international code. Please correct the number and try again.";
        }
    }

    // --------------------------------------------------------------------------------------------------------
    // MESSAGE LENGTH VALIDATION METHODS
    // --------------------------------------------------------------------------------------------------------

    // Checks whether the message meets the 250 character limit
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message sent";
        } else {
            return "Please enter a message of less than 250 characters.";
        }
    }

    // Used specifically for unit testing the message length
    // Returns detailed message showing how many characters over the limit
    public String checkMessageLengthForTest() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over
                    + "; please reduce the size.";
        }
    }

    // --------------------------------------------------------------------------------------------------------
    // MESSAGE HASH METHOD
    // --------------------------------------------------------------------------------------------------------

    // Creates and returns the Message Hash using String manipulation    
    public String createMessageHash() {
        String firstTwoID = messageID.substring(0, 2);
        String trimmedMessage = messageText.trim();

        // GET FIRST WORD using indexOf
        int firstSpace = trimmedMessage.indexOf(" ");
        String firstWord;
        if (firstSpace == -1) {
            firstWord = trimmedMessage;
        } else {
            firstWord = trimmedMessage.substring(0, firstSpace);
        }
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");

        // GET LAST WORD using lastIndexOf
        int lastSpace = trimmedMessage.lastIndexOf(" ");
        String lastWord;
        if (lastSpace == -1) {
            lastWord = trimmedMessage;
        } else {
            lastWord = trimmedMessage.substring(lastSpace + 1);
        }
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");

        // Build hash in uppercase
        messageHash = (firstTwoID + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();

        // Add hash and ID to their respective arrays
        messageHashesArray[hashCount] = messageHash;
        hashCount++;
        messageIDsArray[idCount] = messageID;
        idCount++;

        return messageHash;
    }

    // --------------------------------------------------------------------------------------------------------
    // SEND MESSAGE METHOD
    // --------------------------------------------------------------------------------------------------------

    /*
     * CODE ATTRIBUTION
     * Title: Switch Statement in Java
     * Author: GeeksforGeeks
     * Date: 17/05/2026
     * Version: 1
     * Availability: https://www.geeksforgeeks.org/java/switch-statement-in-java/
     */

    // Handles the user's choice of what to do with the message
    // Also populates the relevant arrays based on the choice made
    public String sentMessage(int choice) {
    switch (choice) {
        case 1:
            // User chose to send - add to sent array and display string
            sentMessagesArray[sentCount] = messageText;
            sentCount++;

            allSentMessages = allSentMessages
                    + "\n--- Message " + (totalMessagesSent + 1) + " ---"
                    + "\nMessage ID: " + messageID
                    + "\nMessage Hash: " + messageHash
                    + "\nRecipient: " + recipient
                    + "\nMessage: " + messageText
                    + "\n";

            totalMessagesSent++;
            return "Message successfully sent";

        case 2:
            // User chose to disregard - add to disregarded array
            disregardedMessagesArray[disregardedCount] = messageText;
            disregardedCount++;
            return "Press 0 to delete the message";

        case 3:
            // User chose to store - add to display string and stored array
            allSentMessages = allSentMessages
                    + "\n--- Message " + (totalMessagesSent + 1) + " ---"
                    + "\nMessage ID: " + messageID
                    + "\nMessage Hash: " + messageHash
                    + "\nRecipient: " + recipient
                    + "\nMessage: " + messageText
                    + "\n";

            storedMessagesArray[storedCount] = messageText;
            storedCount++;
            storeMessage();
            totalMessagesSent++;
            return "Message successfully stored";

        default:
            return "Invalid option. Please input a valid number";
    }
}
    // --------------------------------------------------------------------------------------------------------
    // PRINT MESSAGES METHOD
    // --------------------------------------------------------------------------------------------------------

    // Returns all messages sent during the current session in order: Message ID, Message Hash, Recipient, Message
    public String printMessages() {
        if (allSentMessages.equals("")) {
            return "No messages have been sent.";
        }
        return "\n=== All Sent Messages ===" + allSentMessages;
    }

    // --------------------------------------------------------------------------------------------------------
    // TOTAL MESSAGES METHOD
    // --------------------------------------------------------------------------------------------------------

    // Returns the total number of messages sent this session
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // --------------------------------------------------------------------------------------------------------
    // STORE MESSAGE METHOD (JSON)
    // --------------------------------------------------------------------------------------------------------

    /*
     * CODE ATTRIBUTION
     * Title: Java Classes and Objects
     * Author: W3Schools
     * Date: 10/04/2026
     * Version: 1
     * Availability: https://www.w3schools.com/java/java_classes.asp
     */

    // Stores the message details in a JSON file named messages.json
    // Each message is saved as a JSONObject on its own line
    public void storeMessage() {
        JSONObject messageObject = new JSONObject();
        messageObject.put("messageID", messageID);
        messageObject.put("messageHash", messageHash);
        messageObject.put("recipient", recipient);
        messageObject.put("message", messageText);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(messageObject.toJSONString());
            file.write("\n");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // -----------------------------------------------------------------------------------------------------------
    // PART 3 - STORED MESSAGES METHODS
    // All six features for the Stored Messages sub-menu
    // -----------------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------------------
    // a) DISPLAY SENDER AND RECIPIENT OF ALL STORED MESSAGES
    // --------------------------------------------------------------------------------------------------------

    // Returns the recipient of all stored messages
    // The sender is always the logged in user
    public String displayStoredMessageRecipients(String senderName) {
        if (storedCount == 0) {
            return "No stored messages found.";
        }

        StringBuilder result = new StringBuilder();
        result.append("\n=== Stored Message Recipients ===\n");

        // Loop through stored messages array and display each recipient
        for (int i = 0; i < storedCount; i++) {
            result.append("Message ").append(i + 1).append(":\n");
            result.append("Sender: ").append(senderName).append("\n");
            result.append("Recipient: ").append(storedMessagesArray[i]).append("\n\n");
        }

        return result.toString();
    }

    // --------------------------------------------------------------------------------------------------------
    // b) DISPLAY THE LONGEST STORED MESSAGE
    // --------------------------------------------------------------------------------------------------------

    // Loops through the sent and stored messages to find and return the longest one
    // Uses string length comparison 
    public String getLongestMessage() {
        String longest = "";

        // Check sent messages array
        for (int i = 0; i < sentCount; i++) {
            if (sentMessagesArray[i] != null
                    && sentMessagesArray[i].length() > longest.length()) {
                longest = sentMessagesArray[i];
            }
        }

        // Check stored messages array
        for (int i = 0; i < storedCount; i++) {
            if (storedMessagesArray[i] != null
                    && storedMessagesArray[i].length() > longest.length()) {
                longest = storedMessagesArray[i];
            }
        }

        if (longest.equals("")) {
            return "No messages found.";
        }

        return longest;
    }

    // --------------------------------------------------------------------------------------------------------
    // c) SEARCH FOR A MESSAGE BY ID AND DISPLAY RECIPIENT AND MESSAGE
    // --------------------------------------------------------------------------------------------------------

    // Searches the messageIDsArray for a matching ID
    // Returns the corresponding recipient and message text if found
    public String searchByMessageID(String searchID, Message[] allMessages) {
        for (int i = 0; i < allMessages.length; i++) {
            if (allMessages[i] != null
                    && allMessages[i].getMessageID().equals(searchID)) {
                return "Recipient: " + allMessages[i].getRecipient()
                        + "\nMessage: " + allMessages[i].getMessageText();
            }
        }
        return "Message ID not found.";
    }

    // --------------------------------------------------------------------------------------------------------
    // d) SEARCH FOR ALL MESSAGES SENT TO A PARTICULAR RECIPIENT
    // --------------------------------------------------------------------------------------------------------

    // Searches through all messages for a matching recipient number
    // Returns all messages sent or stored for that recipient
    public String searchByRecipient(String searchRecipient, Message[] allMessages) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < allMessages.length; i++) {
            if (allMessages[i] != null
                    && allMessages[i].getRecipient().equals(searchRecipient)) {
                result.append(allMessages[i].getMessageText()).append("\n");
            }
        }

        if (result.length() == 0) {
            return "No messages found for recipient: " + searchRecipient;
        }

        return result.toString().trim();
    }

    // --------------------------------------------------------------------------------------------------------
    // e) DELETE A MESSAGE USING THE MESSAGE HASH
    // --------------------------------------------------------------------------------------------------------

    // Searches through all messages for a matching hash
    // Removes it by setting that position to null and returns a confirmation
    public String deleteByHash(String searchHash, Message[] allMessages) {
        for (int i = 0; i < allMessages.length; i++) {
            if (allMessages[i] != null
                    && allMessages[i].getMessageHash() != null
                    && allMessages[i].getMessageHash().equals(searchHash)) {

                String deletedMessage = allMessages[i].getMessageText();
                allMessages[i] = null; // Remove the message
                return "Message: \"" + deletedMessage + "\" successfully deleted.";
            }
        }
        return "Hash not found. No message deleted.";
    }

    // --------------------------------------------------------------------------------------------------------
    // f) DISPLAY FULL REPORT OF ALL STORED MESSAGES
    // --------------------------------------------------------------------------------------------------------

    // Returns a full report showing Message Hash, Recipient and Message for all messages that were sent or stored during the session
    public String displayReport(Message[] allMessages) {
        StringBuilder result = new StringBuilder();
        result.append("\n=== Full Message Report ===\n");

        boolean hasMessages = false;

        for (int i = 0; i < allMessages.length; i++) {
            if (allMessages[i] != null) {
                hasMessages = true;
                result.append("\n--- Message ").append(i + 1).append(" ---\n");
                result.append("Message Hash: ").append(allMessages[i].getMessageHash()).append("\n");
                result.append("Recipient: ").append(allMessages[i].getRecipient()).append("\n");
                result.append("Message: ").append(allMessages[i].getMessageText()).append("\n");
            }
        }

        if (!hasMessages) {
            return "No messages to display.";
        }

        return result.toString();
    }

    // --------------------------------------------------------------------------------------------------------
    // ARRAY GETTER METHODS
    // --------------------------------------------------------------------------------------------------------

    // Returns the sent messages array
    public static String[] getSentMessagesArray() {
        return sentMessagesArray;
    }

    // Returns the disregarded messages array
    public static String[] getDisregardedMessagesArray() {
        return disregardedMessagesArray;
    }

    // Returns the stored messages array
    public static String[] getStoredMessagesArray() {
        return storedMessagesArray;
    }

    // Returns the message hashes array
    public static String[] getMessageHashesArray() {
        return messageHashesArray;
    }

    // Returns the message IDs array
    public static String[] getMessageIDsArray() {
        return messageIDsArray;
    }

    // Returns the current sent count
    public static int getSentCount() {
        return sentCount;
    }

    // Returns the current stored count
    public static int getStoredCount() {
        return storedCount;
    }
} // end of class
   
