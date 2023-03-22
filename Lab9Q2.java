
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab9Q2 {
    public static void main(String[] args) {

        Connections conn = new Connections();
        Connection connection = conn.getConn();
        System.out.println("connected to database...");
        boolean exit_on_false = true;
        Scanner sc = new Scanner(System.in);
        while (exit_on_false) {
            System.out.println("1.Student information \n2.Exit");

            int choice = sc.nextInt();
            sc.nextLine();
            {
                switch (choice) {
                    case 1: {
                        System.out.println("Enter student id:");

                        int sid = sc.nextInt();
                        //System.out.println(sid);
                        PreparedStatement pstmt;
                        String query;
                        try {
                            query = "select *from student1 where snum=" + sid;
                            pstmt = connection.prepareStatement(query);
                            ResultSet rset = pstmt.executeQuery();
                            ResultSetMetaData rsmd = rset.getMetaData();
                            if (rset.next()) {
                                int column = rsmd.getColumnCount();
                                int i;
                                for (i = 1; i <= column; i++) {
                                    System.out.print(rset.getString(i) + " ");

                                }
                                System.out.println();

                                query = "select cname from enrolled natural join student1 where snum=" + sid;
                                pstmt = connection.prepareStatement(query);
                                rset = pstmt.executeQuery();
                                if (rset.next()) {
                                    System.out.println("Enrolled class/es:");
                                    do {
                                        System.out.println(rset.getString(1));

                                    } while (rset.next());
                                    System.out.println();
                                } else {
                                    System.out.println("Not enrolled in any class");
                                    query = "select name from class";
                                    pstmt = connection.prepareStatement(query);
                                    rset = pstmt.executeQuery();
                                    rsmd = rset.getMetaData();
                                    ArrayList<String> className=new ArrayList<>();
                                    int index = 0;
                                    System.out.println("Select class from below list:\n");

                                    while (rset.next()) {
                                        className.add(rset.getString(1));
                                        System.out.println((index+1)+"."+className.get(index));
                                        //System.out.println(rset.getString(1));
                                        index++;
                                    }
                                    System.out.println();
                                    int classChoice=sc.nextInt();
                                    System.out.println(className.get(classChoice-1));
                                    query="("+sid+","+className.get((classChoice-1))+");";
                                    pstmt = connection.prepareStatement("insert into NewEnrolled values(?,?)");
                                    pstmt.setInt(1,sid);
                                    pstmt.setString(2,className.get(classChoice-1));
                                    pstmt.executeUpdate();
                                    System.out.println("student having id "+sid+" enrolled successfully as NewEnrolld");
                                }

                            } else {
                                System.out.println("student id not exit in the table student1");
                            }

                        } catch (Exception e) {
                            System.out.println(e);
                            System.out.println("Exception occured...");

                        }

                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        exit_on_false = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid selection");
                    }
                }
            }

        }

    }
}
