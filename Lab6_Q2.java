import com.mysql.cj.xdevapi.Column;
import com.mysql.cj.xdevapi.SqlResult;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.Scanner;

class ConnectionProvi {
    Connection con;

    public Connection createCon() {
        try {
            // loading driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "@Prabhat1137";
            String url = "jdbc:mysql://localhost:3306/university";
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}

class DBTablePrintr {

    public static void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int col = rsmd.getColumnCount();
            // System.out.println(col);
            String coln[] = new String[col];
            int i, j;

            if (col == 2 || col == 1) {
                System.out.print("+------------------------------------------------------------+\n|");

                for (i = 0; i < col; i++) {
                    coln[i] = rsmd.getColumnName(i + 1);

                    System.out.printf("%30.15s", coln[i] + "|");
                }
                while (rs.next()) {
                    System.out.println();
                    System.out.print("+------------------------------------------------------------+\n|");
                    for (i = 0; i < col; i++) {

                        System.out.printf("%30s ", rs.getString(i + 1) + "   |");

                    }

                }
                System.out.print("\n+------------------------------------------------------------+");
            }

            if (col > 2) {

                System.out.print(
                        "+--------------------------------------------------------------------------------------------------------------------------+\n|");

                for (i = 0; i < col; i++) {
                    coln[i] = rsmd.getColumnName(i + 1);

                    System.out.printf("%30.15s", coln[i] + "|");
                }
                while (rs.next()) {
                    System.out.println();
                    System.out.print(
                            "+--------------------------------------------------------------------------------------------------------------------------+\n|");
                    for (i = 0; i < col; i++) {

                        System.out.printf("%30s ", rs.getString(i + 1) + "   |");

                    }

                }
                System.out.print(
                        "\n+---------------------------------------------------------------------------------------------------------------------------+");
            }
        } catch (SQLException e) {
            // System.out.println("R");
            throw new RuntimeException(e);
        }
    }
}

public class Lab6_Q2 {
    public static void main(String[] args) {

        try {
            System.out.println("Connecting...");
            ConnectionProvi conp = new ConnectionProvi();
            Connection con = conp.createCon();
            System.out.println("Connected to database.");
            System.out.println("Now select from below options");

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            Scanner sc = new Scanner(System.in);
            boolean b = true;
            while (b) {
                System.out.println("\n1: Names of all classes that meet in  room =? :");
                System.out
                        .println("2: Print the rooms and the times at which classes for that course will be conducted");
                System.out.println("3: Print the names of the courses that that faculty member teaches");
                System.out.println("4: Exit");
                int choice=0;
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Input mismatch! Invalid input");
                }
                switch (choice) {
                    case 1: {
                        System.out.println("Enter room name :");
                        sc.nextLine();
                        String room = sc.nextLine();
                        String q = "select name,room from class where room='" + room + "'";
                        ResultSet set = null;
                        try {
                            set = stmt.executeQuery(q);
                            if (set.next()) {
                                set.beforeFirst();
                                DBTablePrintr.printResultSet(set);
                            } else {
                                System.out.println("Empty set");
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        break;
                    }

                    case 2: {
                        System.out.println("Enter course name :");
                        sc.nextLine();
                        String course = sc.nextLine();
                        String q = "select room,meets_at from class where name='" + course + "'";
                        ResultSet set = null;
                        try {
                            set = stmt.executeQuery(q);
                            if (set.next()) {
                                set.beforeFirst();
                                DBTablePrintr.printResultSet(set);
                            } else {
                                System.out.println("Empty set");
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    }

                    case 3: {
                        System.out.println("Enter faculty name :");
                        sc.nextLine();
                        String fname = sc.nextLine();
                        String q = "select fid,name from class where fid in(select fid from faculty where fname = '"
                                + fname + "')";
                        ResultSet set = null;
                        try {
                            set = stmt.executeQuery(q);
                            if (set.next()) {
                                set.beforeFirst();
                                DBTablePrintr.printResultSet(set);
                            } else {
                                System.out.println("Empty set");
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    }
                    case 4:{
                        b=false;
                        break;
                    }

                    default:{
                         System.out.println("please select only from below options...");
                    }
                }

                if (b) {
                    System.out.println("\npress any key to continue...");
                    sc.next();
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}