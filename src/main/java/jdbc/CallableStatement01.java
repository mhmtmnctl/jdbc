package jdbc;

import java.sql.*;

public class CallableStatement01 {
    /*
    Java'da metodlar return type sahibi olsa da, void olsa da method olarak adlandırılır
    SQL'de ise data return ediyorsa function denir.Return yapmıyorsa "Procedure" diye adlandırılır
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC)
RETURNS NUMERIC
LANGUAGE plpgsql
AS
$$
BEGIN

RETURN x+y;

END
$$
         */
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "1234");
        Statement st = con.createStatement();
        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        //1.adım: fonksiyon kodunu yaz
        String sql1 = "        CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC) \n" +
                    "RETURNS NUMERIC \n" +
                    "LANGUAGE plpgsql \n" +
                    "AS \n" +
                    "$$                  \n" +
                    "BEGIN\n" +
                    "\n" +
                    "RETURN x+y;\n" +
                    "\n" +
                    "END\n" +
                    "$$";
        //2.adım: fonksiyonu çalıştır.
        st.execute(sql1);

        //3.adım: fonksiyonu çağır.
      CallableStatement cst1 =  con.prepareCall("{? = call toplamaF(?,?)}");//ilk soru işareti return typnını belirtmek için, diğerleri x ve y

        //4.adım:
        cst1.registerOutParameter(1,Types.NUMERIC);//ilk soru işareti numeric olcak dedik
        cst1.setInt(2,15);//ikinci soru işareti değeri 15 olsun
        cst1.setInt(3,25);//3.soru işareti değeri 25 olsun

        //5.adım: çalıştırmak için execute() methodunu kullan.
        cst1.execute();

        //6.adım: sonucu çağırmak için return data tipine göre  get() metodlarından uygun olanı kullan.
        System.out.println("toplama sonucu = " + cst1.getBigDecimal(1)); //data tipinini indexini yazdık


        //todo 2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        String sql2 = "CREATE OR REPLACE FUNCTION koniHacimF(r NUMERIC,h NUMERIC) \n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql \n" +
                "AS \n" +
                "$$                  \n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";
        st.execute(sql2);

        CallableStatement cst2 =  con.prepareCall("{? = call koniHacimF(?,?)}");

        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,15);
        cst2.setInt(3,25);

        cst2.execute();

        System.out.println("Koninin hacmi = " + cst2.getBigDecimal(1));







    }

}
