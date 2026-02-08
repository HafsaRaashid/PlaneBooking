package com.hafsa.planebooking.app;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hafsa.planebooking.model.Ticket;
import com.hafsa.planebooking.service.BookingService;

public class PlaneManagement {
    //creating new scanner for input
    private static final Scanner input = new Scanner(System.in);
    

    public static void find_first_available(int[]...seatArrays){
        String[] rows={"A","B","C","D"};
        int j=0;
        //looping through array of row arrays
        for(int[] row :seatArrays) {
            //looping through seats in row array
            for (int i = 0; i < row.length; i++) {
                //finding first available seat
                if (row[i] == 0) {
                    System.out.println("the seat " + rows[j] + (i + 1) + " is available");
                    return;
                }
            }
            j++;
        }}

    public static void show_seating_plan(int[]...seatArrays){
        System.out.println("                     Seating Plan\n");
        //looping through array of row arrays.
        for (int[]row:seatArrays){
            System.out.print("               ");
            //printing available "0" and unavailable "X" seats according to seat plan format.
            for (int seat : row){
                if (seat == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }

            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public static void print_tickets_info(Ticket[] tickets){
        //initializing variables
        int totalPrice=0;
        int ticketCount=0;
        //looping through tickets in the tickets array
        for (Ticket i:tickets){
            if (i!=null){
                //printing information in ticket
                i.printTicket();
                System.out.println("------------------------------");
                totalPrice+= i.getPrice();
                //counting tickets
                ticketCount++;
            }
        }
        //if no tickets bought printing a message.
        if (ticketCount==0){
            System.out.println("No tickets were bought");
        }
        //printing total price of bought tickets
        System.out.println("Total price= £"+totalPrice);}

    public static void search_ticket(Ticket[] tickets){
        //getting users row and seat number inputs.
        System.out.print("Enter row: ");
        String row=input.next().toUpperCase();
        System.out.print("Enter seat number: ");
        int seatNum=input.nextInt();
        //checking if input is valid.
        if(seatNum>0 && (row.equals("A")||row.equals("D")&& seatNum<14)||(row.equals("B")||row.equals("C")&& seatNum<12)){
            //looping through tickets in tickets array.
            for (Ticket i:tickets){
                //finding for ticket with same row and seat number entered by user.
                if (i!=null && i.getRow().equals(row)&& i.getSeat() ==seatNum){
                    //printing ticket.
                    System.out.println("------------------------------");
                    i.printTicket();
                    System.out.println("------------------------------");
                    return;
                }
            }
            //if there is no ticket bought with same value,print seat available.
            System.out.println("This seat is available");}
        else {
            System.out.println("Invalid input");

        }}

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
                        find_first_available(A,B,C,D);
                        break;
                    case 4:
                        //prints seating plan.
                        show_seating_plan(A,B,C,D);
                        break;
                    case 5:
                        //print tickets information.
                        print_tickets_info(tickets);
                        break;
                    case 6:
                        //search for ticket user wants, and prints ticket information.
                        search_ticket(tickets);
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


