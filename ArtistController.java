package com.company;

import java.sql.*;

/**
 * Gives the possibility to create and insert a new artist in the data base and the possibility to search an artist having a certain name
 */
public class ArtistController {
    private int idCounter = 1;
    private Connection connection;

    public ArtistController(Connection connection) {
        this.connection = connection;
    }

    /**
     * The method creates and executes the query which will insert in the artists able a new artist
     * The idCounter is increased everytime a new artist is inserted
     *
     * @param name    the name of the new artist
     * @param country the new artist's whereabouts
     */
    public void create(String name, String country) {

        String command = "INSERT INTO artists(id, name, country) VALUES ( " + idCounter + "," + name + "," + country + ")";
        try {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(command);
            idCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * The method creates and executes the query which will search in the artists table the artist with a given name
     * The method will print his id and his origin country.
     *
     * @param name the name of the artist we are looking for
     */
    public void findByName(String name) {

        String command = "SELECT * FROM artists WHERE name LIKE = " + " \" " + name + " \" " + ";";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(command);
            while (rs.next())
                System.out.println("Artist's id: " + rs.getInt(1) + ", artist's country:  " + rs.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
