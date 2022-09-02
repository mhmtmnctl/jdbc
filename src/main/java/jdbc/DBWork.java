package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBWork {
    //PostgreSql bağlantı metodu
    public Connection connect_to_db(String dbName,String user,String password)  {//connection veri tipinde bir veri döndürcek
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbName,user,password);
            if (con!=null){
                System.out.println("Bağlantı sağlandı...");
            }else System.out.println("Hata, Bağlantı sağlanamadı");//bu kısım catch içinde kontrol edilebilir. sonra dene

        }catch (Exception e) {
            System.out.println("Hata!!! = " + e.getMessage());

        }
        return con;
    }

    //Yeni tablo oluşturma metodu
    public void createTable(Connection con,String tableName){
        //Statement objesi oluştur.
        Statement statement;
        try {
            String query = "CREATE TABLE "+tableName+" (empId SERIAL, name VARCHAR(50),email VARCHAR(50),salary INTEGER, PRIMARY KEY(empId))";
            statement=con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tablo oluşturuldu");
        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }
}
