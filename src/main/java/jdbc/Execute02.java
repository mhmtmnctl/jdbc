package jdbc;

import java.sql.*;

public class Execute02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "1234");
        Statement st = con.createStatement();

        //1. Example:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean r1 = st.execute(sql1); //true ya da false verir çünkü data çağırma işlemi yaptık. burada true geldi çünkü bir veri geldi
        ResultSet result1= st.executeQuery(sql1);
        //Recordları görmek için executeQuery kullanıyoruz.
        while(result1.next()) //veri olduğu sürece
        {
            System.out.println(result1.getString("country_name"));
         //   System.out.println(result1.getString(1)); bu şekilde 1.indexdeki bilgileri getircek table'dan
        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "SELECT country_id,country_name FROM countries WHERE region_id>2";
        ResultSet result2 = st.executeQuery(sql2);
        while (result2.next())
        {
            System.out.println(result2.getString("country_id")+"-->"+result2.getString("country_name"));

        }
        //3.Example: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet result3 = st.executeQuery(sql3);
        while (result3.next())
        {
            System.out.println(result3.getInt(1)
                    +"--"+result3.getString(2)
                    +"--"+result3.getInt(3));//sadece sutun numaralarını da girebiliriz.
        }


        con.close();
        st.close();


    }
}
