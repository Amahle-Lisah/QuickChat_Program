## AUTHOR
Amahle Lisah Khumalo

## PROJECT TITLE: QuickChat

QuickChat is a console-based messaging application written in Java. A user registers an account, logs in, and then composes, sends, stores or discards messages. Stored messages can be searched, reported on and deleted from a built-in sub-menu.

## FEATURES

Registration and login
- Collects the user's first name, last name, username, password and cell phone number.
- Every field is validated before the account is created:
  - Username: must contain an underscore and be no longer than five characters.
  - Password: must be at least eight characters, with a capital letter, a number and a special character.
  - Cell phone number: must start with the international country code. 
- The user then logs in with their new credentials, with the option to retry if they get them wrong.

Messaging
- The user chooses how many messages to send in the session.
- Each message is checked for a valid recipient number ( +27 followed by 9 digits) and a maximum length of 250 characters.
- Every message gets a randomly generated 10-digit Message ID and a Message Hash (first two digits of the ID, the message number, and the first and last word of the message, in capitals).
- For each message the user can **send** it, **disregard** it, or **store** it to send later. Stored messages are saved to `messages.json`.
- Recently sent messages can be displayed, together with the total number sent.

Stored Messages menu
- Display the sender and recipient of all stored messages.
- Display the longest message.
- Search for a message by Message ID.
- Search for all messages sent to a particular recipient.
- Delete a message using its Message Hash.
- Display a full report of all messages.

## PROJECT STRUCTURE

QuickChat Progarm: Main class. Runs the registration, login and menus.
Registration: Collects and validates the user's details during sign-up.
Login: Holds the user's details, validates them and checks login attempts. 
Message: Creates, validates, sends, stores, searches and reports on messages. 
Unit Testing: JUnit tests for the registration and login validation. 

## TECHNOLOGIES USED
- Netbeans (Maven)
- Java 25
- [json-simple](https://code.google.com/archive/p/json-simple/) 1.1.1 for saving stored messages as JSON
- JUnit 5 for unit testing

## How to run
1. Open the project in NetBeans (or any Maven-compatible IDE).
2. Run "Clean and Build" so Maven can download the dependencies.
3. Run "QuickChat Program" and follow the prompts in the console.

