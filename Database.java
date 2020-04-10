package com.company;

import java.sql.*;

/**
 * A singleton class which creates its own single instance
 * It has a login method and a functionality testing method, using the DAO classes ArtistController and AlbumController
 */
public final class Database {
    private static final Database database = new Database();
    private static Connection connection;

    private Database() {
    }

    public static Database getDatabase() {
        return database;
    }

    /**
     * The method loads the database driver
     * It creates the connection on the MusicAlbums database, with the user "dbase" and the password "sql"
     * If the parameters are not right the connection the user will be notified
     *
     * @param user     the database user
     * @param password the database password
     */
    public void logIn(String user, String password) {

        if (user.equals("dbase") && password.equals("sql")) {
            System.out.println("Connected");
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {

                connection = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:MusicAlbums", "dbase", "sql");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else
            System.out.println("Wrong user or password");

    }

    /**
     * The method creates an ArtistController and an AlbumController
     * It inserts in the database 2 artists and 2 songs using the "create" methods
     * It searches for different artists ans songs using the "findByName" and "findByArtist" methods
     */
    public void testFunctionality() {
        ArtistController artistController = new ArtistController(connection);
        AlbumController albumController = new AlbumController(connection);

        artistController.create("Rihanna", "California");
        artistController.create("Jeremih", "South Africa");
        albumController.create("Stay", 1, 2014);
        albumController.create("Drank", 2, 2009);

        artistController.findByName("Rihanna");
        albumController.findByArtist(2);


    }
}
