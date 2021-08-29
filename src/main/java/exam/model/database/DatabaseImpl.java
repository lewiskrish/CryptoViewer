package exam.model.database;

import java.sql.*;

/**
 * Default implementation of the Database interface.
 */
public class DatabaseImpl implements Database {

    // Whole class adapted from SOFT2412 Assignment 2

    private final String filepath;

    /**
     * Default DatabaseImpl constructor.
     *
     * @param filepath the filepath to the database
     */
    public DatabaseImpl(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void init() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + filepath);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Metadata (id INTEGER PRIMARY KEY, data STRING NOT NULL)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addCryptoMetadata(int id, String jsonData) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + filepath);
            String sql = "INSERT OR REPLACE INTO Metadata (id, data) values(?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, jsonData);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCryptoMetadata(int id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + filepath);
            String sql = "SELECT * FROM Metadata WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("data");
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
