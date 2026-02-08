package com.hafsa.planebooking.app;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hafsa.planebooking.model.Ticket;
import com.hafsa.planebooking.service.BookingService;
import com.hafsa.planebooking.service.SeatService;
import com.hafsa.planebooking.service.TicketService;

public class PlaneManagement {
    //creating new scanner for input
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        //Displaying welcome message
        System.out.println("*".repeat(53));
        System.out.println("*    Welcome to the Plane Management application    *");


        //Implementing the seat management system using standard arrays.
        // 0 indicates that a seat is available,and 1 indicates that a seat has been sold.
        int[] A = new int[14];
        int[] B = new int[12];
        int[] C = new int[12];
        int[] D = new int[14];

        //Creating a ticket object.
        Ticket[] tickets = new Ticket[52];

        //Initializing variables.
        int repeat=1;

        // Displaying the following menu and asking the user to select an option.
        while (repeat==1){
            System.out.println("*".repeat(53));
            System.out.println("*                    MENU OPTIONS                   *");
            System.out.println("*".repeat(53));
            System.out.println("        1) Buy a seat");
            System.out.println("        2) Cancel a seat");
            System.out.println("        3) Find first available seat");
            System.out.println("        4) Show seating plan");
            System.out.println("        5) Print tickets information and total sales");
            System.out.println("        6) Search ticket");
            System.out.println("        0) Quit");
            System.out.println("*".repeat(53));
            System.out.print("Please select an option: ");
            //checking if option input is valid.
            //calling appropriate method for the option.
            try{
                int option = input.nextInt();
                System.out.println("*****************************************************\n");
                switch (option){
                    case 0:
                        //terminates the program
                        repeat=0;
                        System.out.println("Thank you,Session terminated");
                        break;
                    case 1:
                        //buy a seat
                        Ticket ticket=BookingService.buy_seat(A,B,C,D,tickets);
                        ticket.save();
                        break;
                    case 2:
                        //cancels a seat.
                        BookingService.cancel_seat(A,B,C,D,tickets);
                        break;
                    case 3:
                        //find first available seat.
                        SeatService.findFirstAvailable(A,B,C,D);
                        break;
                    case 4:
                        //prints seating plan.
                        SeatService.showSeatingPlan(A,B,C,D);
                        break;
                    case 5:
                        //print tickets information.
                        TicketService.printTicketsInfo(tickets);
                        break;
                    case 6:
                        //search for ticket user wants, and prints ticket information.
                        TicketService.searchTicket(tickets);
                        break;
                    default:
                        //if input is not an option in the menu.
                        System.out.println("invalid option input \n");

                }}
            //if user inputs a letter.
            catch (InputMismatchException e){
                System.out.println("invalid input");
                input.next();
            }
        }}}


