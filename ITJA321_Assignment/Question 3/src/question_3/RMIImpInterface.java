package question_3;

import java.sql.*;
import question_2.StudentDetails;

/**
 * RMIImpInterfaces implements the StudentInterface which forces the
 * implementation of only one function "readFromDatabase"
 * 
 * @author Donovan van Heerden | EL2014-0043
 */
public class RMIImpInterface implements StudentInterface {

  /**
   * Reads StudentData from the database, using the parameter id as a search to
   * select the data.
   * 
   * @param id int
   * @return StudentDetails instance
     * @throws java.lang.ClassNotFoundException
   */
  @Override
  public StudentDetails readFromDatabase(int id) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
      
    StudentDetails student = null;
    // Try and initiate a connection to the database
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PIHE2019", "root", "root");
        Statement statement = conn.createStatement()) {

      // Create the SELECT query for selected the data from the database
      ResultSet rs = statement.executeQuery("SELECT * FROM details WHERE Id = " + id + ";");

      // If we have a record or records, read the data and create an instance of the
      // StudentDetails class
      while (rs.next()) {
        String firstname = rs.getString("FirstName");
        String lastname = rs.getString("LastName");
        String contactnumber = rs.getString("ContactNumber");
        String address = rs.getString("Address");

        student = new StudentDetails(id, firstname, lastname, contactnumber, address);
      }
    } catch (SQLException e) {
      System.out.println(e.toString());
    }

    // Return the StudentDetails instance
    return student;
  }

}
