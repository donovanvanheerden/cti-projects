package question_2;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Donovan van Heerden | EL2014-0043
 */
public class Client {

    private JFrame frmMain;
    private Socket socket;
    private ObjectOutputStream out;

    // This labels string array is used when positioning and laying out the
    // components
    private final String[] labels = new String[] { "Student ID:", "First Name:", "Last Name:", "Contact Number:",
            "Address:" };

    private JTextField txtId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNumber;
    private JTextField txtAddress;

    private JButton btnRegister;

    /**
     * Entry point of file. Creates a new instance of the Client.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Client client = new Client();
    }

    /**
     * Constructor for the Client class, initialises the JFrame and creates the
     * initial Socket connection
     * 
     * @throws IOException
     */
    public Client() throws IOException {
        createForm();
        connect();
    }

    /**
     * Initialises the JFrame and some components
     * 
     */
    private void initialise() {
        frmMain = new JFrame();

        txtId = new JTextField(5);
        txtFirstName = new JTextField(50);
        txtLastName = new JTextField(50);
        txtContactNumber = new JTextField(10);
        txtAddress = new JTextField(250);

        btnRegister = new JButton("Register");
    }

    /**
     * Used to setup and position all components on the JFrame
     * 
     */
    private void createForm() {
        initialise();

        frmMain.setTitle("Student Details");
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setResizable(false);
        frmMain.getContentPane().setLayout(null);

        frmMain.setBackground(new java.awt.Color(255, 255, 255));
        frmMain.setSize(320, 205);
        frmMain.setLocationRelativeTo(null); // Center JFrame

        frmMain.setVisible(true);

        txtId.setBounds(160, 10, 140, 25);
        txtFirstName.setBounds(160, 10 + (1 * 25), 140, 25);
        txtLastName.setBounds(160, 10 + (2 * 25), 140, 25);
        txtContactNumber.setBounds(160, 10 + (3 * 25), 140, 25);
        txtAddress.setBounds(160, 10 + (4 * 25), 140, 25);

        btnRegister.setBounds(160, 10 + (5 * 25), 140, 25);
        // Add the action listener to the button for handling the registration function
        btnRegister.addActionListener((ActionEvent event) -> {
            try {
                handleRegister();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        });

        // Iterate over the labels string array to set the label value and position the
        // labels dynamically
        for (int index = 0; index < labels.length; index++) {
            JLabel lbl = new JLabel(labels[index], SwingConstants.RIGHT);
            lbl.setBounds(35, 10 + (index * 25), 120, 25);
            frmMain.add(lbl);
        }

        // Add all the components to the JFrame
        frmMain.add(txtId);
        frmMain.add(txtFirstName);
        frmMain.add(txtLastName);
        frmMain.add(txtContactNumber);
        frmMain.add(txtAddress);
        frmMain.add(btnRegister);
    }

    /**
     * Initialises the socket connection on localhost:5000
     * 
     * @throws UnknownHostException
     * @throws IOException
     */
    private void connect() throws UnknownHostException, IOException {
        socket = new Socket("127.0.0.1", 5000);
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    /**
     * Handles the registration functionality. Fetches each textbox value, binds the
     * value to an instance of the StudentDetails class and sends it over the socket
     * connection.
     * 
     * @throws IOException
     */
    private void handleRegister() throws IOException {
        try {

            int id = Integer.parseInt(txtId.getText());
            String firstname = txtFirstName.getText();
            String lastname = txtLastName.getText();
            String contactnumber = txtContactNumber.getText();
            String address = txtAddress.getText();

            if (firstname.equals("") || lastname.equals("") || contactnumber.equals("") || address.equals("")) {
                return;
            }

            StudentDetails student = new StudentDetails(id, firstname, lastname, contactnumber, address);

            out.writeObject(student);
            out.flush();

            txtId.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtContactNumber.setText("");
            txtAddress.setText("");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
