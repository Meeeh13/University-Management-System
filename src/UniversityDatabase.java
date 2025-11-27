import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UniversityDatabase {
    
    private static final String DB_URL = "jdbc:sqlite:UMSdatabase.db";

    // -------------------- Connection --------------------
    private Connection connect() throws SQLException {
          try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Database created successfully!");
                createTables(conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void createTables(Connection conn) {
        String createStudents = """
            CREATE TABLE IF NOT EXISTS students (
                student_id INTEGER PRIMARY KEY AUTOINCREMENT,
                student_name TEXT NOT NULL,
                student_email TEXT UNIQUE NOT NULL,
                birth_date TEXT,
                address TEXT,
                password TEXT NOT NULL
            );
        """;

        String createFaculty = """
            CREATE TABLE IF NOT EXISTS faculty (
                faculty_id INTEGER PRIMARY KEY AUTOINCREMENT,
                faculty_name TEXT NOT NULL,
                faculty_email TEXT UNIQUE NOT NULL,
                birth_date TEXT,
                address TEXT,
                password TEXT NOT NULL
            );
        """;

        String createAdmin = """
            CREATE TABLE IF NOT EXISTS admin (
                admin_id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                email TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createStudents);
            stmt.execute(createFaculty);
            stmt.execute(createAdmin);
            System.out.println("Tables created successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // -------------------- Register / Create --------------------
    // Register Student
    public void registerStudent(String name, String email, String birthDate, String address, String password) {
        String sql = "INSERT INTO students(student_name, student_email, birth_date, address, password) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, birthDate);
            pstmt.setString(4, address);
            pstmt.setString(5, password); // store password
            pstmt.executeUpdate();

            System.out.println("Student registered successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Register Faculty
    public void registerFaculty(String name, String email, String birthDate, String address, String password) {
        String sql = "INSERT INTO faculty(faculty_name, faculty_email, birth_date, address, password) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, birthDate);
            pstmt.setString(4, address);
            pstmt.setString(5, password); // store password
            pstmt.executeUpdate();

            System.out.println("Faculty registered successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Register Admin
    public void registerAdmin(String username, String email, String password) {
        String sql = "INSERT INTO admin(username, email, password) VALUES(?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // store password
            pstmt.executeUpdate();

            System.out.println("Admin registered successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // -------------------- Login Verification --------------------
    public boolean verifyStudent(String email, String password) {
        String sql = "SELECT password FROM students WHERE student_email = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // compare passwords
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false; // not found or wrong password
    }

    public boolean verifyFaculty(String email, String password) {
        String sql = "SELECT password FROM faculty WHERE faculty_email = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean verifyAdmin(String username, String password) {
        String sql = "SELECT password FROM admin WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
  


    // -------------------- Display / CRUD --------------------
    public void displayAllStudents() {
        String sql = "SELECT * FROM students";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("student_id"));
                System.out.println("Name: " + rs.getString("student_name"));
                System.out.println("Email: " + rs.getString("student_email"));
                System.out.println("Birth Date: " + rs.getString("birth_date"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayAllFaculty() {
        String sql = "SELECT * FROM faculty";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Faculty ID: " + rs.getInt("faculty_id"));
                System.out.println("Name: " + rs.getString("faculty_name"));
                System.out.println("Email: " + rs.getString("faculty_email"));
                System.out.println("Birth Date: " + rs.getString("birth_date"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}