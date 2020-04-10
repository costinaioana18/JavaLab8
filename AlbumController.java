package com.company;

import java.sql.*;

/**
 * Gives the possibility to create and insert a new song in the data base and the possibility to search a song belonging to a certain artist
 */
public class AlbumController {
    private int idCounter = 1;
    private Connection connection;

    public AlbumController(Connection connection) {
        this.connection = connection;
    }

    /**
     * The method creates and executes the query which will insert in the albums table a new song
     * The idCounter is increased everytime a new song is inserted
     *
     * @param name        the new song's name
     * @param artistId    the new song's artist's id
     * @param releaseYear the new song's release year
     */
    public void create(String name, int artistId, int releaseYear) {

        String command = "INSERT INTO albums(id, name, artist_id, release_year) VALUES ( " + idCounter + "," + name + "," + artistId + "," + releaseYear + ")";
        try {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(command);
            idCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * The method creates and executes the query which will search in the albums table the songs with a given artist_id
     * The method prints the song's id and its artist's name
     *
     * @param artistId the artist whose songs we are searching for
     */
    public void findByArtist(int artistId) {
        String command = "SELECT * FROM albums WHERE artist_id = " + artistId + ";";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(command);
            while (rs.next())
                System.out.println("Song id: " + rs.getInt(1) + ", artists name:  " + rs.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
