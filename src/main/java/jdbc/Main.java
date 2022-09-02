package jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
      //DBWork objesini oluşturalım. çünkü statik değil
      DBWork db = new DBWork();
     Connection con = db.connect_to_db("Techpro","postgres","1234");//bize connection data tipinde bir veri verdiği için ilgili konteynıra koyduk
        //gereken bilgileri scanner ile kullanıcıdan da alabiliriz.

        //yeni table oluşturmak için ilgili metodu çağıralım
        db.createTable(con,"employees");
    }
}
