package com.hafsa.planebooking.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.hafsa.planebooking.model.Person;
import com.hafsa.planebooking.model.Ticket;

public class BookingService {
    //creating new scanner for input
    private static final Scanner input = new Scanner(System.in);
    //initializing class variables
    static int numTickets=0;
    static int price;

    
    public static String row_valid( String row ){
        int rowValid=0;
        while (rowValid==0){
            System.out.print("Enter row : ");
            row = input.next().toUpperCase();
            if(row.equals("A")||row.equals("B")||row.equals("C")||row.equals("D")){
                rowValid=1;}
            else{
                System.out.println("Invalid row input please enter A/B/C/D");}
        }
        return row;
    }
    public static Ticket buy_seat(int[][] seats, Ticket[] tickets) {
        //initializing variables
        int seatValid = 0;
        String row="";
        int seatUnavailable=1;
        int seatNum = 0;


        //looping if seat entered by user is not available
        while (seatUnavailable==1){
            seatUnavailable=0;
            //looping if row entered by user is invalid (not A/B/C/D)
            row=row_valid(row);


            //looping if seat number entered by user is invalid
            while (seatValid == 0) {
                //catching errors
                try {
                    System.out.print("Enter seat number: ");
                    //saving seat numbers array index value
                    seatNum = input.nextInt() - 1;
                    seatValid=1;

                    //recording seat as sold if available
                    int rowIndex = -1;
                    switch (row) {
                        case "A": rowIndex = 0; break;
                        case "B": rowIndex = 1; break;
                        case "C": rowIndex = 2; break;
                        case "D": rowIndex = 3; break;
                    }
                    if (rowIndex >= 0) {
                        if (seats[rowIndex][seatNum] == 0) {
                            seats[rowIndex][seatNum] = 1;
                        } else {
                            System.out.println("Seat not available");
                            seatUnavailable = 1;
                        }
                    }

                    //catching error that could occur if user inputs a letter and setting seat number as invalid.
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, enter a number");
                    seatValid=0;
                    input.next();
                    //catching error that could occur if user inputs a number out of the range of seat numbers and setting seat number as invalid.
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid input, the seat number doesn't exist");
                    seatValid=0;
                }
            }
            seatValid=0;
        }
        //actual seat number (not index value)
        seatNum+=1;

        //determines seat price
        if (seatNum < 6) {
            price = 200;
        } else if (seatNum < 10) {
            price = 150;
        } else {
            price = 180;
        }

        //asking users personal details
        System.out.print("Please enter your name: ");
        String name = input.next();
        System.out.print("Please enter your surname: ");
        String surName = input.next();
        System.out.print("Please enter your email: ");
        String email = input.next();
        //creating a new person object
        Person personInfo = new Person(name, surName, email);
        //creating a new ticket object
        Ticket ticket = new Ticket(row, (seatNum), price, personInfo);

        //adding ticket to the tickets array
        tickets[numTickets] = ticket;
        //updating the number of tickets
        numTickets++;
        System.out.println("You have bought ticket "+row+seatNum+" successfully");
        //return ticket object created
        return ticket;

    }

    public static void cancel_seat(int[][] seats, Ticket[] tickets) {
        //initializing variables
        int seatValid = 0;
        int seatNum = 0;
        String row = "";
        int seatAvailable=0;

        //looping if row entered by user is invalid (not A/B/C/D).
        row=row_valid(row);
        //looping if seat number entered by user is invalid.
        while (seatValid == 0) {
            try {
                System.out.print("Enter seat number: ");
                //saving seat number index value
                seatNum = input.nextInt() - 1;
                int rowIndex = -1;
                switch (row) {
                    case "A": rowIndex = 0; break;
                    case "B": rowIndex = 1; break;
                    case "C": rowIndex = 2; break;
                    case "D": rowIndex = 3; break;
                }
                if (rowIndex >= 0) {
                    if (seats[rowIndex][seatNum] == 0) {
                        System.out.println("Seat is already available.");
                        seatAvailable = 1;
                    } else {
                        seats[rowIndex][seatNum] = 0;
                    }
                }
                seatValid = 1;
                //catching error that could occur if user inputs a letter and setting seat number as invalid.
            }catch (InputMismatchException e) {
                System.out.println("invalid input,enter a number");
                input.next();
                //catching error that could occur if user inputs a number out of the range of seat numbers and setting seat number as invalid.
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("invalid input,the seat number doesn't exist");
            }
        }
        //if seat is not already available deleting seat ticket from tickets array.
        if (seatAvailable==0){
            //looping through tickets to find the ticket that needs to be cancelled.
            for (int i=0;i<tickets.length;i++) {
                if (tickets[i].getRow().equals(row) && tickets[i].getSeat() == (seatNum+1)) {
                    //decreasing the number of tickets
                    numTickets--;
                    //removing ticket from the array
                    tickets[i] = null;
                    System.out.println("Ticket cancelled successfully.");
                    break;

                }}
        }}
    }