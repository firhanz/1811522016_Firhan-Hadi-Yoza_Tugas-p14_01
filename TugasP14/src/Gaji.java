import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;

public class Gaji implements PTABC
{
    static Connection conn;
	String link = "jdbc:mysql://localhost:3306/pbo";

    Integer potongan, gajiPokok, jmlHadir, showJabatan;
    String nama, nomor, jabatan;
    Integer [] listJabatan = {1,2,3,4,5};
    Integer cekInput;
    String totalGaji;

    Scanner input = new Scanner(System.in);

    @Override
    public void NoPegawai() 
    {
        System.out.print("Masukkan nomor : ");
        this.nomor = input.nextLine();
    }

    @Override
    public void NamaPegawai()
    {
        System.out.print("Masukkan nama : ");
        this.nama = input.nextLine();
    }

    @Override
    public void Jabatan() 
    {
        System.out.println("Pilihan : \n1.Ketua\n2.Wakil\n3.Sekretaris\n4.Bendahara\n5.Tukang parkir");
        System.out.print("Pilih Opsi : ");
        this.cekInput = input.nextInt();

        if (cekInput.equals(listJabatan[0])) //method equals()
        {
            this.showJabatan = 1;
            this.jabatan = "Ketua";
        }
        else if (cekInput.equals(listJabatan[1])) //method equals()
        {
            this.showJabatan = 2;
            this.jabatan = "Wakil";
        }
        else if (cekInput.equals(listJabatan[2])) //method equals()
        {
            this.showJabatan = 3;
            this.jabatan = "Sekretaris";
        }
        else if (cekInput.equals(listJabatan[3])) //method equals()
        {
            this.showJabatan = 4;
            this.jabatan = "Bendahara";
        }
        else if (cekInput.equals(listJabatan[4])) //method equals()
        {
            this.showJabatan = 5;
            this.jabatan = "Tukang Parkir";
        }
        else 
        {
            listJabatan[5] = 0;
        }
    }

    @Override
    public void GajiPokok() 
    {
        System.out.println();
        if (this.showJabatan == 1)
        {
            this.gajiPokok = 1000000;
        }
        else if (this.showJabatan == 2)
        {
            this.gajiPokok = 900000;
        }
        else if (this.showJabatan == 3)
        {
            this.gajiPokok = 1232131;
        }
        else if (this.showJabatan == 4)
        {
            this.gajiPokok = 9090909;
        }
        else if (this.showJabatan == 5)
        {
            this.gajiPokok = 10000000;
        }
    }

    @Override
    public void JumlahHariMasuk() 
    {
        ArrayList<Integer> hari = new ArrayList<Integer>();
        for (int i=0; i<30; i++)
        {
            hari.add(i+1);
        }

        System.out.print("Masukkan jumlah kehadiran : ");
        this.jmlHadir = input.nextInt();
        this.jmlHadir = hari.get(jmlHadir-1);
    }

    
    public void Potongan() 
    {
        
    }

    
    public void TotalGaji() 
    {
        
    }        
    
    
}
