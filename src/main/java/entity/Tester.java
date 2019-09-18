/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Annika
 */
public class Tester {
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        
        em.getTransaction().begin();
        
        Customer c = new Customer("Annika", "Ehlers");
        Customer c1 = new Customer("Peter", "Jakobsen");
        Address a = new Address("annikavej", "kbh");
        Address a1 = new Address("petervej", "kbh");
        
        /*Testing for address (a) having more customers and customer (c) 
          having more addresses*/
        c.addAddress(a);
        c.addAddress(a1);
        c1.addAddress(a1);
        a.addCustomer(c);
        a.addCustomer(c1);
        
        em.persist(c);
        em.persist(c1);
        em.persist(a);
        em.persist(a1);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
