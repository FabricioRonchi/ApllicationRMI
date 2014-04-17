
package rmi.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rmi.interfaces.IUser;
import rmi.interfaces.IUserDAO;



public class UserDaoImpl implements IUserDAO, Serializable {

    private EntityManagerFactory emf;
    
    protected EntityManager getSession() {
        emf = Persistence.createEntityManagerFactory("RMI");
        return emf.createEntityManager();
    }
    
    @Override
    public void save(IUser user) {
        EntityManager em = this.getSession();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }catch(Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<IUser> findAll() {
        return (List<IUser>) this.getSession().createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public IUser findById(Long id) {
        return (IUser) this.getSession().createQuery("SELECT u FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void delete(IUser user) {
        EntityManager em = this.getSession();
        try {
            em.getTransaction().begin();
            user = (IUser) em.createQuery("SELECT u FROM User u WHERE u.id = :id")
                .setParameter("id", user.getId())
                .getSingleResult();
            em.remove(user);
            em.getTransaction().commit();
        }catch(Exception ex) {
            em.getTransaction().rollback();
        }
    }
    
}
