/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursive_question2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Donovan van Heerden
 * GUI for the Recursive Application
 */
public final class RecursiveGUI {
    JFrame MainWindow = new JFrame("Recursive Method");
    
    JLabel lblFind = new JLabel("Number to Find:");
    JLabel lblArrayStatus = new JLabel("[ ]");
    JLabel lblFindStatus = new JLabel();
    JLabel lblMethodStatus = new JLabel("");
    
    JTextField txtFind = new JTextField(); 
    JButton btnFindNumber = new JButton("Find Number");
    
    JTable dtCarService;
    JScrollPane spCarService;
    
    List<Integer> numbers = new ArrayList<>();
    
    /**
     * Creates a new instance of the RecursiveGUI
     */
    public RecursiveGUI() {
        ConfigureMainWindow();
        MainWindow_Action();
        
        setNumbers();
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
        MainWindow.setSize(320, 320);
        int width = dim.width/2 - MainWindow.getSize().width / 2;
        int height = dim.height / 2 - MainWindow.getSize().height / 2;
        MainWindow.setLocation(width, height);
        MainWindow.getContentPane().setLayout(null);
        
        lblFind.setBounds(30, 15, 150, 25);
        lblArrayStatus.setBounds(20, 40, 500, 25);
        lblFindStatus.setBounds(35, 135, 500, 25);
        lblMethodStatus.setBounds(35,160,500,25);
        
        txtFind.setBounds(130, 15, 150, 25);
        btnFindNumber.setBounds(85, 220, 130, 25);

        MainWindow.add(lblFind);
        MainWindow.add(lblArrayStatus);
        MainWindow.add(lblFindStatus);
        MainWindow.add(lblMethodStatus);
        
        MainWindow.add(txtFind);
        MainWindow.add(btnFindNumber);
        
        MainWindow.setVisible(true);
    }
    
    /**
     * Adds event listeners to the buttons on the JFrame
     */
    private void MainWindow_Action()
    {
        try
        {
            btnFindNumber.addActionListener((java.awt.event.ActionEvent evt) -> {
                if (!FindNumber(numbers, 0)) {
                    lblFindStatus.setText("Number not found.");
                    lblMethodStatus.setText("Method Result: False");
                }
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
     * method to generate 10 random numbers from 1 ranging to 1000.
     */
    void setNumbers() {
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            numbers.add(random.nextInt(1000));
        }
        lblArrayStatus.setText(numbers.toString());
    }
    
    /**
     * Recursive Method used to find the number entered.
     * @param myList the list of numbers generated
     * @param index the target element
     * @return True if the number is found within the list provided
     */
    boolean FindNumber(List<Integer> myList, Integer index) {
        try {
            Integer find = Integer.parseInt(txtFind.getText());
            if (myList.size() -1 >= index) {
                lblFindStatus.setText("Number found at index: " + index);
                lblMethodStatus.setText("Method Result: True");
                return Objects.equals(myList.get(index), find) ? true : FindNumber(myList, ++index);
            }
            
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Invalid Number entered to find.", "Input Error", 2);
        }
        
        return false;
    }
    
    /**
     * Handles the JFrame closing event, prompting the user.
     * @param frame the current JFrame
     */
    public static void ACTION_CLOSE(JFrame frame) {
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
