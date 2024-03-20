import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    double[][] add(double[][]A, double[][]B) throws DimensExcep, RemoteException;
    double[][] sub(double[][]A, double[][]B) throws DimensExcep, RemoteException;
    double[][] multi(double[][]A, double[][]B) throws DimensExcep, RemoteException;
    double[][] trans(double[][]A) throws RemoteException;
}