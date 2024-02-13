package tests;

import model.User;
import utils.TestUtils;

public class UserTest {
    
    public static void testUserConstructor() {
    	
        
        String test_username = "mike";
        String test_password = "my_passwrd";
        String test_first_name = "Mike";
        String test_last_name = "Smith";
        String test_mobile_number = "07771234567";
        
        boolean passed =true;
        
        User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);
        
        //intentional Defect, == changed to !=
        if (testUser.getUsername() !=(test_username))
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC1 failed: username did not match" + TestUtils.TEXT_COLOR_RESET);
        	passed=false;
        	
        if (testUser.getPassword() == (test_password))
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC2 passed" + TestUtils.TEXT_COLOR_RESET);
        else 
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC2 failed: password did not match" + TestUtils.TEXT_COLOR_RESET);
        	passed=false;
        	
        //TODO 1
        // Test for first name
        if (testUser.getFirst_name() == (test_first_name))
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC3 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC3 failed: first name did not match" + TestUtils.TEXT_COLOR_RESET);
        	passed=false;
        // Test for last name
        if (testUser.getLast_name() == (test_last_name))
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC4 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC4 failed: last name did not match" + TestUtils.TEXT_COLOR_RESET);
        	passed=false;
        // Test for mobile number
        if (testUser.getMobile_number() == (test_mobile_number))
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC5 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC5 failed: mobile number did not match" + TestUtils.TEXT_COLOR_RESET);
        	passed=false;
        	
        //TODO 4 - ''Developing the other four assertions in the UserTest class	
        assert testUser.getUsername() == test_username : "username doesnt match";
        assert testUser.getPassword() == (test_password) : "Password does not match";
        assert testUser.getFirst_name() == (test_first_name) : "First name does not match";
        assert testUser.getLast_name() == (test_last_name) : "Last name does not match";
        assert testUser.getMobile_number() == (test_mobile_number) : "Mobile number does not match";

        
        if(passed)
            System.out.println("All Java assertions in the test suite passed (none failed)");
        else
            System.out.println("Some tests failed");
        
        
      
        
        
    }
    
    public static void main(String[] args) {
        testUserConstructor();
  
    }
    
    
    
  
}
