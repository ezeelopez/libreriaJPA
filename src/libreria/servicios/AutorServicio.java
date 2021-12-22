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
import libreria.entidades.Autor;
import libreria.entidades.Editorial;


/**
 *
 * @author eze_1
 */
public class AutorServicio {
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearAutor(){
        
     try{   
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    em.getTransaction().begin();
    
    Autor autor = new Autor();
    
    System.out.println("ingrese el id");
    autor.setId(leer.nextInt());
    System.out.println("ingrese el nombre");
    autor.setNombre(leer.next());
    autor.setAlta(true);
    
    em.persist(autor);
    em.getTransaction().commit();
    }catch(Exception e){
        System.out.println("error!! ya exuste esta ID");
    }
    }
     public void editarAutor() {

         try{
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
           
        em.getTransaction().begin();
        System.out.println("ingrese el id");
        int id = leer.nextInt();
        Autor autor = em.find(Autor.class, id);
        
        
         autor.setNombre(leer.next());
        
         em.merge(autor);
         em.getTransaction().commit();
         }catch(Exception e){
             System.out.println("no se ha modificado!! verifique ID");
         }
    }
      public void eliminarEditorial(){
          try{
         EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
             
         em.getTransaction().begin();
         
         System.out.println("ingrese el id");
         int id = leer.nextInt();
          Autor autor = em.find(Autor.class, id);
        
          em.remove(autor);
          em.getTransaction().commit();
          }catch(Exception e){
              System.out.println("no se ha eliminado!! verificar ID");
          }
    }
    
      
        
    public void imprimireAutor(){
        
        try{
     EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        
     List<Autor> autor = em.createQuery("SELECT a FROM Autor a").getResultList();
        
        for (Autor autor1 : autor) {
            System.out.println(autor1);
        }
 
        }catch(Exception e){
            System.out.println("no se ha podido imprimir!!");
        }
        
    }
      
    public void darBajaAutor(){
        
    try{
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    em.getTransaction().begin();
    
    System.out.println("ingrese el ID que quieres dar de baja");
    int id = leer.nextInt();
    
    Autor autor = em.find(Autor.class, id);
    autor.setAlta(false);
    
    em.merge(autor);
    em.getTransaction().commit();
    
}catch(Exception e){
    System.out.println("no se ha podido dar de BAJA");
}
    }
    
public void darAltaAutor(){
    
    try{
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    em.getTransaction().begin();
    
    System.out.println("ingrese el ID para dar de ALTA");
    int id = leer.nextInt();
    
    Autor autor = em.find(Autor.class, id);
    autor.setAlta(true);
    em.merge(autor);
    em.getTransaction().commit();
}catch(Exception e){
    System.out.println("no se ha podido dar de ALTA");
}
}
    
    
    
}
