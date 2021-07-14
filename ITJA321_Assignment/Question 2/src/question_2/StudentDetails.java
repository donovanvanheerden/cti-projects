package question_2;

import java.io.Serializable;

/**
 *
 * @author Donovan van Heerden | EL2014-0043
 */
public class StudentDetails implements Serializable {

    /**
     * Constructor of StudentDetails, takes in all required parameters to initialise
     * the class
     * 
     * @param Id            int
     * @param FirstName     String
     * @param LastName      String
     * @param ContactNumber String
     * @param Address       String
     */
    public StudentDetails(int Id, String FirstName, String LastName, String ContactNumber, String Address) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ContactNumber = ContactNumber;
        this.Address = Address;
    }

    private final int Id;
    private final String FirstName;
    private final String LastName;
    private final String ContactNumber;
    private final String Address;

    /**
     * Returns the Id of the StudentDetails instance
     * 
     * @return Id
     */
    public int getId() {
        return this.Id;
    }

    /**
     * Returns the FirstName of the StudentDetails instance
     * 
     * @return FirstName
     */
    public String getFirstName() {
        return this.FirstName;
    }

    /**
     * Returns the LastName of the StudentDetails instance
     * 
     * @return LastName
     */
    public String getLastName() {
        return this.LastName;
    }

    /**
     * Returns the ContactNumber of the StudentDetails instance
     * 
     * @return ContactNumber
     */
    public String getContactNumber() {
        return this.ContactNumber;
    }

    /**
     * Returns the Address of the Student Details instance
     * 
     * @return Address
     */
    public String getAddress() {
        return this.Address;
    }
}
