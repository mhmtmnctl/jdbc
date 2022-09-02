package jdbc;

import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "1234");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        //1.yol offset ve fetch next kullanarak
        String sql1 = "SELECT company,number_of_employees \n" +
                "FROM companies\n" +
                "ORDER BY number_of_employees DESC\n" +
                "OFFSET 1 ROW \n" +
                "FETCH NEXT 1 ROW ONLY";

      ResultSet result1= st.executeQuery(sql1);
        while (result1.next()){
            System.out.println(result1.getString(1)+"-->"+  result1.getInt(2));
        }
        //2.yol (subQuery ile)
        String sql2 = "SELECT company,number_of_employees \n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM companies\n" +
                "\t\t\t\t\t\t\tWHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "\t\t\t\t\t\t\tFROM companies))";

        ResultSet result2= st.executeQuery(sql2);
        while (result2.next()){
            System.out.println(result2.getString(1)+"-->"+  result2.getInt(2));
        }
        con.close();
        st.close();
        result1.close();
        result2.close();
    }
}
