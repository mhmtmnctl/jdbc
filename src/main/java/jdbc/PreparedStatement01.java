package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "1234");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1.adım: Prepared statement query'sini oluştur
        // UPDATE companies SET number_of_employees = 9999 WHERE company = 'IBM'.
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?"; //reusable, prepared...

        //2.adım: PreparedStatement objesini oluştur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.adım: set...() methodları ile ? (soru işaretleri) için değer gir.
        pst1.setInt(1, 9999);   //1 demek 1.soru işareti anlamında... 1.soru işaretini 9999 yap
        pst1.setString(2, "IBM");//2.soru işaretini IBM yap

        //4.adım: sorguyu çalıştır... execute query
        pst1.executeUpdate(); //kaç satır değiştiğini verir. istersek sout içine de alabiliriz. ben gerek görmedim zaten bi tane örnekte

        String sql2 = "SELECT * FROM companies";
        ResultSet rs2 = st.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getInt(1) + "---" + rs2.getString(2) + "---" + rs2.getInt(3));
        }

        //Google için değişiklik yapalım
        pst1.setInt(1, 15000);   //1 demek 1.soru işareti anlamında... 1.soru işaretini 9999 yap
        pst1.setString(2, "GOOGLE");//2.soru işaretini IBM yap


        pst1.executeUpdate(); //kaç satır değiştiğini verir. istersek sout içine de alabiliriz. ben gerek görmedim zaten bi tane örnekte

        String sql3 = "SELECT * FROM companies";
        ResultSet result2 = st.executeQuery(sql2);
        while (result2.next()) {
            System.out.println(result2.getInt(1) + "---" + result2.getString(2) + "---" + result2.getInt(3));
        }
        System.out.println("---");
        ReadData(con,"companies");
    }

    //bir tablonun istenilen datasını prepared statements ile çağırmak için kullanacağımız method ama aynı table için.
    public static void ReadData(Connection con, String tableName) {
        try {
            String query = String.format("SELECT * FROM %s", tableName); //%s string.format dan bir özellik, dinamik olması için
            //sql query çalıştır
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(query);//datayı çağırıp rs içine koyduk
            while (rs.next())//tüm dataları çağıralım
            {
                System.out.println(rs.getInt(1) + "---" + rs.getString(2) + "---" + rs.getInt(3));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
