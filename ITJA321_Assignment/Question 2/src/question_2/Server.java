package question_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Donovan van Heerden | EL2014-0043
 */
public class Server {
  private static final int PORT_NUMBER = 5000;
  private static ServerSocket serverSocket;

  // Connections arraylist is used to keep track of the existing connections,
  // later on this allows the server to dispose or disconnect each connection
  // gracefully before exiting
  private static ArrayList<Socket> connections;

  /**
   * Start of the server, creates a ServerSocket instance on port 5000. Waits for
   * incoming connections and creates a new thread for each new connection,
   * storing that connection into an array to keep track of.
   * 
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    connections = new ArrayList<>();
    serverSocket = new ServerSocket(PORT_NUMBER);

    while (true) {
      Socket socket = serverSocket.accept();

      connections.add(socket);

      Thread thread = new Thread(() -> {
        try {
          handleConnection(socket);
        } catch (SQLException e) {
          System.out.println(e.toString());
        }
      });

      thread.start();
    }
  }

  /**
   * Used to handle each client connection. Waits for data being sent from the
   * client and casts the object to a StudentDetails instance. Which it then tries
   * to save to the database.
   * 
   * @param client
   * @throws SQLException
   */
  private static void handleConnection(Socket client) throws SQLException {
    ObjectInputStream input = null;
    try {
      input = new ObjectInputStream(client.getInputStream());

      while (client.isConnected()) {
        StudentDetails student = (StudentDetails) input.readObject();
        saveToDatabase(student);
      }

      input.close();
      client.close();

    } catch (IOException | ClassNotFoundException ex) {
      // exit silently
    } finally {
      try {
        input.close();
        client.close();
      } catch (IOException e) {
        System.out.println(e.toString());
      }
    }
  }

  /**
   * Saves the StudentDetails data into the database, provided the student
   * parameter is not null
   * 
   * @param student
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  private static void saveToDatabase(StudentDetails student) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PIHE2019", "root", "root")) {
      PreparedStatement statement = conn.prepareStatement(
          "INSERT INTO details (Id, FirstName, LastName, ContactNumber, Address)" + " VALUES(?, ?, ?, ?, ?);");

      statement.setInt(1, student.getId());
      statement.setString(2, student.getFirstName());
      statement.setString(3, student.getLastName());
      statement.setString(4, student.getContactNumber());
      statement.setString(5, student.getAddress());

      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Allows for the server to shutdown gracefully and closes any open socket
   * connections to the server, before stopping the server
   * 
   * @throws IOException
   */
  protected void finalise() throws IOException {

    for (int i = 0; i < connections.size(); i++) {
      Socket socket = connections.get(i);
      if (socket.isClosed()) {
        continue;
      }

      socket.shutdownOutput();
      socket.close();
    }

    serverSocket.close();
  }
}
