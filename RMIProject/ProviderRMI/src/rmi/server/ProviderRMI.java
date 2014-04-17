/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import rmi.interfaces.IUserDAO;
import rmi.persistence.UserDaoImpl;

/**
 *
 * @author jschmitt
 */
public class ProviderRMI {
    
   public static void main(String args[]) {
        
        try {
            IUserDAO userDao = new UserDaoImpl();
            IUserDAO stub = (IUserDAO) UnicastRemoteObject.exportObject(userDao, 0);
            Registry registry = LocateRegistry.createRegistry(2010);
            registry.bind("UserDAO", stub);

            System.err.println("Server ready");
        } catch (RemoteException | AlreadyBoundException e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
   
}
