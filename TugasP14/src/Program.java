import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;

public class Program 
{
    static Connection conn;
    public static void main(String[] args) throws Exception 
    {

        DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
        Date tanggal = new Date();

        Scanner input = new Scanner(System.in);
        int pilihan;
        boolean balikMenu = true;
        String link = "jdbc:mysql://localhost:3306/pbo";

        System.out.println("Program menghitung gaji karyawan");
    
        while (balikMenu)
        {
			System.out.println("Database karyawan");
			System.out.println("=====================");
			System.out.println("1.\tLihat Data karyawan");
			System.out.println("2.\tTambah Data karyawan");
			System.out.println("3.\tUbah Data karyawan");
			System.out.println("4.\tHapus Data karyawan");
			System.out.println("5.\tCari Data karyawan");
            System.out.println("6.\tKeluar");
				
			System.out.print("\nPilihan anda (1/2/3/4/5/6): ");
			pilihan = input.nextInt();

            Gaji gaji = new Gaji();
            TerimaGaji terimaGaji = new TerimaGaji();
            switch (pilihan)
            {
                case 1:
                    terimaGaji.view();
                break;
                   
                case 2:
                   terimaGaji.save();
                break;

                case 3:
                   terimaGaji.update();
                break;

                case 4:
                   terimaGaji.delete();
                break;

                case 5:
                   terimaGaji.search();
                break;

                case 6:
                   balikMenu = false;
                break;

                default:
                    System.err.println("\nData tidak ditemukan\n pilih [1-6]");
                break;
            }
        }
                System.out.println("====================================");
                System.out.println("=Dibuat pada     : "+formatTanggal.format(tanggal)+"=");
                System.out.println("=Diupdate pada   : "+formatJam.format(tanggal)+" WIB    =");
                System.out.println("====================================");
                System.out.println("\nSelesai\n");
            
    }
}