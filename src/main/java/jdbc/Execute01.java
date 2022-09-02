package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // hata almamak için sql'den önce kontrol et table varmı yokmu diye
        //1.adım Driver'a kaydol
        Class.forName("org.postgresql.Driver"); //önce driver ekledik

        //2.adım Databas'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "1234");

        //3.adım: statement oluştur
        Statement st = con.createStatement();

        //4.adım: Query çalıştır.

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        String sql1 = "CREATE TABLE workers(worker_id VARCHAR(50),worker_name VARCHAR(50),worker_salary INT)";
        st.execute(sql1);
        //  System.out.println("result(create table için) = " + result);//false gelcek çünkü bir veri getirmiycek

        //2.Örnek: Alter table by adding worker_address column into the workers table
        String sql2 = "ALTER table workers ADD worker_adress VARCHAR(50)";
        st.execute(sql2);
        ////3.Örnek: Drop workers table
        String sql3 = "DROP TABLE workers";
        st.execute(sql3);

        //5.adım: bağlantı ve statement'ı kapat
        con.close();//connnection kapatıldı
        st.close(); //statement kapatıldı


    }
}
