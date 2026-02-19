package com.hafsa.planebooking.service;

public class SeatService {

    public static void findFirstAvailable(int[][] seats) {
        String[] rows = {"A", "B", "C", "D"};
        // looping through rows
        for (int r = 0; r < seats.length; r++) {
            int[] row = seats[r];
            for (int i = 0; i < row.length; i++) {
                if (row[i] == 0) {
                    System.out.println("the seat " + rows[r] + (i + 1) + " is available");
                    return;
                }
            }
        }
    }

    public static void showSeatingPlan(int[][] seats) {
        System.out.println("                     Seating Plan\n");
        for (int[] row : seats) {
            System.out.print("               ");
            // printing available "O" and unavailable "X" seats according to seat plan format.
            for (int seat : row) {
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
}
