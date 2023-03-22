import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class database {
    public static void main(String[] args) {
        boolean b=true;
        while(b){

        System.out.println("\n");
        System.out.println("1.Find the names and ages of all students");
        System.out.println("2.Find all students whose age is above 18. (List their serial " +
                "numbers, names, major, level and age).");
        System.out.println("3.Find the serial numbers of all students who have enrolled " +
                "for 'Database Systems'");
        System.out.println("4.Find the names of all students who have enrolled for 'Data base Systems'");
        System.out.println("5.Find the names of all faculty members who belong to the department '20'");
        System.out.println("6.exit");
        try {

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(
                                "E:\\E Drive\\IIITG Study\\Semester\\SEM 4\\DBMS lab\\Lab1\\Project2\\files\\student.txt"));
                        String s;
                        while ((s = br.readLine()) != null) {
                            String words[] = s.split(",");
                            System.out.println(words[1] + "    " + words[4]);

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
                case 2: {

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(
                                "E:\\E Drive\\IIITG Study\\Semester\\SEM 4\\DBMS lab\\Lab1\\Project2\\files\\student.txt"));
                        String s;
                        while ((s = br.readLine()) != null) {
                            String words[] = s.split(",");
                            if (Integer.parseInt(words[4]) > 18) {
                                System.out.print(words[0] + "   " + words[1] + "    " + words[2] + "    " + words[3]
                                        + "    " + words[4]);

                            }

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
                case 3: {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(
                                "E:\\E Drive\\IIITG Study\\Semester\\SEM 4\\DBMS lab\\Lab1\\Project2\\files\\enrolled.txt"));
                        String s;
                        while ((s = br.readLine()) != null) {
                            String words[] = s.split(",");
                            if ((words[1]).equals("Database Systems")) {
                                System.out.println(words[0]);

                            }

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

                case 4: {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(
                                "E:\\E Drive\\IIITG Study\\Semester\\SEM 4\\DBMS lab\\Lab1\\Project2\\files\\enrolled.txt"));
                        String s;
                        while ((s = br.readLine()) != null) {
                            String words[] = s.split(",");
                            if ((words[1]).equals("Database Systems")) {
                                String snum = words[0];
                                BufferedReader br1 = new BufferedReader(new FileReader(
                                        "E:\\E Drive\\IIITG Study\\Semester\\SEM 4\\DBMS lab\\Lab1\\Project2\\files\\student.txt"));
                                String s1;
                                while ((s1 = br1.readLine()) != null) {
                                    String words1[] = s1.split(",");
                                    if ((words1[0]).equals(snum)) {
                                        System.out.println(words1[1]);

                                    }

                                }

                            }

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

                case 5: {

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(
                                "E:\\E Drive\\IIITG Study\\Semester\\SEM 4\\DBMS lab\\Lab1\\Project2\\files\\faculty.txt"));
                        String s;
                        while ((s = br.readLine()) != null) {
                            String words[] = s.split(",");
                            if (Integer.parseInt(words[2]) == 20) {
                                System.out.println(words[1]);

                            }

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
                case 6:{
                    b=false;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }

}