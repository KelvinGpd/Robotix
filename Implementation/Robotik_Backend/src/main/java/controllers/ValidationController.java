package controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * The ValidationController class provides various methods for validating user input
 * and performing checks on email addresses, usernames, phone numbers, and action types.
 */
public class ValidationController {
    private Scanner scanner;


    /**
     * Creates a new instance of the Controller class.
     * Initializes Scanner.
     */
    public ValidationController(){
        this.scanner = new Scanner(System.in);
    }


    /**
     * Takes a valid integer input from the user within the range of 0 to the specified bar value.
     *
     * @param bar The upper bound of the valid input range (inclusive).
     * @return The valid integer input entered by the user.
     */
    public int takeValidInput(int bar) {
        //for taking valid int inputs from 0-bar
        int choice;

        try{
            choice = parseInt(scanner.nextLine());

            if(choice < 0 || choice > bar) {
                System.out.println("Invalid Input. Please enter a number form 0 to " + bar);
                choice = takeValidInput(bar);
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number");
            choice = takeValidInput(bar);
        }

        return choice;
    }


    /**
     * Validates an email address.
     *
     * @param email The email address to be validated.
     * @return The validated email address if it matches the standard pattern.
     */
    public String validateEmail(String email) {
        if (email.matches("(?i)^[A-Z0-9+_.-]+@[A-Z0-9.-]+$")) {
            return email;
        }
        System.out.println("not valid, please enter anew");
        return validateEmail(scanner.nextLine());
    }

    /**
     * Validates an username.
     *
     * @param username The username to be validated.
     * @return The validated username if it matches the standard pattern.
     */
    public String validateUsername(String username) {
        if (username.matches("^[A-Za-z0-9_]+$")) {
            return username;
        }
        System.out.println("not valid, please enter anew");
        return validateUsername(scanner.nextLine());
    }


    /**
     * Validates an phone number.
     *
     * @param phoneNum The phone number to be validated.
     * @return The validated phone number if it matches the standard pattern.
     */
    public String validatePhonenum (String phoneNum) {
        if (phoneNum.matches("^[0-9]{10}$")) {
            return phoneNum;
        }
        System.out.println("not valid, please enter anew");
        return validatePhonenum(scanner.nextLine());
    }

    /**
     * Validates and prompts the user to select a valid action type from a given list of types.
     *
     * @param types The list of action types from which the user should select.
     * @return The valid action type chosen by the user.
     */
    public String validateActionType (ArrayList<String> types) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choississez un type");
        String input = scanner.nextLine();
        if (types.contains(input)) {
            return input;
        }
        System.out.println("invalide. Recommencez");
        return validateActionType(types);
    }

}
