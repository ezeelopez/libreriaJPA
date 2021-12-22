/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

/**
 *
 * @author eze_1
 */
public class EditorialServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearEditorial() {
        
        try{
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

        Editorial editorial = new Editorial();

        em.getTransaction().begin();

        System.out.println("ingrese el ID");
        editorial.setId(leer.nextInt());
        System.out.println("ingrese el nombre");
        editorial.setNombre(leer.next());
        editorial.setAlta(true);

        em.persist(editorial);
        em.getTransaction().commit();
        }catch(Exception e){
            System.out.println("ya existe este ID");
        }
    }

    public void editarEditorial() {

        try{
        
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
           
        em.getTransaction().begin();
        System.out.println("ingrese el id");
        int id = leer.nextInt();
        Editorial editorial = em.find(Editorial.class, id);
        
        
         editorial.setNombre(leer.next());
        
         em.merge(editorial);
         em.getTransaction().commit();
        }catch(Exception e){
            System.out.println("no se ha modificado!!! verificar ID");
        }
        
    }
    
    public void eliminarEditorial(){
        
        try{
         EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
             
         em.getTransaction().begin();
         
         System.out.println("ingrese el id");
         int id = leer.nextInt();
          Editorial editorial = em.find(Editorial.class, id);
        
          em.remove(editorial);
          em.getTransaction().commit();
        } catch(Exception e){
                System.out.println("no se ha eliminado!! verifique ID");
                  }
    }
    
    
    public void imprimireEitorial(){
        
     try{
     EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        
     List<Editorial> editorial = em.createQuery("SELECT a FROM Editorial a").getResultList();
        
        for (Editorial edi : editorial) {
            System.out.println(edi);
        }
     }catch(Exception e){
         System.out.println("no se ha podido imprimir");
     }
    }
    
    public void darBajaEditorial(){
        
     try{
     EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
     em.getTransaction().begin();
     
     System.out.println("ingrese el ID del editorial que quieres dar de baja");
     int id = leer.nextInt();
     Editorial editorial = em.find(Editorial.class, id);
     editorial.setAlta(false);
     em.merge(editorial);
     em.getTransaction().commit();
        
    }catch(Exception e){
        System.out.println("no se ha podido dar de baja! revise ID!1");
    }
    }
    
    public void darAltaEditorial(){
        
    }
    
    
    
}
