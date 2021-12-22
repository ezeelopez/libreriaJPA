/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;

/**
 *
 * @author eze_1
 */
public class Libreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
           
        EditorialServicio servE = new EditorialServicio();
        LibroServicio servL = new LibroServicio();
        AutorServicio servA = new AutorServicio();
          
         while(true){
        System.out.println("1- crear un nuevo Autor");
        System.out.println("2- crear un nuevo Editorial");
        System.out.println("3- crear un nuevo Libro");
        System.out.println("4- mostrar todo los libro");
        System.out.println("5- buscar libro por ISBN");
        System.out.println("6- buscar libro por TITULO");
        System.out.println("7- buscar Libro por AUTOR");
        System.out.println("8- buscar Libro por EDITORIAL");
        System.out.println("9- mostrar todo los AUTORES");
        System.out.println("10-mostrar todo los EDITORIALES");
        System.out.println("11- editar AUTOR");
        System.out.println("12- editar EDITORIAÑ");
        System.out.println("13- editar LIBRO");
        System.out.println("15- salir");
        int sal = leer.nextInt();
           switch(sal){
               case 1: 
                   servA.crearAutor();
                   break;
               case 2: 
                   servE.crearEditorial();
                   break;
               case 3:
                   servL.crearLibro();
                   break;
               case 4:
                   servL.mostrarTodoLibro();
                   break;
               case 5:
                   servL.consultarLibroISBN();
                   break;
               case 6:
                   servL.consultarLibroTitulo();
                   break;
               case 7:
                   servL.consultarLibroAutor();
                   break;
               case 8:
                   servL.consultarLibroEditorial();
                   break;
               case 9:
                   servA.imprimireAutor();
                   break;
               case 10:
                   servE.imprimireEitorial();
               case 11:
                   servA.editarAutor();
                   break;
               case 12:
                   servE.editarEditorial();
                   break;
               case 13:
                   servL.editarLibro();
                   break;
               case 15:
                   System.exit(0);
                   break;
           }
    }
    
    
    }

    }
    

