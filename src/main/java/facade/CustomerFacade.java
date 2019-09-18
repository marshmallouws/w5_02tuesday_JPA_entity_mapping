/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bitten
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer getCustomer(int id) {
        EntityManager em = getEntityManager();
        Customer c = null;
        try {
            c = em.find(Customer.class, id);
        } finally {
            em.close();
        }
        return c;
    }

    public List<Customer> getCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Customer> query 
                    = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    } //(Check out the hints below)

    public Customer addCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

    public Customer deleteCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            //Need to find the customer before removing (cannot instantiate new object to remove!)
            Customer c = em.find(Customer.class, id); 
            if (c == null) {
                return null;
            }
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return c;
            
        } finally {
            em.close();
        }
    }
    
    /* Unsure of how to find the given customer that should be changed
        but the code offers an example of how it should be solved.
    
    */
    
    public Customer editCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try { 
            /*
            em.getTransaction().begin();
            cust.setFirstname("Lise");
            em.getTransaction().commit();*/
        } finally {
            em.close();
        }
        return null;
    }
    
    
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getFacade(emf);
        System.out.println(facade.getCustomer(1));
        System.out.println(facade.getCustomers());
        Customer cust = new Customer("Lise", "Lotte"); 
        System.out.println(facade.addCustomer(cust));
        //System.out.println(facade.deleteCustomer(3));
        
    }*/

}
