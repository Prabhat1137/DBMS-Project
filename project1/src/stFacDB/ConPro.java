package stFacDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConPro {
   static Connection con;
   public static Connection createC(){
       try{
           // loading driver
           Class.forName("com.mysql.cj.jdbc.Driver");
           String user="root";
           String password="@Prabhat1137";
           String url="jdbc:mysql://localhost:3306/studentdb";
           con= DriverManager.getConnection(url,user,password);
       }
       catch(Exception e){
           System.out.println(e);
       }
       return con;
   }
}
