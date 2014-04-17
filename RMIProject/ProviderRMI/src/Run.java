
import rmi.interfaces.IUser;
import rmi.beans.User;
import rmi.persistence.UserDaoImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jschmitt
 */
public class Run {
    
    public static void main(String... args) {
        
        IUser user = new User();
        user.setName("Jessica");
        
        new UserDaoImpl().save(user);
        
    }
    
}
