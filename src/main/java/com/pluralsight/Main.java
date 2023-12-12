package com.pluralsight;

import java.sql.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws SQLException {
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            // load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            Connection con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();

            String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock " +
                    "FROM products";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String productID = results.getString("ProductID");
                System.out.println("Product ID: " + productID);
                String productName = results.getString("ProductName");
                System.out.println("Name: " + productName);
                Double unitPrice = results.getDouble("UnitPrice");
                System.out.println("Price: " + df.format(unitPrice));
                String unitsInStock = results.getString("UnitsInStock");
                System.out.println("Stock: " + unitsInStock);
                System.out.println();

            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
