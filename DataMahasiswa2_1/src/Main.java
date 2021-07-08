
import javax.security.auth.login.LoginContext;
import java.sql.*;
import java.util.Scanner;
import java.lang.String;


public class Main {
    //konfig data base
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/datamahasiswa1_2";
    static final String user = "root";
    static final String password = "";

    static Connection conn;
    static Statement stat;
    static ResultSet rs;

    //boolean untuk lanjutkan (y/n)
    static boolean lanjutkan = true;


    static void Login() {
        Scanner input = new Scanner(System.in);

       String user = "";
       String pass = "";

        while (lanjutkan) {
            System.out.println("1. Sebagai Mahasiswa.");
            System.out.println("2. Sebagai Admin.");
            System.out.println("3. KELUAR");
            System.out.print("\nPilih : ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1:
                        System.out.println("Login sebagai Mahasiswa");
                        TampilMenuMhs();
                    break;

                case 2:
                    while (lanjutkan) {
                        System.out.println("Login sebagai Admin");

                        System.out.print("\nUsername : ");
                        user = input.next();
                        System.out.print("\nPassword : ");
                        pass = input.next();

                        try {
                            String sql = ("SELECT * FROM loginadmin " + "WHERE username LIKE ('%" + user + "%')" + "AND password LIKE ('%" + pass + "%')");
                            rs = stat.executeQuery(sql);
                            if (rs.next()) {
                                System.out.println("Login berhasil, selamat datang " + rs.getString("nama"));
                                TampilMenuAdmin();
                            } else {
                                System.out.println("Login gagal, silahkan login ulang.");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        lanjutkan = ulang("Login ulang ");
                    }
                    break;

                case 3:
                    System.exit(0);
                default:
                    System.err.println("Inputan menu tidak ada, Silahkan input 1-3");
            }

        }
        lanjutkan = ulang("Kembali Kemenu Awal?");
    }

    static void TampilMenuAdmin(){
        Scanner input = new Scanner(System.in);

        while (lanjutkan) {
            System.out.println("==== ==== ====");
            System.out.println("1. TAMPILKAN DATA");
            System.out.println("2. CARI DATA");
            System.out.println("3. AKADEMIK");
            System.out.println("4. TAMBAH DATA");
            System.out.println("5. DELETE DATA");
            System.out.println("6. PERBARUI DATA");
            System.out.println("7. INPUT MATA KULIAH BARU");
            System.out.println("8. INPUT NILAI TUGAS, UTS, UAS");
            System.out.println("9. LOGOUT");
            System.out.println("================");
            System.out.print("Pilih 1-9 : ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    System.out.println("\n\n=== MENU TAMPIL DATA MAHASISWA ===");
                    TampilData();
                    System.out.println("######################################");
                    break;

                case 2:
                    System.out.println("\n\n=== MENU CARI DATA MAHASISWA ===");
                    CariData();
                    System.out.println("######################################");
                    TampilData();
                    break;

                case 3:
                    while (lanjutkan) {
                        System.out.println("\n\n=== MENU AKADEMIK ===");
                        System.out.print("\n1. KRS ( kartu rencana studi )");
                        System.out.print("\n2. KHS ( kartu hasil studi )");
                        System.out.print("\nPilih : ");
                        int pilih2 = input.nextInt();
                        switch (pilih2) {
                            case 1:
                                TampilKrs();
                                break;

                            case 2:
                                TampilKhs();
                                break;

                            default:
                                System.out.println("Inputan menu tidak ditemukan. Silahkan input 1-2");
                        }
                        lanjutkan = ulang("Kembali>");
                    }
                    break;



                case 4:
                    System.out.println("\n\n=== MENU TAMBAH DATA MAHASISWA ===");
                    TambahData();
                    System.out.println("######################################");
                    TampilData();
                    break;

                case 5:
                    System.out.println("\n\n=== MENU HAPUS DATA MAHASISWA ===");
                    HapusData();
                    System.out.println("######################################");
                    break;

                case 6:
                    System.out.println("\n\n=== MENU PERBARUI DATA MAHASISWA ===");
                    PerbaruiData();
                    System.out.println("######################################");
                    break;

                case 7:
                    System.out.println("\n\n=== MENU INPUT MATA KULIAH ===");
                    inputMatakuliah();
                    break;

                case 8:
                    System.out.println("\n\n=== MENU INPUT NILAI ===");
                    inputNilai1();
                    break;

                case 9:

                    for (int i =0; i<=3; i++) {
                        System.err.println("######################################");
                    }
                    System.err.println("################ LOGOUT ################");

                    for (int i =0; i<=3; i++) {
                        System.err.println("######################################");
                    }
                    System.exit(0);
                default:
                    System.out.println("Menu yang anda pilih salah, Silahkan input antara 1-9.");
            }
            lanjutkan = ulang("Apakah anda ingin kembali ke menu awal?");
        }
    }

    //-- awal tampil menu --//
    static void TampilMenuMhs(){
        Scanner input = new Scanner(System.in);

        String user = "";
        String pass = "";


        while (lanjutkan) {
            System.out.println("==== ==== ====");
            System.out.println("1.TAMPILKAN DATA");
            System.out.println("2.CARI DATA");
            System.out.println("3.AKADEMIK");
            System.out.println("4.KELUAR");
            System.out.println("================");
            System.out.print("Pilih 1-4 : ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    System.out.println("\n\n=== MENU TAMPIL DATA MAHASISWA ===");
                    TampilData();
                    System.out.println("######################################");
                    break;

                case 2:
                    System.out.println("\n\n=== MENU CARI DATA MAHASISWA ===");
                    CariData();
                    System.out.println("######################################");
                    TampilData();
                    break;

                case 3:
                    System.out.println("\n\n=== MENU AKADEMIK ===");
                    System.out.println("ANDA HARUS LOGIN TERLEBIH DAHULU MENGGUNAKAN AKUN YANG SUDAH TERDAFTAR");//fungsi login untuk mengelompokkan khs setiap siswa
                        user = input.nextLine();
                        System.out.print("\nUsername : ");
                        user = input.nextLine();
                        System.out.print("\nPassword : ");
                        pass = input.nextLine();

                        try {
                            String sql1 = ("SELECT * FROM loginmhs " + "WHERE username LIKE ('%" + user + "%')" + "AND password LIKE ('%" + pass + "%')");
                            rs = stat.executeQuery(sql1);

                            if (rs.next()) {

                                System.out.println("Login berhasil, selamat datang " + rs.getString("nama"));

                                while (lanjutkan) {
                                    System.out.print("\n1. KRS ( kartu rencana studi )");
                                    System.out.print("\n2. KHS ( kartu hasil studi )");
                                    System.out.print("\nPilih : ");
                                    int pilih2 = input.nextInt();
                                    switch (pilih2) {
                                        case 1:
                                            TampilKrs();
                                            break;

                                        case 2:
                                            try {
                                                String sql = "SELECT * FROM khs " + "WHERE nim LIKE ('%" + user + "%')";
                                                rs = stat.executeQuery(sql);

                                                System.out.println("\n|\t\t\tNIM\t\t\t\t|\t\t\t\tNAMA\t\t\t\t|\t\tKODE MATA KULIAH\t\t\t|\t\tNAMA MATA KULIAH\t\t\t|\t\tTUGAS\t\t|\t\tUTS\t\t|\t\t\t\tUAS\t\t\t\t|\t\t\tTOTAL NILAI\t\t\t|");
                                                System.out.println("=================================================================================================================================================================================");

                                                while (rs.next()) {
                                                    String nim = rs.getString("nim");
                                                    String nama = rs.getString("nama");
                                                    String kode_mata_kuliah = rs.getString("kode_mata_kuliah");
                                                    String nama_mata_kuliah = rs.getString("nama_mata_kuliah");
                                                    float tugas = rs.getFloat("tugas");
                                                    float uts = rs.getFloat("uts");
                                                    float uas = rs.getFloat("uas");
                                                    float ttl_nilai = rs.getFloat("ttl_nilai");

                                                    System.out.println(String.format("|%s\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t\t\t\t|%s\t\tt\t\t\t|%f\t\t\t\t\t|%f\t\t\t|%f\t\t\t\t|%f\t\t\t|", nim, nama, kode_mata_kuliah, nama_mata_kuliah, tugas, uts, uas, ttl_nilai));
                                                }
                                                System.out.println("\n");

                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                    }
                                    lanjutkan = ulang("Kembali ke menu akademik ?");
                                }
                            } else{
                                System.out.println("username atau Password salah");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    break;
                case 4:

                    for (int i =0; i<=3; i++) {
                        System.err.println("######################################");
                    }
                    System.err.println("################ KELUAR ################");

                    for (int i =0; i<=3; i++) {
                        System.err.println("######################################");
                    }
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



            System.out.println("\n|\t\t\tNIM\t\t\t\t|\t\t\t\tNAMA\t\t\t\t|\t\t\t\tTANGGAL LAHIR\t\t\t\t|\t\t\tJURUSAN\t\t\t\t|\t\tANGKATAN\t\t|\t\tJK\t\t|\t\t\t\tEMAIL\t\t\t\t|\t\t\tIPK\t\t\t|");
            System.out.println("=================================================================================================================================================================================");


            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String tgl_lahir = rs.getString("tanggal_lahir");
                String jurusan = rs.getString("jurusan");
                String angkatan = rs.getString("angkatan");
                String jk = rs.getString("jenis_kelamin");
                String email = rs.getString("email");
                float ipk = rs.getFloat("ipk");


                System.out.println(String.format("|%s\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t\t\t\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t\t|%s\t\t\t|%s\t\t\t\t|%f\t\t\t|", nim, nama, tgl_lahir, jurusan, angkatan, jk, email,ipk));
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
            System.out.print("Tanggal Lahir || format dd/mm/yy : ");
            String tgl_lahir = input.nextLine();
            String jurusan = input.nextLine();
            System.out.print("\njurusan : ");
            jurusan = input.nextLine();
            System.out.print("\nangkatan : ");
            String angkatan = input.next();
            System.out.print("\njenis kelamin : ");
            String jk = input.next();
            System.out.print("\nemail : ");
            String email = input.next();
            System.out.print("\nipk : ");
            float ipk = input.nextFloat();

            stat.executeUpdate("INSERT INTO `informasimahasiswa` (`nim`, `nama`, `tanggal_lahir`, `jurusan`, `angkatan`, `jenis_kelamin`, `email`, `ipk`) VALUES ('" + nim + "', '" + nama + "', '" + tgl_lahir + "' , '" + jurusan + "', '" + angkatan + "', '" + jk + "', '"+email+"', '" + ipk + "')");
            stat.executeUpdate("INSERT INTO `loginmhs` (`nama`,`username`,`password`) VALUES ('" + nama + "', '" + nim + "', '" + tgl_lahir + "') ");
            stat.executeUpdate( "UPDATE informasimahasiswa SET nama = UPPER (nama)");
            stat.executeUpdate( "UPDATE informasimahasiswa SET jurusan = UPPER (jurusan)");
            stat.executeUpdate( "UPDATE informasimahasiswa SET jenis_kelamin = UPPER (jenis_kelamin)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-- akhir tambah data--//

    //-- awal hapus data --//
    static  void HapusData() {

        Scanner input = new Scanner(System.in);

        String nim = "";

        System.out.println("Masukan NIM dan NAMA yang akan di HAPUS");
        System.out.print("\nNIM : ");
        nim = input.nextLine();

        try {
            String sql = ("SELECT * from informasimahasiswa " + " WHERE nim LIKE ('%" + nim + "%')");
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                //konfirmasi hapus
                System.out.println(rs.getString("nama"));
                System.out.println("Apakah NIM yang ingin anda hapus sudah sesuai dengan NAMA tersebut [Y/N]? : ");
                String pilih = input.next();
                if (pilih.equalsIgnoreCase("y")) {
                    stat.executeUpdate("DELETE FROM `informasimahasiswa` WHERE `informasimahasiswa`.`nim` = '" + nim + "' ");
                } else {
                    TampilMenuAdmin();
                }
            }
        } catch (SQLException e) {

        }

    }

    //-- akhir hapus data --//

    //-- awal update data --//
    static void PerbaruiData(){

        Scanner input = new Scanner(System.in);
        TampilData();
        while (lanjutkan) {
            System.out.println("PILIH DATA YANG AKAN DI PERBARUI");
            System.out.println("1.NAMA");
            System.out.println("2.JURUSAN");
            System.out.println("3.TANGGAL LAHIR");
            System.out.println("4.JENIS KELAMIN");
            System.out.println("5.ANGKATAN");
            System.out.println("6.EMAIL");
            System.out.println("7.EXIT");
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
                    PerbaruiTanggalLahir();
                    break;

                case 4:
                    PerbaruiGender();
                    break;

                case 5:
                    PerbaruiAngkatan();
                    break;

                case 6:
                    PerbaruiEmail();
                    break;

                case 7:
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

        String nim = "";
        String jurusan_baru = "";
        TampilData();
        System.out.println("Masukan Nim yang akan di perbarui JURUSAN ");
        System.out.println("NIM : ");
        nim = input.nextLine();

        try{
            String sql = ("SELECT * FROM informasimahasiswa " + "WHERE nim LIKE ('%" + nim + "%')");
            rs = stat.executeQuery(sql);

            while(rs.next()){
                //konfirmasi edit jurusan
                System.out.println("JURUSAN SEBELUMNYA : ");
                System.out.println(rs.getString("jurusan"));
            }
            System.out.println("Input JURUSAN baru : ");
            jurusan_baru = input.nextLine();

            String.format("");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.jurusan = '" + jurusan_baru + "' WHERE informasimahasiswa.nim ="+nim);
            stat.executeUpdate("UPDATE informasimahasiswa SET jurusan = UPPER(jurusan)");

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //-- akhir update jurusan

    //-- awal update nama --//
    static void PerbaruiNama(){
        Scanner input = new Scanner(System.in);

        String nim = "";
        String namabaru = "";
        TampilData();
        System.out.println("Masukan Nim yang akan di perbarui namanya ");
        System.out.println("NIM : ");
        nim = input.nextLine();

        try{
            String sql = ("SELECT * FROM informasimahasiswa " + "WHERE nim LIKE ('%" + nim + "%')");
            rs = stat.executeQuery(sql);

            while(rs.next()){
                //konfirmasi edit nama
                System.out.println("NAMA LAMA");
                System.out.println(rs.getString("nama"));
            }
            System.out.println("Input nama baru : ");
            namabaru = input.nextLine();

            String.format("");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.nama = '" + namabaru + "' WHERE informasimahasiswa.nim ="+nim);
            stat.executeUpdate("UPDATE informasimahasiswa SET nama = UPPER(nama)");

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //-- akhir update nama --//

    static void PerbaruiTanggalLahir(){
        Scanner input = new Scanner(System.in);

        String nim = "";
        String tglbaru = "";
        TampilData();
        System.out.println("Masukan Nim yang akan di perbarui tanggal lahirnya ");
        System.out.println("NIM : ");
        nim = input.nextLine();

        try{
            String sql = ("SELECT * FROM informasimahasiswa " + "WHERE nim LIKE ('%" + nim + "%')");
            rs = stat.executeQuery(sql);

            while(rs.next()){
                //konfirmasi edit nama
                System.out.println("TANGGAL LAHIR SEBELUMNYA");
                System.out.println(rs.getString("tanggal_lahir"));
            }
            System.out.println("Input TANGGAL LAHIR baru || format dd/mm/yy o/r 170200 : ");
            tglbaru = input.nextLine();

            String.format("");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.tanggal_lahir = '" + tglbaru + "' WHERE informasimahasiswa.nim ="+nim);
            stat.executeUpdate("UPDATE loginmhs SET loginmhs.password = '" + tglbaru +"'");

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //-- awal update gender --//
    static void PerbaruiGender(){
        Scanner input = new Scanner(System.in);

        String nim = "";
        String jkbaru = "";
        TampilData();
        System.out.println("Masukan Nim yang akan di perbarui GENDERnya ");
        System.out.println("NIM : ");
        nim = input.nextLine();

        try{
            String sql = ("SELECT * FROM informasimahasiswa " + "WHERE nim LIKE ('%" + nim + "%')");
            rs = stat.executeQuery(sql);

            while(rs.next()){
                //konfirmasi edit jenis kelamin
                System.out.println("GENDER sebelumnya :");
                System.out.println(rs.getString("jenis_kelamin"));
            }
            System.out.println("Input GENDER baru : ");
            jkbaru = input.nextLine();

            String.format("");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.jenis_kelamin = '" + jkbaru + "' WHERE informasimahasiswa.nim ="+nim);
            stat.executeUpdate("UPDATE informasimahasiswa SET jenis_kelamin = UPPER(jenis_kelamin)");

        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    //-- akhir update gender --//

    //-- awal update tahun angkatan --//
    static void PerbaruiAngkatan(){
        Scanner input = new Scanner(System.in);

        String nim = "";
        String Angkatanbaru = "";
        TampilData();
        System.out.println("Masukan Nim yang akan di perbarui Tahun Angkatanya ");
        System.out.println("NIM : ");
        nim = input.nextLine();

        try{
            String sql = ("SELECT * FROM informasimahasiswa " + "WHERE nim LIKE ('%" + nim + "%')");
            rs = stat.executeQuery(sql);

            while(rs.next()){
                //konfirmasi edit tahun angkatan
                System.out.println("TAHUN ANGKATAN SEBELUMNYA");
                System.out.println(rs.getString("angkatan"));
            }
            System.out.println("Input TAHUN ANGKATAN baru : ");
            Angkatanbaru = input.nextLine();

            String.format("");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.angkatan = '" + Angkatanbaru + "' WHERE informasimahasiswa.nim ="+nim);
            stat.executeUpdate("UPDATE informasimahasiswa SET angkatan = UPPER(angkatan)");

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //-- akhir update tahun angkatan --//

    //-- awal update email --//
    static void PerbaruiEmail(){
        Scanner input = new Scanner(System.in);

        String nim = "";
        String emailbaru = "";
        TampilData();
        System.out.println("Masukan Nim yang akan di perbarui EMAILnya ");
        System.out.println("NIM : ");
        nim = input.nextLine();

        try{
            String sql = ("SELECT * FROM informasimahasiswa " + "WHERE nim LIKE ('%" + nim + "%')");
            rs = stat.executeQuery(sql);

            while(rs.next()){
                //konfirmasi edit email
                System.out.println("EMAIL SEBLUMNYA");
                System.out.println(rs.getString("email"));
            }
            System.out.println("Input EMAIL baru : ");
            emailbaru = input.nextLine();

            String.format("");
            stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.email = '" + emailbaru + "' WHERE informasimahasiswa.nim ="+nim);


        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //-- akhir update email --//

    //-- awal searching data --//
    static void CariData(){

        Scanner input = new Scanner(System.in);

        String key = "";

        while (lanjutkan) {
            System.out.println("Masukan kata kunci. contoh nim : 19..... atau nama depan, nama tengah, nama belakang atau jurusan co : teknik");
            System.out.print("\nMasukan kata kunci : ");
            key = input.nextLine();
            try {

                String sql = ("SELECT * from informasimahasiswa " + " WHERE nim LIKE ('%" + key + "%')" + "OR nama LIKE ('%" + key + "%')" + " OR jurusan LIKE ('%" + key + "%')  ");
                rs = stat.executeQuery(sql);
                System.out.println("\n|\t\t\tNIM\t\t\t\t|\t\t\t\tNAMA\t\t\t\t|\t\t\t\tTANGGAL LAHIR\t\t\t\t|\t\t\tJURUSAN\t\t\t\t|\t\tANGKATAN\t\t|\t\tJK\t\t|\t\t\t\tEMAIL\t\t\t\t|");
                System.out.println("=================================================================================================================================================================================");


                if (rs.next()) {
                    String nim = rs.getString("nim");
                    String nama = rs.getString("nama");
                    String tgl_lahir = rs.getString("tanggal_lahir");
                    String jurusan = rs.getString("jurusan");
                    String angkatan = rs.getString("angkatan");
                    String jk = rs.getString("jenis_kelamin");
                    String email = rs.getString("email");


                    System.out.println(String.format("|%s\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t\t\t\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t\t|%s\t\t\t|%s\t\t\t\t|", nim, nama, tgl_lahir, jurusan, angkatan, jk, email));
                } else{
                    System.out.println("DATA TIDAK DITEMUKAN");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Data ada kesalahan " + e.toString());
            }
            lanjutkan = ulang("\nApakah anda ingin cari data lagi?");
        }
    }
    //-- akhir cari data --//

    //-- Awal Tampilkan Krs --//
    static void TampilKrs(){

        String sql = "SELECT * FROM krs";

        try{
            rs = stat.executeQuery(sql);

            System.out.println("\n|\t\tKODE MATA KULIAH\t\t\t|\t\tNAMA MATA KULIAH\t\t\t\t|\t\tSKS\t\t|");
            System.out.println("===================================================================================================================================================");

            while (rs.next()){
                String kode_matkul = rs.getString("kode_mata_kuliah");
                String nama_matkul = rs.getString("nama_mata_kuliah");
                int sks =  rs.getInt("sks");

                System.out.println(String.format("|\t%s\t\t\t\t\t\t\t|%s\t\t\t\t\t\t\t|%d\t\t\t\t|", kode_matkul, nama_matkul, sks));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //-- Akhir tampilkan krs --//

    //-- Awal Tampilkan KHS --//
    static void  TampilKhs(){
        try {
            String sql = "SELECT * FROM khs ";
            rs = stat.executeQuery(sql);

            System.out.println("\n|\t\t\tNIM\t\t\t\t|\t\t\t\tNAMA\t\t\t\t|\t\tKODE MATA KULIAH\t\t\t|\t\tNAMA MATA KULIAH\t\t\t|\t\tTUGAS\t\t|\t\tUTS\t\t|\t\t\t\tUAS\t\t\t\t|\t\t\tTOTAL NILAI\t\t\t|");
            System.out.println("=================================================================================================================================================================================");

            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String kode_mata_kuliah = rs.getString("kode_mata_kuliah");
                String nama_mata_kuliah = rs.getString("nama_mata_kuliah");
                float tugas = rs.getFloat("tugas");
                float uts = rs.getFloat("uts");
                float uas = rs.getFloat("uas");
                float ttl_nilai = rs.getFloat("ttl_nilai");

                System.out.println(String.format("|%s\t\t\t\t|%s\t\t\t\t|%s\t\t\t\t\t\t\t|%s\t\tt\t\t\t|%f\t\t\t\t\t|%f\t\t\t|%f\t\t\t\t|%f\t\t\t|", nim, nama, kode_mata_kuliah, nama_mata_kuliah, tugas, uts, uas,ttl_nilai));
            }
            System.out.println("\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //-- input matkul --//
    static void inputMatakuliah(){
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("\nKode mata kuliah Baru : ");
            String kode_matkul = input.nextLine();
            System.out.print("\nNama mata kuliah Baru : ");
            String nama_matkul = input.nextLine();
            System.out.print("\nJumlah SKS : ");
            int sks = input.nextInt();

            stat.executeUpdate("INSERT INTO `krs` (`kode_mata_kuliah`, `nama_mata_kuliah`, `sks`) VALUES ('" + kode_matkul + "', '" + nama_matkul + "', '" + sks + "')");
            stat.executeUpdate( "UPDATE krs SET kode_mata_kuliah = UPPER (kode_mata_kuliah)");
            stat.executeUpdate("UPDATE krs SET nama_mata_kuliah = UPPER (nama_mata_kuliah)");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //-- akhir input matkul --//

    //-- awal input nilai --//
    static void inputNilai1(){
        Scanner input = new Scanner(System.in);
        TampilKrs();
        String kode_matkul = "";
        String nama_matkul = "";
        String nim = "";
        String nama = "";
        float tugas = 0;
        float uts = 0;
        float uas = 0;
        float ttl_nilai = 0;

        System.out.print("\n\nMasukan nim : ");
        nim = input.nextLine();

        try {
            rs = stat.executeQuery("SELECT * FROM informasimahasiswa WHERE nim LIKE ('%" + nim + "%')");
            if (rs.next()) {
                nama = rs.getString("nama");
                System.out.println("NAMA : " +nama);
            } else {
                System.out.println("Nim tidak ditemukan");
                System.out.println("Silahkan masukan nim ulang.");
                inputNilai1();
            }
            try {
                System.out.print("\nMasukan kode mata kuliah : ");
                kode_matkul = input.nextLine();
                rs = stat.executeQuery("SELECT * FROM krs WHERE kode_mata_kuliah LIKE ('%" + kode_matkul + "%')");

                if (rs.next()) {
                    System.out.println(rs.getString("nama_mata_kuliah"));
                    nama_matkul = rs.getString("nama_mata_kuliah");
                }
                System.out.print("\nKODE MATA KULIAH : " + kode_matkul);
                System.out.print("\nNAMA MATA KULIAH : " + nama_matkul);
                System.out.print("\nNILAI TUGAS : ");
                tugas = input.nextFloat();
                System.out.print("\nNILAI UTS : ");
                uts = input.nextFloat();
                System.out.print("\nNILAI UAS : ");
                uas = input.nextFloat();
                ttl_nilai = tugas + uts + uas;
                System.out.print("\nTOTAL NILAI : " + ttl_nilai);

                stat.executeUpdate("INSERT INTO khs (`nim`, `nama`, `kode_mata_kuliah`, `nama_mata_kuliah`, `tugas`, `uts`, `uas`, `ttl_nilai`) VALUES ('" + nim + "', '" + nama +"', '" +kode_matkul+ "', '"+nama_matkul+"', '" +tugas+ "','"+uts+"', '"+uas+"','"+ttl_nilai+"')");
                stat.executeUpdate("UPDATE informasimahasiswa SET informasimahasiswa.ipk = '" + ttl_nilai + "' WHERE informasimahasiswa.nim ="+nim);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //-- akhir input nilai --//


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
        System.out.println("=========================================================================================");
        System.out.println("==================================== DATA MAHASISWA =====================================");
        System.out.println("=========================================================================================");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();

            while (!conn.isClosed()){
                Login();
            }

            stat.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
