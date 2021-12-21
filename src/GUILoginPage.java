import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GUILoginPage extends JFrame {

        private JLabel label;
        private JLabel label2;
        private JTextField eingabe;
        private JTextField eingabe2;
        private JButton button;
        private JButton createNewAccountButton;
        private JButton deleteAccountButton;
        private ScrollPane scrollPane;


        public GUILoginPage(String titel) throws SQLException, ClassNotFoundException {

            setTitle(titel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            GridLayout mainLayout = new GridLayout(8,1);
            mainLayout.setVgap(10);

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(30,100,60,100));
            panel.setLayout(mainLayout);
            panel.setBackground(Color.BLACK);

            add(panel,BorderLayout.CENTER);

            initComponents();
            addComponentsToPanel(panel);

            setColorOfComponents();

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(true);

        }


        private void addComponentsToPanel(JPanel panel)
        {
            panel.add(label);
            panel.add(eingabe);
            panel.add(label2);
            panel.add(eingabe2);
            panel.add(button);
            panel.add(createNewAccountButton);
            panel.add(deleteAccountButton);
            panel.add(scrollPane);
        }

        private void setColorOfComponents()
        {
            label.setForeground(Color.WHITE);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            eingabe.setBackground(Color.BLACK);
            eingabe.setForeground(Color.WHITE);
            eingabe.setCaretColor(Color.WHITE);
            label.setFont(label.getFont().deriveFont(32.0f));
            button.setFont(button.getFont().deriveFont(32.0f));
            eingabe.setFont(eingabe.getFont().deriveFont(32.0f));
            label2.setForeground(Color.WHITE);
            label2.setFont(label.getFont().deriveFont(32.0f));
            eingabe2.setBackground(Color.BLACK);
            eingabe2.setForeground(Color.WHITE);
            eingabe2.setCaretColor(Color.WHITE);
            eingabe2.setFont(eingabe.getFont().deriveFont(32.0f));
            scrollPane.setBackground(Color.WHITE);
        }


        private void initComponents() throws SQLException, ClassNotFoundException {

            eingabe = new JTextField(20);
            label = new JLabel("Username:");
            eingabe2 = new JTextField(20);
            label2 = new JLabel("Password:");
            button = new JButton("Login");
            createNewAccountButton = new JButton("Create a new account");
            scrollPane = new ScrollPane();
            JLabel tableData = new JLabel(TableDataExtractor.getTableData()+"</html>");
            tableData.setFont(label.getFont().deriveFont(16.0f));
            scrollPane.add(tableData);
            deleteAccountButton = new JButton("Delete my account");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String usernameInput = eingabe.getText();
                    String passwordInput = eingabe2.getText();

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    Connection c = null;
                    try {
                        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/besucher","root","");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Statement statement = null;
                    try {
                        statement = c.createStatement();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String sql = "Select * from user where username='"+usernameInput+"' and password='"+passwordInput+"'";
                    try {
                        ResultSet rs = statement.executeQuery(sql);
                        if(rs.next())
                        {
                            JOptionPane.showMessageDialog(GUILoginPage.this,
                                    "<html><h1 style='font-family: Calibri; font-size: 36pt;'>Login successful!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(GUILoginPage.this,
                                    "<html><h1 style='font-family: Calibri; font-size: 36pt;'>Wrong username/password!");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
            });

            createNewAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GUIRegistrationPage gui = new GUIRegistrationPage("Create a new account");
                    dispose();
                }
            });

            deleteAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        GUIDeleteAccount gui = new GUIDeleteAccount("Delete my account");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    dispose();
                }
            });


        }
    }

