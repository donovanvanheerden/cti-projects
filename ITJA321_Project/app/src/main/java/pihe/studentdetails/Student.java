package pihe.studentdetails;

/**
 * 
 * @author Donovan van Heerden | EL2014-0043
 */
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String address;

    /**
     * Constructor of the Student class, initialises an instance of the Student
     * class with the values provided.
     * 
     * @param id
     * @param firstName
     * @param lastName
     * @param contactNumber
     * @param address
     */
    public Student(int id, String firstName, String lastName, String contactNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    /**
     * Fetches the student Id
     * 
     * @return int Student Id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the student Id
     * 
     * @param id integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Fetches the student First Name.
     * 
     * @return String Student First Name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the student First Name.
     * 
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Fetches the student Last Name.
     * 
     * @return String Student Last Name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the student Last Name.
     * 
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Fetches the student Contact Number.
     * 
     * @return String Student Contact Number
     */
    public String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * Sets the student Contact Number.
     * 
     * @param contactNumber String
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Fetches the student Address.
     * 
     * @return String Student Address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the student Address.
     * 
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
