import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GUIRegistrationPage extends JFrame {

    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JTextField eingabe;
    private JTextField eingabe2;
    private JTextField eingabe3;
    private JButton button;
    private JButton loginButton;


    public GUIRegistrationPage(String titel) {

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
        setResizable(false);

    }


    private void addComponentsToPanel(JPanel panel)
    {
        panel.add(label);
        panel.add(eingabe);
        panel.add(label2);
        panel.add(eingabe2);
        panel.add(label3);
        panel.add(eingabe3);
        panel.add(button);
        panel.add(loginButton);

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
        label3.setForeground(Color.WHITE);
        label3.setFont(label.getFont().deriveFont(32.0f));
        eingabe3.setBackground(Color.BLACK);
        eingabe3.setForeground(Color.WHITE);
        eingabe3.setCaretColor(Color.WHITE);
        eingabe3.setFont(eingabe.getFont().deriveFont(32.0f));
    }


    private void initComponents() {

        eingabe = new JTextField(20);
        label = new JLabel("Username:");
        eingabe2 = new JTextField(20);
        label2 = new JLabel("Password:");
        button = new JButton("Create a new account");
        loginButton = new JButton("I already have an account (Login)");
        label3 = new JLabel("Repeat password:");
        eingabe3 = new JTextField(20);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = eingabe.getText();
                String passwordInput = eingabe2.getText();
                String repeatPasswordInput = eingabe3.getText();

                try {
                    if(UsernameCheck.checkIfUsernameExists(usernameInput))
                    {

                        if(passwordInput.equals(repeatPasswordInput))
                        {
                            if(passwordInput.length()>3)
                            {
                                InsertUser.insertUserIntoDatabase(usernameInput,passwordInput);
                                JOptionPane.showMessageDialog(GUIRegistrationPage.this,
                                        "<html><h1 style='font-family: Calibri; font-size: 36pt;'>Created new account successfully!");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(GUIRegistrationPage.this,
                                        "<html><h1 style='font-family: Calibri; " +
                                                "font-size: 36pt;'>Please enter at least 4 characters for your password!");
                            }

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(GUIRegistrationPage.this,
                                    "<html><h1 style='font-family: Calibri; font-size: 36pt;'>The passwords don't match!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(GUIRegistrationPage.this,
                                "<html><h1 style='font-family: Calibri; font-size: 36pt;'>This username already exists!");
                    }
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUILoginPage gui = new GUILoginPage("Login");
                dispose();
            }
        });


    }
}
