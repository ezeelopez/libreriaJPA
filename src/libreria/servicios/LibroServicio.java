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

import libreria.entidades.Libro;

/**
 *
 * @author eze_1
 */
public class LibroServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    AutorServicio serva = new AutorServicio();

    public void crearLibro() {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            em.getTransaction().begin();

            Libro libro = new Libro();
            System.out.println("ingrese el ISBN");
            libro.setIsbn(leer.nextLong());
            System.out.println("ingrese el titulo");
            libro.setTitulo(leer.next());
            System.out.println("ingrese el año publicado");
            libro.setAnio(leer.nextInt());
            libro.setEjemplares(null);
            libro.setEjemplaresPrestados(null);
            libro.setEjemplaresRestante(null);
            libro.setAlta(true);

            System.out.println("AUTOR al que pertenece");

            System.out.println("ingrese ID autor");
            int ida = leer.nextInt();
            Autor autor = em.find(Autor.class, ida);

            libro.setAutor(autor);

            System.out.println("Editorial al que pertenece");
            System.out.println("ingrese ID editorial");

            int ide = leer.nextInt();

            Editorial editorial = em.find(Editorial.class, ide);
            libro.setEditorial(editorial);

            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ya existe ISBN");
        }
    }

    public void editarLibro() {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            System.out.println("EDITAR LIBRO");
            em.getTransaction().begin();
            System.out.println("ingrese el id");
            long isbn = leer.nextLong();
            Libro libro = em.find(Libro.class, isbn);

            System.out.println("que quieres editar");
            System.out.println("1- TITULO");
            System.out.println("2- AÑO");
            System.out.println("3- AUTOR");
            System.out.println("4- EDITOR");

            int num = leer.nextInt();
            switch (num) {
                case 1:
                    System.out.println("ingrese el nuevo titulo");
                    libro.setTitulo(leer.next());
                    break;
                case 2:
                    System.out.println("ingrese un nuevo AÑO");
                    libro.setAnio(leer.nextInt());
                    break;
                case 3:
                    System.out.println("ingrese un nuevo id del Autor");
                    int id = leer.nextInt();
                    Autor autor = em.find(Autor.class, id);
                    libro.setAutor(autor);
                    break;
                case 4:
                    System.out.println("ingrese un nuevo id del editor");
                    int ide = leer.nextInt();
                    Editorial editorial = em.find(Editorial.class, ide);
                    libro.setEditorial(editorial);
                    break;
            }

            // libro.setTitulo(leer.next());
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("no se ha modificado!! verificar ISBN");
        }
        System.out.println("se ha modificado correctamente"); 
    }

    public void eliminarLibro() {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            em.getTransaction().begin();

            System.out.println("ingrese el id");
            long isbn = leer.nextLong();
            Libro libro = em.find(Libro.class, isbn);

            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("no se ha eliminado!! verificar ISBN");
        }
    }

    public void mostrarTodoLibro() {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            List<Libro> libro = em.createQuery("SELECT a FROM Libro a").getResultList();

            for (Libro libro1 : libro) {
                System.out.println(libro1);
            }
        } catch (Exception e) {
            System.out.println("no se ha podidio imprimir");
        }
    }

    public void consultarLibroISBN() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            System.out.println("ingrese el numero de ISBN");
            long isbn = leer.nextLong();

            List<Libro> libro = em.createQuery("SELECT a FROM Libro a WHERE a.isbn = :isbn")
                    .setParameter("isbn", isbn)
                    .getResultList();

            for (Libro libro1 : libro) {
                System.out.println(libro1);
            }
        } catch (Exception e) {
            System.out.println("verificar ISBN");
        }
    }

    public void consultarLibroTitulo() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            System.out.println("ingrese el titulo");
            String titulo = leer.next();

            List<Libro> libro = em.createQuery("SELECT a FROM Libro a WHERE a.titulo LIKE titulo")
                    .setParameter("nombre", "%" + titulo + "%")
                    .getResultList();

            for (Libro libro1 : libro) {
                System.out.println(libro1);
            }
        } catch (Exception e) {
            System.out.println("no existe ese TITULO");
        }
    }

    public void consultarLibroAutor() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            System.out.println("ingrese el AUTOR");
            String nombre = leer.next();

            List<Libro> libro = em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre  LIKE :nombre")
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();

            for (Libro libro1 : libro) {
                System.out.println(libro1);
            }
        } catch (Exception e) {
            System.out.println("no existe ese AUTOR");
        }
    }

    public void consultarLibroEditorial() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            System.out.println("ingrese el editorial");
            String nombre = leer.next();

            List<Libro> libro = em.createQuery("SELECT a FROM Libro a WHERE a.editorial.nombre LIKE :nombre")
                    .setParameter("titulo", "%" + nombre + "%")
                    .getResultList();

            for (Libro libro1 : libro) {
                System.out.println(libro1);
            }
        } catch (Exception e) {
            System.out.println("no existe ese EDITORIAL");
        }
    }

    public void darBajaLibro() {
        
        try{
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        em.getTransaction().begin();
        
        System.out.println("que libro quieres dar de baja");
        long baja = leer.nextLong();
        
        Libro libro = em.find(Libro.class, baja);
        libro.setAlta(false);
        em.merge(libro);
        
        em.getTransaction().commit();
        }catch(Exception e){
            System.out.println("el libro no se ha dadado de baja!! revise ISBN");
        }
        
    }

}
