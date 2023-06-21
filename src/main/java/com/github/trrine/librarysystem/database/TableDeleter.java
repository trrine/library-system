package com.github.trrine.librarysystem.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class TableDeleter {
    public static void main(String[] args) {
        // path to sql script
        String scriptPath = "src/main/resources/drop_tables.sql";

        try (Connection conn = DatabaseManager.getConnection();
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

            System.out.println("Table deletion completed successfully.");

        } catch (Exception e) {
            System.err.println("Error deleting tables: " + e.getMessage());
        }
    }
}
