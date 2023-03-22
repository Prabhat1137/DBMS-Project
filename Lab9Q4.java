
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.attribute.FileStoreAttributeView;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab9Q4 {
    public static void main(String[] args) {

        Connections conn = new Connections();
        Connection connection = conn.getConn();
        System.out.println("connected to database...");
        boolean exit_on_false = true;
        Scanner sc = new Scanner(System.in);
        while (exit_on_false) {
            System.out.println("1.Retrieve paper of student\n2.upload pdf file\n3.Exit");

            int choice = sc.nextInt();
            sc.nextLine();
            {
                switch (choice) {
                    case 1: {
                        System.out.println("Enter student id:");

                        int sid = sc.nextInt();
                        String query;
                        PreparedStatement pstmt;
                        ResultSet rset;
                        FileInputStream fis;
                        ArrayList<String> paperIdList = new ArrayList<>();

                        InputStream is;
                        try {
                            query = "select paper_id from student_research where snum=" + sid;
                            pstmt = connection.prepareStatement(query);
                            rset = pstmt.executeQuery();
                            int ind = 0;

                            if (rset.next()) {
                                do {
                                    paperIdList.add(rset.getString(1));
                                    System.out.println(paperIdList.get(ind));
                                    ind++;

                                } while (rset.next());
                                System.out.println("Paper_id retrieved successfully...");
                                System.out.println();
                            } else {
                                System.out.println("paper id not found");
                                System.out.println();
                            }

                            int paperCount = ind;
                            String paper[] = new String[paperCount];
                            int k;
                            for (k = 0; k < paperCount; k++) {
                                paper[k] = paperIdList.get(k);
                            }
                            int count = 0;
                            int i = 1;
                            while (count < paperCount) {

                                String query1 = "select  * from file where paper_id='" + paper[count] + "'";

                                pstmt = connection.prepareStatement(query1);

                                rset = pstmt.executeQuery();

                               
                                if (rset.next()) {

                                    do {

                                        System.out.println(rset.getString("paper_id"));
                                        Blob blob = rset.getBlob("files");
                                        byte byteArray[] = blob.getBytes(1, (int) blob.length());
                                        FileOutputStream outPutStream = new FileOutputStream(paper[count]+".out" + i + ".pdf");
                                        outPutStream.write(byteArray);
                                        System.out.println(paper[count]+".out" + i + ".pdf");
                                        System.out.println();
                                        i++;
                                    } while (rset.next());
                                } else {
                                    System.out.println(paper[count]+" Paper not found/uploaded");
                                }
                                count++;

                            }

                            System.out.println("All paper retrieved successfully");

                        } catch (Exception e) {
                            System.out.println(e);

                        }

                        break;
                    }

                    case 2: {
                        System.out.println("Enter paper_id");
                        String paperId = sc.next();
                        System.out.println("Enter pdf file name:");
                        String fileName = sc.next();
                        File file = new File(fileName);
                        FileReader fr;
                        PreparedStatement pstmt;
                        FileInputStream fis;
                        ResultSet rset;
                        InputStream is;
                        try {
                            {
                                // fr = new FileReader(file);
                                fis = new FileInputStream(file);
                                String query;
                                pstmt = connection.prepareStatement("insert into file values(?,?)");
                                pstmt.setString(1, paperId);
                                // pstmt.setCharacterStream(2,fr,file.length());
                                pstmt.setBlob(2, fis, file.length());
                                int e = pstmt.executeUpdate();
                                if (e > 0) {
                                    System.out.println("file uploading success");
                                } else {
                                    System.out.println("Can not upload file");
                                }

                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
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
