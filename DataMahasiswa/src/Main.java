
import java.sql.*;
import java.util.Scanner;
import java.lang.String;


public class Main {
    //konfig data base
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3307/datamahasiswa";
    static final String user = "root";
    static final String password = "";

    static Connection conn;
    static Statement stat;
    static ResultSet rs;

    //boolean untuk lanjutkan (y/n)
    static boolean lanjutkan = true;

    //-- awal tampil menu --//
    static void TampilMenu(){
        Scanner input = new Scanner(System.in);

        while (lanjutkan) {
            System.out.println("=== MENU ===");
            System.out.println("1.TAMPILKAN DATA");
            System.out.println("2.TAMBAH DATA");
            System.out.println("3.DELETE DATA");
            System.out.println("4.PERBARUI DATA");
            System.out.println("5.CARI DATA");
            System.out.println("6.EXIT");
            System.out.println("================");
            System.out.print("Pilih 1-6 : ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    System.out.println("\n\n=== MENU TAMPIL DATA MAHASISWA ===");
                    TampilData();
                    break;

                case 2:
                    System.out.println("\n\n=== MENU TAMBAH DATA MAHASISWA ===");
                    TambahData();
                    break;

                case 3:
                    System.out.println("\n\n=== MENU HAPUS DATA MAHASISWA ===");
                    HapusData();
                    break;

                case 4:
                    System.out.println("\n\n=== MENU PERBARUI DATA MAHASISWA ===");
                    PerbaruiData();
                    break;

                case 5:
                    System.out.println("\n\n=== MENU CARI DATA MAHASISWA ===");
                    CariData();
                    break;

                case 6:
                    System.err.println("EXIT");
                    System.exit(0);
                default:
                    System.out.println("Menu yang anda pilih salah, Silahkan input antara 1-4.");
            }
            lanjutkan = ulang("Apakah anda ingin kembali ke menu awal?");
        }
    }

    //-- akhir tampil menu --//

    //-- awal tampil data --//
    static void TampilData(){

        Scanner input = new Scanner(System.in);
        String sql = "SELECT * FROM informasiMahasiswa";
        String sql2 = "SELECT * FROM informasimahasiswa ORDER BY nama ASC";


        try {
            rs = stat.executeQuery(sql);
            rs = stat.executeQuery(sql2);



            System.out.println("\n|\t\t\tNIM\t\t\t\t|\t\t\t\tNAMA\t\t\t\t|\t\t\tJURUSAN\t\t\t\t|\t\tANGKATAN\t\t|\t\tJK\t\t|\t\t\t\tEMAIL\t\t\t\t|");
            System.out.println("=================================================================================================================================================================================");


            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String jurusan = rs.getString("jurusan");
                String angkatan = rs.getString("angkatan");
                String jk = rs.getString("jenis_kelamin");
                String email = rs.getString("email");


               System.out.println(String.format("|%s\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t\t|%s\t\t\t|%s\t\t\t\t|", nim, nama, jurusan, angkatan, jk, email));
            }
            System.out.println("\n");
            jmlSeluruhData();
            jmlDataPria();
            jmlDataWanita();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- akhir tampil data --//

    //-- awal tambah data --//
    static void TambahData(){

        Scanner input = new Scanner(System.in);

            try {
                System.out.print("\nnim : ");
                String nim = input.next();
                String nama = input.nextLine();
                System.out.print("\nnama : ");
                nama = input.nextLine();
                System.out.print("\njurusan : ");
                String jurusan = input.nextLine();
                System.out.print("\nangkatan : ");
                String angkatan = input.next();
                System.out.print("\njenis kelamin : ");
                String jk = input.next();
                System.out.print("\nemail : ");
                String email = input.next();

                stat.executeUpdate("INSERT INTO `informasimahasiswa` (`nim`, `nama`, `jurusan`, `angkatan`, `jenis_kelamin`, `email`) VALUES ('" + nim + "', '" + nama + "', '" + jurusan + "', '" + angkatan + "', '" + jk + "', '" + email + "')");
                stat.executeUpdate( "UPDATE informasimahasiswa SET nama = UPPER (nama)");
                stat.executeUpdate( "UPDATE informasimahasiswa SET jurusan = UPPER (jurusan)");
                stat.executeUpdate( "UPDATE informasimahasiswa SET jenis_kelamin = UPPER (jenis_kelamin)");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    //-- akhir tambah data--//

    //-- awal hapus data --//
    static  void HapusData(){

        Scanner input = new Scanner(System.in);

        try {

            System.out.println("Masukan NIM dan NAMA yang akan di HAPUS");
            System.out.print("\nNIM : ");
            String nim = input.nextLine();


            stat.executeUpdate( "DELETE FROM `informasimahasiswa` WHERE `informasimahasiswa`.`nim` = '"+nim+"' ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //-- akhir hapus data --//

    //-- awal update data --//
    static void PerbaruiData(){

        Scanner input = new Scanner(System.in);

        while (lanjutkan) {
            System.out.println("PILIH DATA YANG AKAN DI PERBARUI");
            System.out.println("1.NAMA");
            System.out.println("2.JURUSAN");
            System.out.println("3.JENIS KELAMIN");
            System.out.println("4.ANGKATAN");
            System.out.println("5.EMAIL");
            System.out.println("6.EXIT");
            System.out.print("\nPILIH : ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    PerbaruiNama();
                    break;

                case 2:
                    PerbaruiJurusan();
                    break;

                case 3:
                    PerbaruiGender();
                    break;

                case 4:
                    PerbaruiAngkatan();
                    break;

                case 5:
                    PerbaruiEmail();
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.err.println("Kode tidak ada, pilih 1-5");
            }

            lanjutkan = ulang("Apakah anda ingin perbarui data lagi?");
        }

    }

    //-- akhir update data --//

    //-- awal bagian untuk menampilkan jml seluruh mahasiswa
    static void jmlSeluruhData(){
        String sql1 = "SELECT count(jenis_kelamin) FROM informasimahasiswa";

        try{
            rs = stat.executeQuery(sql1);
            while (rs.next()) {

                String jenis_kelamin = rs.getString("count(jenis_kelamin)");


                System.out.println(String.format("Jumlah seluruh data : %s",jenis_kelamin));

                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- jumlah seluruh mahasiswa berdasarkan gender pria
    static void jmlDataPria(){
      String sql = "SELECT count(jenis_kelamin) FROM informasimahasiswa WHERE jenis_kelamin = 'pria'";
        try{
            rs = stat.executeQuery(sql);
            while (rs.next()) {

                String jenis_kelamin = rs.getString("count(jenis_kelamin)");

                //tampilkan
                System.out.println(String.format("Jumlah pria : %s",jenis_kelamin));

                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- jumlah seluruh mahasiswa berdasarkan gender wanita
    static void jmlDataWanita(){
        String sql = "SELECT count(jenis_kelamin) FROM informasimahasiswa WHERE jenis_kelamin = 'wanita'";
        try{
            rs = stat.executeQuery(sql);
            while (rs.next()) {

                String jenis_kelamin = rs.getString("count(jenis_kelamin)");

                //tampilkan
                System.out.println(String.format("Jumlah wanita : %s",jenis_kelamin));

                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //-- akhir tampil jml mahasiswa

    //-- awal update jurusan --//
    static void PerbaruiJurusan(){
        Scanner input = new Scanner(System.in);

        String jurusan_baru = "";
        String nim_primary = "";
        try{
            TampilData();

            System.out.println("UBAH / PERBARUI JURUSAN MAHASISWA");
            System.out.println("INPUT NIM YANG AKAN DI PERBARUI JURUSAN-NYA.");
            System.out.print("\nNIM : ");
            nim_primary = input.nextLine();
            System.out.print("\nInput JURUSAN baru : ");
            jurusan_baru = input.nextLine();

            String.format(" ");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.jurusan = '"+jurusan_baru+"' WHERE informasimahasiswa.nim = "+nim_primary);


            System.out.println("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- akhir update jurusan

    //-- awal update nama --//
    static void PerbaruiNama(){
        Scanner input = new Scanner(System.in);


        String nama_baru = "";
        String nim_primary = "";
        try{
            TampilData();

            System.out.println("UBAH / PERBARUI NAMA MAHASISWA");
            System.out.println("INPUT NIM YANG AKAN DI PERBARUI NAMA-NYA.");
            System.out.print("\nNIM : ");
            nim_primary = input.nextLine();
            System.out.print("\nInput NAMA baru : ");
            nama_baru = input.nextLine();

            String.format(" ");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.nama = '"+nama_baru+"' WHERE informasimahasiswa.nim = "+nim_primary);


            System.out.println("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- akhir update nama --//

    //-- awal update gender --//
    static void PerbaruiGender(){
        Scanner input = new Scanner(System.in);


        String gender_baru = "";
        String nim_primary = "";
        try{
            TampilData();

            System.out.println("UBAH / PERBARUI GENDER MAHASISWA");
            System.out.println("INPUT NIM YANG AKAN DI PERBARUI GENDER-NYA.");
            System.out.print("NIM : ");
            nim_primary = input.nextLine();
            System.out.print("\nINPUT PRIA / WANITA : ");
            gender_baru = input.nextLine();

            String.format(" ");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.jenis_kelamin = '"+gender_baru+"' WHERE informasimahasiswa.nim = "+nim_primary);


            System.out.println("");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //-- akhir update gender --//

    //-- awal update tahun angkatan --//
    static void PerbaruiAngkatan(){
        Scanner input = new Scanner(System.in);


        String angkatan_baru = "";
        String nim_primary = "";
        try{
            TampilData();

            System.out.println("UBAH / PERBARUI TAHUN ANGKATAN MAHASISWA");
            System.out.println("INPUT NIM YANG AKAN DI PERBARUI THN/ANGKATAN-NYA.");
            System.out.print("\nNIM : ");
            nim_primary = input.nextLine();
            System.out.print("\nInput Tahun Angkatan baru : ");
            angkatan_baru = input.nextLine();

            String.format(" ");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.angkatan = '"+angkatan_baru+"' WHERE informasimahasiswa.nim = "+nim_primary);


            System.out.println("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- akhir update tahun angkatan --//

    //-- awal update email --//
    static void PerbaruiEmail(){
        Scanner input = new Scanner(System.in);


        String email_baru = "";
        String nim_primary = "";
        try{
            TampilData();

            System.out.println("UBAH / PERBARUI EMAIL MAHASISWA");
            System.out.println("INPUT NIM YANG AKAN DI PERBARUI EMAIL-NYA.");
            System.out.print("\nNIM : ");
            nim_primary = input.nextLine();
            System.out.print("\nINPUT EMAIL BARU : ");
            email_baru = input.nextLine();

            String.format(" ");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.email = '"+email_baru+"' WHERE informasimahasiswa.nim = "+nim_primary);


            System.out.println("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- akhir update email --//

    //-- awal searching data --//
    static void CariData(){

        Scanner input = new Scanner(System.in);

        String key = "";

        while (lanjutkan) {
            System.out.println("Masukan kata kunci. contoh nim : 19..... atau huruf nama depan atau jurusan co : teknik");
            System.out.print("\nMasukan kata kunci : ");
            key = input.nextLine();
            try {
                String sql = ("SELECT * from informasimahasiswa " + " WHERE nim LIKE ('%" + key + "%')" + "OR nama LIKE ('%" + key + "%')" + " OR jurusan LIKE ('%" + key + "%')  ");
                rs = stat.executeQuery(sql);

                while (rs.next()) {
                    System.out.println("\n");
                    System.out.print(rs.getString("nim") + "\t\t\t");
                    System.out.print(rs.getString("nama") + "\t\t\t");
                    System.out.print(rs.getString("jurusan") + "\t\t\t");
                    System.out.print(rs.getString("angkatan") + "\t\t\t");
                    System.out.print(rs.getString("jenis_kelamin") + "\t\t\t");
                    System.out.print(rs.getString("email"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Data ada kesalahan " + e.toString());
            }
            lanjutkan = ulang("\nApakah anda ingin cari data lagi?");
        }
    }

    //-- awal boolean message ulang (y/n)
    static boolean ulang(String message){
        Scanner input = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanUser = input.next();

        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanUser = input.next();
        }

        return pilihanUser.equalsIgnoreCase("y");

    }

    //-- akhir boolean message ulan (y/n)


    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();

            while (!conn.isClosed()){
                TampilMenu();
            }

            stat.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
