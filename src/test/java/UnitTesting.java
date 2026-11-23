/*
 *CODE ATTRIBUTTIONS
 *
 *Title:  JUnit User Guide
 *Author: Geeksforgeeks 
 *Date: 10/04/26 
 *Version:1
 *Availability: https://docs.junit.org/6.0.3/overview.html
 *
 *Title:  Java Classes and Objects 
 *Author: W3schools
 *Date: 10/04/26
 *Version:1
 *Availability: https://www.w3schools.com/java/java_classes.asp
 *    
 *Title: Java assert keyword
 *Author: W3schools
 *Date: 06/04/26
 *Version: 1
 *Availability: https://www.w3schools.com/JAVA/ref_keyword_assert.asp
*/

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.quickchatprogram.Login;

/**
 *
 * @author Amahle Lisah Khumalo
 */
public class UnitTesting {
  
    // USERNAME TESTS 
    // Tests for the checkUserName() method.
    
    @Test
    //Username is correctly formatted.
    //checkUserName() returns true.
    public void testValidUsername() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27831234567", "Amahle", "Khumalo");
        assertTrue(user.checkUserName());
    }

    @Test
    //Username is invalid
    //checkUserName() returns false.
    public void testInvalidUsername_NoUnderscore() {
        Login user = new Login("kyle!!!!!!!", "password", "08966553", "Amahle", "Khumalo");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testInvalidUsername_TooLong() {
        Login user = new Login("kyle!!!!!!!", "password", "08966553", "Amahle", "Khumalo");
        assertFalse(user.checkUserName());
    }

    // PASSWORD TESTS 
    // Test for the checkPasswordComplexity() method.

    @Test
    public void testValidPassword() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+2783896876", "Amahle", "Khumalo");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword_NoCapital() {
        Login user = new Login("kyl_1", "password", "08966553", "Amahle", "Khumalo");
        assertFalse(user.checkPasswordComplexity());
    }    

    @Test
    public void testInvalidPassword_NoNumber() {
        Login user = new Login("kyle!!!!!!!", "password", "08966553", "Amahle", "Khumalo");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword_NoSpecialCharacter() {
        Login user = new Login("kyle!!!!!!!", "password", "08966553", "Amahle",  "Khumalo");
        assertFalse(user.checkPasswordComplexity());
    }
    
    // CELL NUMBER TESTS
    // Tests for the checkCellPhoneNumber() method to check if the cellphone number is incorrectly
    @Test
    public void testValidCellPhoneNumber() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27831234567", "Amahle",  "Khumalo");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        Login user = new Login("kyl_1", "password", "08966553", "Amahle",  "Khumalo");
        assertFalse(user.checkCellPhoneNumber());
    }
    
    // LOGIN TESTS 
    // Tests for the loginUser() method.
    @Test
    public void testSuccessfulLogin() { ///failed 
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27831234567", "Amahle",  "Khumalo");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    // Test: Login attempt fails.
    //The entered credentials do not match the stored credential
    //Expected: loginUser() returns false.
    @Test
    public void testFailedLogin() {
        Login user = new Login("kyle!!!!!!!", "password", "08966553", "Amahle",  "Khumalo");
        assertFalse(user.loginUser("ab_cd", "WrongPass"));
    }
    
    // LOGIN STATUS MESSAGE
    //Test: returnLoginStatus() returns a welcome message on successful login.
   
    @Test
    public void testLoginStatusSuccessMessage() { //// failed
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27831234567", "Amahle",  "Khumalo");
        String message = user.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertTrue(message.contains("Welcome"));
    }

    //Test: returnLoginStatus() returns an error message on failed login.
    //The entered credentials do not match so the error message
    @Test
    public void testLoginStatusFailureMessage() {
        Login user = new Login("kyle!!!!!!!", "password", "08966553", "Amahle",  "Khumalo");
        String message = user.returnLoginStatus("wrong", "wrong");
        assertEquals("Username or password is incorrect, please try again.", message);
    }
} //End of class
    
    
