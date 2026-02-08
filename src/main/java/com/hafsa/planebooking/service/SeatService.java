package com.hafsa.planebooking.service;

public class SeatService {

    public static void findFirstAvailable(int[]... seatArrays) {
        String[] rows = {"A", "B", "C", "D"};
        int j = 0;
        // looping through array of row arrays
        for (int[] row : seatArrays) {
            // looping through seats in row array
            for (int i = 0; i < row.length; i++) {
                // finding first available seat
                if (row[i] == 0) {
                    System.out.println("the seat " + rows[j] + (i + 1) + " is available");
                    return;
                }
            }
            j++;
        }
    }

    public static void showSeatingPlan(int[]... seatArrays) {
        System.out.println("                     Seating Plan\n");
        // looping through array of row arrays.
        for (int[] row : seatArrays) {
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
