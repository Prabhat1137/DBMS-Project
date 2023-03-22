import com.mysql.cj.xdevapi.Column;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.Scanner;

class ConnectionProvider {
    Connection con;

    public Connection createCon() {
        try {
            // loading driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "@Prabhat1137";
            String url = "jdbc:mysql://localhost:3306/university";
            //accessing getconncetion() method from driver
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}

class DBTablePrinter {

    public static void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int col = rsmd.getColumnCount();
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

            if (col == 3) {
                System.out.print(
                        "+--------------------------------------------------------------------------------------------+\n|");

                for (i = 0; i < col; i++) {
                    coln[i] = rsmd.getColumnName(i + 1);

                    System.out.printf("%30.15s", coln[i] + "|");
                }
                while (rs.next()) {
                    System.out.println();
                    System.out.print(
                            "+-------------------------------------------------------------------------------------------+\n|");
                    for (i = 0; i < col; i++) {

                        System.out.printf("%30s ", rs.getString(i + 1) + "   |");

                    }

                }
                System.out.print(
                        "\n+------------------------------------------------------------------------------------------------+");
            }

            if (col == 4) {
                System.out.print(
                        "+-------------------------------------------------------------------------------------------------------------------------+\n|");

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
                        "\n+------------------------------------------------------------------------------------------------------------------------------+");
            }

            if (col == 5) {

                System.out.print(
                        "+---------------------------------------------------------------------------------------------------------------------------------------------------------+\n|");

                for (i = 0; i < col; i++) {
                    coln[i] = rsmd.getColumnName(i + 1);

                    System.out.printf("%30.15s", coln[i] + "|");
                }
                while (rs.next()) {
                    System.out.println();
                    System.out.print(
                            "+---------------------------------------------------------------------------------------------------------------------------------------------------------+\n|");
                    for (i = 0; i < col; i++) {

                        System.out.printf("%30s ", rs.getString(i + 1) + "   |");

                    }

                }
                System.out.print(
                        "\n+---------------------------------------------------------------------------------------------------------------------------------------------------------+");
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Connecting...");
            ConnectionProvider conp = new ConnectionProvider();
            Connection con = conp.createCon();
            System.out.println("Connected to database.");

            boolean b = true;
            while (b) {
                System.out.println("\nNow select from below options i.e 1,2,3,4");
                System.out.println("NOTE : PRESS 0 FOR EXIT.");

                String q = "show tables";
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet set = stmt.executeQuery(q);

                int i = 0;
                set.last();
                int row = set.getRow();
                String s[] = new String[row];
                set.beforeFirst();
                while (set.next()) {
                    System.out.print("+------------------------+\n|");
                    System.out.printf("%14s %10s", set.getString(1), "|");
                    System.out.println();
                    s[i] = set.getString(1);
                    i++;

                }
                System.out.print("+------------------------+");
                int choice = 0;
                Scanner sc = new Scanner(System.in);

                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("INPUT MISMATCH");
                    continue;
                }
                switch (choice) {
                    case 1: {
                        q = "select *from " + s[0];
                        set = stmt.executeQuery(q);
                        DBTablePrinter.printResultSet(set);
                        break;
                    }

                    case 2: {
                        q = "select *from " + s[1];
                        set = stmt.executeQuery(q);
                        DBTablePrinter.printResultSet(set);
                        break;
                    }

                    case 3: {
                        q = "select *from " + s[2];
                        set = stmt.executeQuery(q);
                        DBTablePrinter.printResultSet(set);
                        break;
                    }

                    case 4: {
                        q = "select *from " + s[3];
                        set = stmt.executeQuery(q);
                        DBTablePrinter.printResultSet(set);
                        break;
                    }
                    case 0: {
                        b = false;
                        break;
                    }

                    default: {
                        System.out.println("Invalid input");
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