package com.github.trrine.librarysystem.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void main(String[] args) {
        // path to sql script
        String scriptPath = "src/main/resources/init_data.sql";

        // database connection details
        String url = "jdbc:sqlite:src/main/resources/library.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {

            StringBuilder scriptContent = new StringBuilder();
            String line;

            // read each line of script
            while ((line = reader.readLine()) != null) {
                scriptContent.append(line).append(System.lineSeparator());
            }

            // split script content into individual statements
            String[] statements = scriptContent.toString().split(";");

            // execute each statement
            for (String statement : statements) {
                stmt.executeUpdate(statement);
            }

            System.out.println("Database initialization completed successfully.");

        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }
}
