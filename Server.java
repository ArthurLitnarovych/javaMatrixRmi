import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements RemoteInterface {
    protected RemoteInterface calc;
    Server(RemoteInterface calculator) throws RemoteException {
        super();
        this.calc = calculator;
    }

    @Override
    public double[][] add(double[][] A, double[][] B) throws RemoteException {
        try {
            return this.calc.add(A, B);
        } catch (DimensExcep e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public double[][] sub(double[][] A, double[][] B) throws RemoteException {
        try {
            return this.calc.sub(A, B);
        } catch (DimensExcep e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public double[][] multi(double[][] A, double[][] B) throws RemoteException {
        try {
            return this.calc.multi(A, B);
        } catch (DimensExcep e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public double[][] trans(double[][] A) throws RemoteException {
        return this.calc.trans(A);
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("Init. remote calc...");

        RemoteInterface service = new MCalc();

        Server server = new Server(service);
        String serviceName = "rmi://localhost/Server";
        Naming.rebind(serviceName, server);
        System.out.println("Server is ready...");
    } 
}
