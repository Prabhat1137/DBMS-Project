import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.sql.*;

class ConnectionProvidr {
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

class item extends JFrame implements ActionListener {
    JMenuItem class1;
    JMenuItem enrolled;
    JMenuItem faculty1;
    JMenuItem student;
    JMenuBar mb;
    JFrame frame;

    item() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("JDBC");
        Container c = frame.getContentPane();
        c.setLayout(null);
        mb = new JMenuBar();
        mb.setBackground(Color.CYAN);
        JMenu jm = new JMenu("All Tables");
        class1 = new JMenuItem("Class");
        class1.addActionListener(this);
        enrolled = new JMenuItem("Enrolled");
        enrolled.addActionListener(this);
        faculty1 = new JMenuItem("Faculty");
        faculty1.addActionListener(this);
        student = new JMenuItem("Student");
        student.addActionListener(this);
        jm.add(class1);
        jm.add(enrolled);
        jm.add(faculty1);
        jm.add(student);
        mb.add(jm);

        JMenu jm1 = new JMenu("Run Queries");
        JMenu room = new JMenu("By Room");
        JMenu course = new JMenu("By Course");
        JMenu faculty = new JMenu("By Faculty");

        JTextField tf = new JTextField("Enter name of any room");
        tf.setSize(200, 15);
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rm = tf.getText();
                String Q = "select name,room from class where room='" + rm+"'";
                printtable(Q);
            }
        };
        tf.addActionListener(action);

        tf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf.getText().equals("Enter name of any room")) {
                    tf.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().equals("")) {
                    tf.setText("Enter name of any room");
                }

            }
        });

        JTextField tf1 = new JTextField("Enter name of any course");
        tf1.setSize(100, 15);
        Action action1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cr = tf1.getText();
                String Q1 = " select meets_at,room from class where name='" + cr+"'";
                printtable(Q1);
            }
        };
        tf1.addActionListener(action1);

        tf1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf1.getText().equals("Enter name of any course")) {
                    tf1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf1.getText().equals("")) {
                    tf1.setText("Enter name of any course");
                }

            }
        });

        JTextField tf2 = new JTextField("Enter name of any faculty");
        tf2.setSize(100, 15);
        Action action2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fac = tf2.getText();
                String Q2 = " select distinct fid,name from class where fid in(select fid from faculty where fname='"
                        + fac + "')";
                printtable(Q2);
            }
        };
        tf2.addActionListener(action2);

        tf2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf2.getText().equals("Enter name of any faculty")) {
                    tf2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf2.getText().equals("")) {
                    tf2.setText("Enter name of any faculty");
                }

            }
        });

        room.add(tf);
        course.add(tf1);
        faculty.add(tf2);
        jm1.add(room);
        jm1.add(course);
        jm1.add(faculty);
        mb.add(jm1);
        this.setJMenuBar(mb);

        frame.setVisible(true);

        frame.setJMenuBar(mb);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String q = "";
        if (e.getSource() == enrolled) {
            q = "select *from enrolled";
            printtable(q);
        }
        if (e.getSource() == faculty1) {
            q = "select *from faculty";
            printtable(q);
        }
        if (e.getSource() == class1) {
            q = "select *from class";
            printtable(q);
        }
        if (e.getSource() == student) {
            q = "select *from student";
            printtable(q);
        }
    }

    void printtable(String q) {
        Connection con;
        JTable table;

        try {
            ConnectionProvidr conn = new ConnectionProvidr();
            con = conn.createCon();
            // System.out.println("connected");

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet set = stmt.executeQuery(q);
            ResultSetMetaData rsmd = set.getMetaData();
            int collen = rsmd.getColumnCount();
            set.last();
            int rowlen = set.getRow();
            String tab[][] = new String[rowlen][collen];
            //tab=null;
            String col[] = new String[collen];
            int i, j;
            set.beforeFirst();
            for (i = 0; i < collen; i++) {
                col[i] = rsmd.getColumnName((i + 1));
                // System.out.print(col[i]);
            }
            i = 0;
            while (set.next()) {
                for (j = 0; j < collen; j++) {
                    tab[i][j] = set.getString((j + 1));
                    // System.out.print(tab[i][j]);
                }
                i++;
                System.out.println();
            }
             
            DefaultTableModel model = new DefaultTableModel(tab, col);

            table = new JTable(model);
            JScrollPane sp = new JScrollPane(table);
            add(sp);

            frame.addWindowListener(new WindowAdapter() {
            

                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e); 
                    JOptionPane.showConfirmDialog(null,"Are sure to close!");
                    frame.dispose();
                    //this.tab=null;
                    //col=null;
                }
    
                @Override
                public void windowOpened(WindowEvent e) {
                    super.windowOpened(e); 
                    JOptionPane.showMessageDialog(null, "Welcome to the System");
                }
                
            });

            validate();
            setVisible(true);
            set.beforeFirst();
            
           
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }
}

public class jdbcGui {
    public static void main(String[] args) {

        item itm = new item();

    }
}
