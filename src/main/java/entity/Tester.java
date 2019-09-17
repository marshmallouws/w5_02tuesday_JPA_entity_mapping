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
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Customer c = new Customer("Annika", "Ehlers");
        Customer c1 = new Customer("Peter", "Jakobsen");
        Customer c2 = new Customer("Ole", "Jakobsen");
        c.addHobby("Something");
        c.addHobby("More nothing");
        c.addPhone("12345678", "home");
        c.addPhone("98765432", "cellphone");
        em.persist(c);
        em.persist(c1);
        em.persist(c2);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
