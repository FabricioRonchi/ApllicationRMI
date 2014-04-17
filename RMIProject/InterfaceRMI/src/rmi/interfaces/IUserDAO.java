
package rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IUserDAO extends Remote {
    
    public void save(IUser user) throws RemoteException;
    public List<IUser> findAll() throws RemoteException;
    public IUser findById(Long id) throws RemoteException;
    public void delete(IUser user) throws RemoteException;
    
}
