import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    public static Connection conn;
    public static Statement stat;

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost/3306/dataMahasiswa";
           // http://localhost/phpmyadmin/index.php?route=/
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();
            System.out.println("Koneksi berhasil");
        } catch (Exception e){
            System.out.println("Koneksi gagal " + e.getMessage());
        }
    }

}
