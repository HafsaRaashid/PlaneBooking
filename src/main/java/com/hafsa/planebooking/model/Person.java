package com.hafsa.planebooking.model;

    public class Person {
        private String name;
        private String surName;
        private String email;

        public Person(String name,String surName,String email){
            this.name = name;
            this.surName = surName;
            this.email = email;
        }
        public String getName()
        {
            return name;
        }
        public String getSurname()
        {
            return surName;
        }
        public String getEmail(){
            return email;
        }
        public void setName(String newName) {
            this.name = newName;
        }
        public void setSurname(String newSurname) {
            this.surName = newSurname;
        }
        public void setEmail(String newEmail) {
            this.email = newEmail;
        }

        public void printPersonInfo() {
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surName);
            System.out.println("Email: " + email);
        }
    }


