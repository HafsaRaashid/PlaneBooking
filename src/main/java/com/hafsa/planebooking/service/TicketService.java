package com.hafsa.planebooking.service;

import java.util.Scanner;
import com.hafsa.planebooking.model.Ticket;

public class TicketService {
    private static Scanner input = new Scanner(System.in);

    public static void printTicketsInfo(Ticket[] tickets) {
        // initializing variables
        int totalPrice = 0;
        int ticketCount = 0;
        // looping through tickets in the tickets array
        for (Ticket i : tickets) {
            if (i != null) {
                // printing information in ticket
                i.printTicket();
                System.out.println("------------------------------");
                totalPrice += i.getPrice();
                // counting tickets
                ticketCount++;
            }
        }
        // if no tickets bought printing a message.
        if (ticketCount == 0) {
            System.out.println("No tickets were bought");
        }
        // printing total price of bought tickets
        System.out.println("Total price= £" + totalPrice);
    }

    public static void searchTicket(Ticket[] tickets) {
        // getting users row and seat number inputs.
        System.out.print("Enter row: ");
        String row = input.next().toUpperCase();
        System.out.print("Enter seat number: ");
        int seatNum = input.nextInt();
        // checking if input is valid.
        if (seatNum > 0 && (row.equals("A") || row.equals("D") && seatNum < 14)
                || (row.equals("B") || row.equals("C") && seatNum < 12)) {
            // looping through tickets in tickets array.
            for (Ticket i : tickets) {
                // finding for ticket with same row and seat number entered by user.
                if (i != null && i.getRow().equals(row) && i.getSeat() == seatNum) {
                    // printing ticket.
                    System.out.println("------------------------------");
                    i.printTicket();
                    System.out.println("------------------------------");
                    return;
                }
            }
            // if there is no ticket bought with same value,print seat available.
            System.out.println("This seat is available");
        } else {
            System.out.println("Invalid input");
        }
    }
}
