package com.getreankeautomat;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:./" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection connect() {
        Connection con = null;
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:./getraenke.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }

    public static void createTable() {
        String url = "jdbc:sqlite:./getraenke.db";

        String sql = "CREATE TABLE IF NOT EXISTS getraenke (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	price integer,\n"
                + "	stock integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws SQLException {
        RefillStock refillStock = new RefillStock();
        StockUpdate su = new StockUpdate();
        Scanner myObj = new Scanner(System.in);


//        createNewDatabase("getraenke.db");
//        connect();
//        createTable();
//        refillStock.refill("Fanta", "2€", 10);
//        app2.stockUpdate(1);
        System.out.println("Getraenkeautomat \n\n Was moechtest du machen? \n1: Getraenk kaufen \n2: Admin Panel ");
        int userInput = myObj.nextInt();
        if (userInput == 1) {
            System.out.println("Getraenkeautomat");
            DataRetrieve dataRetrieve = new DataRetrieve();
            ResultSet rs = dataRetrieve.getResultSet();

            while (rs.next()) {
                System.out.println(rs.getString(1) +  " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            System.out.println("Gebe die ID von dem Getraenk an, welches du kaufen möchtest");
            int selectedDrink = myObj.nextInt();
            su.stockUpdate(selectedDrink);
            System.out.println("Dein Getraenk wird ausgegeben");


        } else if (userInput == 2) {

            System.out.println("Admin Panel");
            System.out.println("Gebe Name, Preis und Bestand von dem Getraenk ein:");
            String fix = myObj.nextLine();
            String getraenkeName = myObj.nextLine();
            String getraenkePrice = myObj.nextLine();
            int getraenkeStock = myObj.nextInt();
            refillStock.refill(getraenkeName, getraenkePrice, getraenkeStock);

        } else {
            System.out.println("Keine gueltige Eingabe");
        }

    }
}



