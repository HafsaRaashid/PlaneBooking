
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {
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
    public static Ticket buy_seat(int[] A,int[] B,int[] C ,int[] D,Ticket[] tickets) {
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
                    switch (row) {
                        case "A":
                            if (A[seatNum] == 0) {
                                A[seatNum] = 1;
                            } else {
                                System.out.println("Seat not available");
                                seatUnavailable=1;
                            }
                            break;

                        case "B":
                            if (B[seatNum] == 0) {
                                B[seatNum] = 1;
                            } else {
                                System.out.println("Seat not available");
                                seatUnavailable=1;
                            }
                            break;

                        case "C":
                            if (C[seatNum] == 0) {
                                C[seatNum] = 1;
                            } else {
                                System.out.println("Seat not available");
                                seatUnavailable=1;
                            }
                            break;

                        case "D":
                            if (D[seatNum] == 0) {
                                D[seatNum] = 1;
                            } else {
                                System.out.println("Seat not available");
                                seatUnavailable=1;
                            }
                            break;
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

    public static void cancel_seat(int[] A, int[] B, int[] C , int[] D, Ticket[] tickets) {
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
                //recording seat as available if not already available
                switch (row) {
                    case "A":
                        if (A[seatNum] == 0){
                            System.out.println("Seat is already available.");
                            seatAvailable=1;
                        }
                        else{
                            A[seatNum] = 0;}
                        break;
                    case "B":
                        if (B[seatNum] == 0){
                            System.out.println("Seat is already available.");
                            seatAvailable=1;
                        }
                        else{
                            B[seatNum] = 0;}
                        break;
                    case "C":
                        if (C[seatNum] == 0){
                            System.out.println("Seat is already available.");
                            seatAvailable=1;
                        }
                        else{
                            C[seatNum] = 0;}
                        break;
                    case "D":
                        if (D[seatNum] == 0){
                            System.out.println("Seat is already available.");
                            seatAvailable=1;
                        }
                        else{
                            D[seatNum] = 0;}
                        break;
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
                        Ticket ticket=buy_seat(A,B,C,D,tickets);
                        ticket.save();
                        break;
                    case 2:
                        //cancels a seat.
                        cancel_seat(A,B,C,D,tickets);
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


