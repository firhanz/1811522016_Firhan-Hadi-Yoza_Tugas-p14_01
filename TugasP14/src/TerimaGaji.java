import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
public class TerimaGaji extends Gaji

{
    static Connection conn;
	String link = "jdbc:mysql://localhost:3306/pbo";

    Integer totalGaji, jmlAbsen;   

    @Override
    public void Potongan()
    {
        this.jmlAbsen = 30-jmlHadir;
        this.potongan = jmlAbsen*200000;
    }

    @Override
    public void TotalGaji()
    {
        this.totalGaji = this.gajiPokok - this.potongan;
    }

    public void PrintJabatan()
    {
        if (showJabatan==1)
        {
            System.out.println("Komisaris");
        }
        else if (showJabatan==2)
        {
            System.out.println("Manager");
        }
        else if (showJabatan==3)
        {
            System.out.println("Karyawan");
        }
        else if (showJabatan==4)
        {
            System.out.println("Magang");
        }
        else if (showJabatan==5)
        {
            System.out.println("Satpam");
        }
    }

    public void Tampilkan()
    {
        System.out.println("\nInformasi Penerimaan Gaji");
        System.out.println("Nama pegawai    : "+this.nama.toUpperCase()); //method toUpperCase()
        System.out.println("Karakter nama   : "+this.nama.length()+" karakter"); //method lenght()
        System.out.println("Nomor Pegawai   : "+this.nomor);
        System.out.print("Jabatan         : ");
        PrintJabatan();
        System.out.println("Jumlah kehadiran: "+this.jmlHadir+" hari");
        System.out.println("Potongan gaji   : Rp"+this.potongan);
        System.out.println("Total gaji      : Rp"+this.totalGaji+"\n");
    }
    public void view()throws SQLException 
    {
        System.out.println("Daftar seluruh pekerja");
        String sql = "SELECT * FROM tugas";
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
        
        while (result.next())
        {
            System.out.print("\nNo pegawai\t\t: ");
            System.out.print(result.getString("Nomor"));
            System.out.print("\nNama pegawai\t\t: ");
            System.out.print(result.getString("Nama"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("Jabatan"));
            System.out.print("\nKehadiran\t\t: ");
            System.out.print(result.getInt("Jumlah Hadir"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.println(result.getInt("Total Gaji"));
        }
    }
    public void save() throws SQLException 
    {
        try 
        {
            System.out.println("Masukkan data karyawan");
            NoPegawai();
            NamaPegawai();
            Jabatan();
            GajiPokok();
            JumlahHariMasuk();
            Potongan();
            TotalGaji();
    
            
            String sql = "INSERT INTO tugas (Nama, Nomor, Jabatan, Jumlah Hadir, Total Gaji) VALUES ('"+nama+"','"+nomor+"','"+jabatan+"','"+jmlHadir+"','"+totalGaji+"')";
            conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            //System.out.println("Error masukkan nomor jabatan dengan benar");
            System.err.println("TETOTTTTTT ERRORRR!!!! masukkan nomor jabatan dengan benar");
        }

        catch (IndexOutOfBoundsException e)
        {
            //System.out.println("Masukkan rentang jumlah hadir 1-30");
            System.err.println("rentang jumlah hadir 1-30");
        }

        catch (InputMismatchException e)
        {
            System.err.println("Input pilihan dengan angka");
        }
        
    }
    public void delete() throws SQLException
    {
        view();
        try
        {
            System.out.println("Hapus data karyawan");
            System.out.print("Masukkan nomor pegawai yang akan dihapus : ");
            String noPegawai = input.nextLine();

            String sql = "DELETE FROM tugas WHERE Nomor = "+ noPegawai;

            conn = DriverManager.getConnection(link,"root","");
	        Statement statement = conn.createStatement();

            if(statement.executeUpdate(sql) > 0)
            {
	            System.out.println("Berhasil menghapus data pegawai (Nomor "+noPegawai+")");
	        }
        }

        catch(SQLException e)
        {
	        System.out.println("TETOOOTTT !!! DATA GAGAL DIINPUT");
	    }
        catch(Exception e)
        {
            System.out.println("masukan data yang benar");
        }
    }

    public void update() throws SQLException
    {
        view();
        try
        {
            System.out.print("Masukkan nomor pegawai ingin diubah: ");
            String noPegawai = input.nextLine();

            String sql = "SELECT * FROM tugas WHERE Nomor = " +noPegawai;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next())
            {
                System.out.print("Nama baru ["+result.getString("Nama")+"]\t: ");
                String namaPegawai = input.nextLine();

                sql = "UPDATE tugas SET nama='"+namaPegawai+"' WHERE Nomor='"+noPegawai+"'";

                if(statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui data pegawai (Nomor "+noPegawai+")");
                }
            }
            statement.close();
        }

            catch (SQLException e) 
            {
                System.err.println("TETOTTTTT!!! SALAHHH");
                System.err.println(e.getMessage());
            }
        
    }
    public void search() throws SQLException
    {
        System.out.print("Masukkan Nama Pegawai yang ingin dilihat : ");    
		String keyword = input.nextLine();
		
		String sql = "SELECT * FROM tugas WHERE Nama LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 

        while (result.next())
        {
            System.out.print("\nNo pegawai\t\t: ");
            System.out.print(result.getString("Nomor"));
            System.out.print("\nNama pegawai\t\t: ");
            System.out.print(result.getString("Nama"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("Jabatan"));
            System.out.print("\nKehadiran\t\t: ");
            System.out.print(result.getInt("Jumlah Hadir"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.println(result.getInt("Total Gaji"));
        }
        
    }
}
