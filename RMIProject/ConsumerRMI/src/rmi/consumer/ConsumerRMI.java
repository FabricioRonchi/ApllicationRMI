

package rmi.consumer;

import java.rmi.Naming;
import java.util.Scanner;
import rmi.beans.User;
import rmi.interfaces.IUser;
import rmi.interfaces.IUserDAO;




public class ConsumerRMI {

    public static void main(String[] args) {
        
        
        String host = (args.length < 1) ? "127.0.0.1" : args[0];
        
        try {
            IUserDAO userDao = (IUserDAO) Naming.lookup("rmi://" + host + ":2010/UserDAO");

            Scanner in = new Scanner(System.in);
            int op = 0;
            do {
                
                System.out.println(" ____________RMI STANDART____________");
                System.out.println("|                                    |");
                System.out.println("| 1 - Adicionar Usuario              |");
                System.out.println("| 2 - Remover Usuario                |");
                System.out.println("| 3 - Buscar Usuario pelo ID         |");
                System.out.println("| 4 - Listar todos usuarios          |");
                System.out.println("|                                    |");
                System.out.println("| -1 - Para sair                     |");
                System.out.println("|____________________________________|");
                
                op = Integer.parseInt(in.nextLine());
                
                switch(op) {
                    
                    case 1:
                        IUser user = new User();
                        System.out.println("Digite o nome:");
                        String name = in.nextLine();
                        user.setName(name);
                        userDao.save(user);
                        break;
                    case 2:
                        IUser user2 = new User();
                        System.out.println("Digite o ID:");
                        Long id = Long.parseLong(in.nextLine());
                        user2.setId(id);
                        userDao.delete(user2);
                        break;
                    case 3:
                        Long uId = Long.parseLong(in.nextLine());
                        IUser user3 = userDao.findById(uId);
                        System.out.println("Id: " + user3.getId() + " - Name: " + user3.getName());
                        break;
                    case 4:
                        for(IUser u : userDao.findAll()) {
                            System.out.println("Id: " + u.getId() + " - Name: " + u.getName());
                        }
                        break;
                    
                }
                
            }while(op != -1);
        
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
    }
    
}
