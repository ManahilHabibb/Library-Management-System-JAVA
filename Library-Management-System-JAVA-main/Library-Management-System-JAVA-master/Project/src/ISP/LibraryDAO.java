package LMS;

import java.io.IOException;
import java.sql.*;

public class LibraryDAO {
    private DatabaseOperations databaseOperations;

    // Fix: Default constructor to prevent errors in existing code
    public LibraryDAO() {
        this.databaseOperations = new SQLDatabaseOperations(); // Default to SQL
    }

    // Parameterized constructor for flexibility
    public LibraryDAO(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    public Connection getConnection() {
        return databaseOperations.getConnection();
    }

    public void populateLibrary(Library library) throws SQLException, IOException {
        databaseOperations.loadLibraryData(library);
    }

    public void persistLibrary(Library library) throws SQLException {
        databaseOperations.saveLibraryData(library);
    }
}

// Abstract Interface for Database Operations
interface DatabaseOperations {
    Connection getConnection();
    void loadLibraryData(Library library) throws SQLException, IOException;
    void saveLibraryData(Library library) throws SQLException;
}

// Concrete Implementation for SQL Database
class SQLDatabaseOperations implements DatabaseOperations {
    private final String host = "jdbc:derby://localhost:1527/LMS";
    private final String user = "haris";
    private final String password = "123";

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            System.out.println("Database connection error: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void loadLibraryData(Library library) throws SQLException, IOException {
        Connection con = getConnection();
        if (con == null) return;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK");
        while (rs.next()) {
            String title = rs.getString("TITLE");
            String author = rs.getString("AUTHOR");
            String subject = rs.getString("SUBJECT");
            int id = rs.getInt("ID");
            boolean issued = rs.getBoolean("IS_ISSUED");
            Book b = new Book(id, title, subject, author, issued);
            library.addBook(b);
        }
        con.close();
    }

    @Override
    public void saveLibraryData(Library library) throws SQLException {
        Connection con = getConnection();
        if (con == null) return;

        String sql = "INSERT INTO BOOK (ID, TITLE, AUTHOR, SUBJECT, IS_ISSUED) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        for (Book b : library.getBooks()) {
            pstmt.setInt(1, b.getID());
            pstmt.setString(2, b.getTitle());
            pstmt.setString(3, b.getAuthor());
            pstmt.setString(4, b.getSubject());
            pstmt.setBoolean(5, b.isIssued());  
            pstmt.executeUpdate();
        }
        con.close();
    }
}
