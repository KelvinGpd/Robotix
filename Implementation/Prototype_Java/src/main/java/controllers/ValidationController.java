package controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ValidationController {

    public int takeValidInput(int bar) {
        //for taking valid int inputs from 0-bar
        Scanner scanner = new Scanner(System.in);
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

    public boolean validateEmail(String email) {
        if (email.matches("(?i)^[A-Z0-9+_.-]+@[A-Z0-9.-]+$")) {
            return true;
        }
        return false;
    }

    public boolean validateUsername(String username) {
        if (username.matches("^[A-Za-z0-9_]+$")) {
            return true;
        }
        return false;
    }

    public boolean validatePhonenum (String phoneNum) {
        if (phoneNum.matches("^[0-9]{10}$")) {
            return true;
        }
        return false;
    }

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
