
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

class Connections {
    private Connection connection;
    private String username = "root";
    private String password = "@Prabhat1137";
    private String url = "jdbc:mysql://localhost:3306/university";

    public Connection getConn() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

public class q1 {
    public static void main(String[] args) {

        Connections conn = new Connections();
        Connection connection = conn.getConn();
        System.out.println("connected to database...");
        boolean exit_on_false = true;
        Scanner sc = new Scanner(System.in);
        while (exit_on_false) {
            System.out.println("1.create table \n2.Insert into table\n3.Exit");

        

                int choice = sc.nextInt();
                sc.nextLine();
                {
                    switch(choice)
                    {
                        case 1:
                        {
                            System.out.println("Enter table name:");
                            String tableName=sc.next();

                            System.out.println("Enter 1st attribute:");
                            String snum=sc.next();

                            System.out.println("Enter 2nd Attribute:");
                            String sname=sc.next();

                            System.out.println("Enter 3rd attrubute:");
                            String major=sc.next();

                            System.out.println("Enter 4th attribute:");
                            String level=sc.next();

                            System.out.println("Enter 5th attribute:");
                            String age=sc.next();

                            
                            String query="create table "+tableName+" values("+snum+" int , "+sname+" varchar(50), "+major+" varchar(20), "+level+" varchar(20), "+age+" int)";
                            //System.out.println(query);
                            PreparedStatement pstmt;
                            try{
                                pstmt=connection.prepareStatement(query);
                                pstmt.execute();
                                System.out.println("Table created successfully...");

                            }
                            catch(Exception e)
                            {
                                System.out.println(e);

                            }
                            
                    
                            break;
                        }
                        case 2:
                        {
                            System.out.println("Enter query:");
                            
                            String query=sc.nextLine();
                            
                            //System.out.println(query);
                            PreparedStatement pstmt;
                            try{
                                pstmt=connection.prepareStatement(query);
                                pstmt.execute();
                                System.out.println(" inserted successfully...");

                            }
                            catch(Exception e)
                            {
                                System.out.println(e);

                            }
                            break;
                        }
                        case 3:
                        {
                            exit_on_false = false;
                            break;
                        }
                        default:
                        {
                            System.out.println("Invalid selection");
                        }
                    }
                }

            }
            
        }
    }
