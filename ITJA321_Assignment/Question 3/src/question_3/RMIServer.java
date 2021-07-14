package question_3;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Donovan van Heerden | EL2014-0043
 */
public class RMIServer {
  /**
   * Constructor of RMIServer class
   */
  public RMIServer() {
  }

  /**
   * Entry point of RMIServer, creates a new instance of the RMIImpInterface
   * class, creates a RMI stub and binds the stub to localhost:4300/RMI
   * 
   * @param args
   * @throws RemoteException
   * @throws MalformedURLException
   * @throws AlreadyBoundException
   */
  public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
    try {
      RMIImpInterface obj = new RMIImpInterface();
      StudentInterface stub = (StudentInterface) UnicastRemoteObject.exportObject(obj, 0);

      LocateRegistry.createRegistry(4300);
      Naming.rebind("//localhost:4300/RMI", stub);

    } catch (RemoteException e) {
      System.err.println(e.toString());
    }

  }
}
