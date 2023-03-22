package stFacDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.Scanner;

public class jdbc {
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("1.Find the names and ages of all students");
        System.out.println("2.Find all students whose age is above 18. (List their serial" +
                "numbers, names, major, level and age).");
        System.out.println("3.Find the serial numbers of all students who have enrolled" +
                "for “Database Systems”");
        System.out.println("4.Find the names of all students who have enrolled for “Data base Systems”");
        System.out.println("5.Find the names of all faculty members who belong to the department “20”");
        try{
            System.out.println("Connecting...");
            Connection con=ConPro.createC();
            System.out.println("Connected to database.");
            //create querry
//            String q="select *from student";
//            Statement stmt=con.createStatement();
//            ResultSet set=stmt.executeQuery(q);



        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch(choice){
            case 1:{
                String q="select *from student";
                Statement stmt=con.createStatement();
                ResultSet set=stmt.executeQuery(q);

                while(set.next()){
                    String sname=set.getString("sname");
                    int age=set.getInt("age");
                    System.out.print(sname+"  ");
                    System.out.println(age);
                }
                break;

            }
            case 2:{
                String q="select *from student where age>18";
                Statement stmt=con.createStatement();
                ResultSet set=stmt.executeQuery(q);

                while(set.next()){
                    String sname=set.getString("sname");
                    String major=set.getString("major");
                    String level=set.getString("level");
                    int age=set.getInt("age");
                    System.out.print(sname+"  ");
                    System.out.print(major+"  ");
                    System.out.print(level+"  ");
                    System.out.println(age);
                }
                break;

            }
            case 3:{
                String q="select snum from enrolled";
                Statement stmt=con.createStatement();
                ResultSet set=stmt.executeQuery(q);

                while(set.next()){
                    int snum=set.getInt("snum");
                    System.out.println(snum);
                }
                break;

            }

            case 4:{
                String q="select sname from student,enrolled where student.major='database system'";
                Statement stmt=con.createStatement();
                ResultSet set=stmt.executeQuery(q);

                while(set.next()){
                    String sname=set.getString("sname");
                    String major=set.getString("major");

                    System.out.print(sname+"  ");
                    System.out.println(major+"  ");

                }

                break;
            }

            case 5:{
                String q="select *from faculty where deptid=20";
                Statement stmt=con.createStatement();
                ResultSet set=stmt.executeQuery(q);

                while(set.next()){
                    String fname=set.getString("fname");

                    int id=set.getInt("deptid");
                    System.out.print(fname+"  ");
                    System.out.println(id+"  ");

                }
                break;

            }
        }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}