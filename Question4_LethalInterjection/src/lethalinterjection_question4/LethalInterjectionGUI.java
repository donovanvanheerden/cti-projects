/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lethalinterjection_question4;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Donovan van Heerden
 * GUI for Lethal Interjection Application
 */
public class LethalInterjectionGUI {

    private static final String[] tableCols = {"Name", "Age"};
    
    JFrame MainWindow = new JFrame("Lethal-Interjection: VIP Registration System");
    JPanel pnlMain = new JPanel();
    
    JLabel lblFirstName  = new JLabel("First Name:");
    JLabel lblLastName =  new JLabel("Last Name:");
    JLabel lblAge = new JLabel("Age:");
    JLabel lblSeatCount = new JLabel("Avaliable Seats: 12/12");
    
    JTextField txtFirstName = new JTextField();
    JTextField txtLastName = new JTextField();
    JTextField txtAge = new JTextField();
    
    JButton btnRegisterVIP = new JButton("Register");
    JButton btnRemoveVIP = new JButton("Remove VIPs");
    
    JTable dtVIPS = new JTable();
    JScrollPane spVIPS = new JScrollPane();
    
    Stack<VIP> registered_vips;
    
    /**
     * Creates a new instance of the Lethal Interjection Application
     */
    public LethalInterjectionGUI() {
        
        ConfigureMainWindow();
        MainWindow_Action();
        
        registered_vips = new Stack<>();
        
    }
    
    /**
     * Configures the GUI and sets up the layout of each component.
     */
    private void ConfigureMainWindow()
    {
        MainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MainWindow.setResizable(false);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MainWindow.setBackground(new java.awt.Color(255, 255, 255));
        MainWindow.setSize(620, 370);
        int width = dim.width / 2 - MainWindow.getSize().width / 2;
        int height = dim.height / 2 - MainWindow.getSize().height / 2;
        MainWindow.setLocation(width, height);
        MainWindow.getContentPane().setLayout(null);
        
        pnlMain.setBounds(10, 10, 250, 320);
        pnlMain.setLayout(null);
        pnlMain.setBorder(BorderFactory.createEtchedBorder());
        MainWindow.add(pnlMain);
        
        lblFirstName.setBounds(10, 10, 150, 25);
        lblLastName.setBounds(10, 35, 150, 25);
        lblAge.setBounds(10, 60, 150, 25);
        lblSeatCount.setBounds(pnlMain.getSize().width + 15, 10, 150, 25);
        
        txtFirstName.setBounds(85, 10, 150, 25);
        txtLastName.setBounds(85, 35, 150, 25);
        txtAge.setBounds(85, 60, 150, 25);
        
        btnRegisterVIP.setBounds(60, 250, 130, 25);
        btnRemoveVIP.setBounds(60, 280, 130, 25);

        pnlMain.add(lblFirstName);
        pnlMain.add(lblLastName);
        pnlMain.add(lblAge);
        MainWindow.add(lblSeatCount);
        
        pnlMain.add(txtFirstName);
        pnlMain.add(txtLastName);
        pnlMain.add(txtAge);
        
        pnlMain.add(btnRegisterVIP);
        pnlMain.add(btnRemoveVIP);
        
        spVIPS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spVIPS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spVIPS.getViewport().add(dtVIPS, null);
        MainWindow.add(spVIPS);
        spVIPS.setBounds(pnlMain.getSize().width+15, 35, 340, 295);
        
        dtVIPS.setFont(new java.awt.Font("Tahoma", 0, 12));
        dtVIPS.setForeground(new java.awt.Color(0, 0, 0));
        
        MainWindow.setVisible(true);
    }
    
    /**
     * Adds event listeners to the buttons on the JFrame
     */
    private void MainWindow_Action()
    {
        try
        {
            btnRemoveVIP.addActionListener((java.awt.event.ActionEvent evt) -> {
                removeVIPs();
            });
            
            btnRegisterVIP.addActionListener((java.awt.event.ActionEvent evt) -> {
                RegisterVIP();
            });
            
            MainWindow.addWindowListener(new java.awt.event.WindowAdapter(){
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    ACTION_CLOSE(MainWindow);
                }
            });
            
        }
        catch(Exception X)
        {
            System.out.println(X);
        }         
    }
    
    /**
     * Adds the VIP to the registered VIP list if the list has not 
     * reached max capacity
     */
    void RegisterVIP() {
        try {
            if (registered_vips.size() < 12) {
                if(validateReg()) {

                    VIP reg = new VIP(txtFirstName.getText(), txtLastName.getText(), Integer.parseInt(txtAge.getText()));
                    registered_vips.push(reg);

                    loadRegistrations();
                    clearRegistration();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No more avaliable seats.", "Registration Error", 2);
                clearRegistration();
            }    
        } 
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Please enter a number for age.", "Input Error", 2);
        }
    }
    
    /**
     * 
     * @return True if all fields are filled in
     */
    boolean validateReg() {
        
        if (txtFirstName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a first name", "Registration Error", 0);
            return false;
        }

        if (txtLastName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a last name", "Registration Error", 0);
            return false;
        }

        if (txtAge.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an age", "Registration Error", 0);
            return false;
        }
        
        return true;
    }
    
    /**
     * removes all VIPs from the registered VIPs list and shows a exit report
     */
    void removeVIPs() {
        
        if (registered_vips.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no VIPs to currently remove.", "Remove VIP", 0);
            return;
        }
        
        String report = "";
        
        while (registered_vips.size() > 0) {
            report += ((VIP)registered_vips.pop()).getName() + " left the show.\n";
            loadRegistrations();
        }
        
        JOptionPane.showMessageDialog(null,report, "VIP Report", 1);
    }
    
    /**
     * Loads all registered VIPs into the JTable
     */
    void loadRegistrations() {
        
        lblSeatCount.setText("Avaliable Seats: " + (12 - registered_vips.size()) + "/12");
        
        int rowCount = registered_vips.size();
        
        String[][] vips = new String[rowCount][2];
        registered_vips.stream().forEach((reg) -> {
            String[] vip_info = new String[] {reg.getName(), Integer.toString(reg.getAge())};
            System.arraycopy(vip_info, 0, vips[registered_vips.indexOf(reg)], 0, vip_info.length);
        });
        
        dtVIPS =  new JTable(vips, tableCols);
        spVIPS.getViewport().add(dtVIPS);
        spVIPS.setViewportView(dtVIPS);
    }
    
    /**
     * Clears the textfields
     */
    void clearRegistration() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
    }
    
    /**
     * Handles the JFrame closing event, prompting the user.
     * @param frame the current JFrame
     */
    public static void ACTION_CLOSE(JFrame frame)
    {
        String[] closingOptions = {"Minimize", "Exit", "Cancel"};
        int response = JOptionPane.showOptionDialog(null,
             "Are you sure you want to exit?", "Exit?", 0,
                 JOptionPane.WARNING_MESSAGE, null, closingOptions, closingOptions[1]);

        if (response == 0)
        {
            frame.setState(JFrame.ICONIFIED);
        }
        else if (response == 1)
        {
            System.exit(0);
        }

    }
}
