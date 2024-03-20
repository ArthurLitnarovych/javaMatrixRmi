import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Remote {
    public static final int PORT = 2099;
    public static final String HOST = "207.154.236.239";

    Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("Init. remote calc...");

        MCalc service = new MCalc();

        LocateRegistry.createRegistry(Server.PORT);
        Registry registry = LocateRegistry.getRegistry(Server.HOST, Server.PORT);
        Remote remServer = UnicastRemoteObject.exportObject(service, Server.PORT);

        String serviceName = "Server";
        registry.rebind(serviceName, remServer);

        System.out.println("Server is ready!");
    } 
}
