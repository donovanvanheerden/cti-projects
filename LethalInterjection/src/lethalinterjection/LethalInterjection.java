/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lethalinterjection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Donovan van Heerden
 */
public class LethalInterjection {

    private static final String[] tableCols = {"First Name", "Last Name", "Age"};
    
    JFrame MainWindow = new JFrame();
    JPanel pnlMain = new JPanel();
    
    JLabel lblFirstName = new JLabel();
    JLabel lblLastName =  new JLabel();
    JLabel lblAge = new JLabel();
    
    JLabel lblSeatCount = new JLabel("Avaliable Seats: 12/12");
    
    JTextField txtFirstName = new JTextField();
    JTextField txtLastName = new JTextField();
    JTextField txtAge = new JTextField();
    
    JButton btnRegisterVIP = new JButton("Register");
    JButton btnShowVIPs = new JButton("Show VIPs");
    JButton btnRemoveVIP = new JButton("Remove VIP");
    
    JTable dtVIPS = new JTable();
    JScrollPane spVIPS = new JScrollPane();
    
    List<VIP> registered_vips;
    
    public LethalInterjection() {
        MainWindow.setTitle("Lethal-Interjection: VIP Registration System");
        MainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MainWindow.setResizable(false);
        ConfigureMainWindow();
        MainWindow_Action();
        
        registered_vips = new ArrayList<>();
        MainWindow.setVisible(true);
    }
    
    public static void main(String[] args) {
        LethalInterjection lethal = new LethalInterjection();
    }
    
    private void ConfigureMainWindow()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MainWindow.setBackground(new java.awt.Color(255, 255, 255));
        MainWindow.setSize(620, 370);
        MainWindow.setLocation(dim.width/2-MainWindow.getSize().width/2, dim.height/2-MainWindow.getSize().height/2);
        MainWindow.getContentPane().setLayout(null);
        
        
        pnlMain.setBounds(10, 10, 250, 320);
        pnlMain.setLayout(null);
        pnlMain.setBorder(BorderFactory.createEtchedBorder());
        MainWindow.add(pnlMain);
        
        lblFirstName.setText("First Name:");
        lblFirstName.setBounds(10, 10, 150, 25);
        lblLastName.setText("Last Name:");
        lblLastName.setBounds(10, 35, 150, 25);
        lblAge.setText("Age:");
        lblAge.setBounds(10, 60, 150, 25);
        lblSeatCount.setBounds(pnlMain.getSize().width + 15, 10, 150, 25);
        
        
        txtFirstName.setBounds(85, 10, 150, 25);
        txtLastName.setBounds(85, 35, 150, 25);
        txtAge.setBounds(85, 60, 150, 25);
        
        btnRegisterVIP.setBounds(60, 220, 130, 25);
        btnShowVIPs.setBounds(60, 250, 130, 25);
        btnRemoveVIP.setBounds(60, 280, 130, 25);

        pnlMain.add(lblFirstName);
        pnlMain.add(lblLastName);
        pnlMain.add(lblAge);
        MainWindow.add(lblSeatCount);
        
        pnlMain.add(txtFirstName);
        pnlMain.add(txtLastName);
        pnlMain.add(txtAge);
        
        pnlMain.add(btnRegisterVIP);
        pnlMain.add(btnShowVIPs);
        pnlMain.add(btnRemoveVIP);
        
        spVIPS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spVIPS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spVIPS.getViewport().add(dtVIPS, null);
        MainWindow.add(spVIPS);
        spVIPS.setBounds(pnlMain.getSize().width+15, 35, 340, 295);
        
        dtVIPS.setFont(new java.awt.Font("Tahoma", 0, 12));
        dtVIPS.setForeground(new java.awt.Color(0, 0, 0));
        
        //ClientMainWindow.getContentPane().add(pnlSearchControls);
        
    }
    
    private void MainWindow_Action()
    {
        try
        {
            btnRemoveVIP.addActionListener((java.awt.event.ActionEvent evt) -> {
                removeVIP();
            });
            
            btnRegisterVIP.addActionListener((java.awt.event.ActionEvent evt) -> {
                RegisterVIP();
            });
            
            btnShowVIPs.addActionListener((java.awt.event.ActionEvent evt) -> {
                ShowRegisteredVIPs();
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
    
    public void RegisterVIP() {
        try {
            if (registered_vips.size() < 12) {
                if(validateReg()) {
                    VIP reg = new VIP();

                    reg.first_name = txtFirstName.getText();
                    reg.last_name = txtLastName.getText();
                    reg.age = Integer.parseInt(txtAge.getText());

                    registered_vips.add(reg);

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
    
    public boolean validateReg() {
        
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
    
    public void removeVIP() {
        int row = dtVIPS.getSelectedRow();
        
        if (row > -1) {
            
            VIP vip = new VIP();
            
            vip.first_name = dtVIPS.getValueAt(row, 0).toString();
            vip.last_name = dtVIPS.getValueAt(row, 1).toString();
            vip.age = Integer.parseInt(dtVIPS.getValueAt(row, 2).toString());
            
            System.out.println(registered_vips.size());
            registered_vips.remove(row);
            loadRegistrations();
            System.out.println(vip.first_name + " " + vip.last_name + ", age:" + vip.age);
            System.out.println(registered_vips.size());
        }
    }
    
    public void ShowRegisteredVIPs() {
        
        if (registered_vips.size() > 0) {
            registered_vips.stream().forEach((reg) -> {
                System.out.println(reg.first_name + " " + reg.last_name + ", age: " + reg.age );
            });
        }
                
    }
    
    void loadRegistrations() {
        
        lblSeatCount.setText("Avaliable Seats: " + (12 - registered_vips.size()) + "/12");
        
        int rowCount = registered_vips.size();
        
        String[][] vips = new String[rowCount][3];
        registered_vips.stream().forEach((reg) -> {
            String[] vip_info = new String[] {reg.first_name, reg.last_name, Integer.toString(reg.age)};
            System.arraycopy(vip_info, 0, vips[registered_vips.indexOf(reg)], 0, vip_info.length);
        });
        
        dtVIPS =  new JTable(vips, tableCols);
        spVIPS.getViewport().add(dtVIPS);
        spVIPS.setViewportView(dtVIPS);
    }
    
    void clearRegistration() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
    }
    
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
