package question_3;

import question_2.StudentDetails;
import java.rmi.RemoteException;

/**
 *
 * @author Donovan van Heerden | EL2014-0043
 */
public interface StudentInterface extends java.rmi.Remote {
  /**
   * Abstract function which returns an instance of the StudentDetails class
   * 
   * @param id
   * @return
   * @throws RemoteException
     * @throws java.lang.ClassNotFoundException
   */
  public abstract StudentDetails readFromDatabase(int id) throws RemoteException, ClassNotFoundException;
}
