package com.hafsa.planebooking.model;

    import java.io.FileWriter;

    public class Ticket {
        private String row;
        private int seat;
        private int price;
        private Person person;

        public Ticket(String row,int seat,int price,Person person){
            this.row=row;
            this.seat=seat;
            this.price=price;
            this.person=person;
        }

        public String getRow(){
            return row;
        }
        public int getSeat(){
            return seat;
        }
        public int getPrice(){
            return price;
        }

        public Person getPerson() {
            return person;
        }

        public void setRow(String row) {
            this.row = row;
        }
        public void setSeat(int seat) {
            this.seat = seat;
        }
        public void setPrice(int price) {
            this.price = price;
        }
        public void setPerson(Person person) {
            this.person=person;
        }
        public void printTicket(){
            System.out.println("  Ticket Information  ");
            System.out.println("row: "+row);
            System.out.println("seat: "+seat);
            System.out.println("price: £"+price+"\n");
            System.out.println("  Persons Information  ");
            person.printPersonInfo();


        }
        public void save(){
            try {
                //write ticket details in a file
                FileWriter file=new FileWriter(row+seat+".txt");
                file.write("  Ticket Information  \n");
                file.write("row: " + row + "\n");
                file.write("seat: " + seat + "\n");
                file.write("price: £" + price + "\n");
                file.write("  Person's Information  \n");
                file.write("Name: " + person.getName() + "\n");
                file.write("Surname: " + person.getSurname() + "\n");
                file.write("Email: " + person.getEmail() + "\n");
                file.close();
                //catch errors.
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }





