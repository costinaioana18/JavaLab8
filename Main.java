package com.company;

public class Main {

    public static void main(String[] args) {

        Database database = Database.getDatabase();
        database.logIn("dbase", "sql");
        database.testFunctionality();
    }
}
