package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class BibliotecaModel {

	private String directorio = "./database/";
	private String archivo = "libros.tsv";
	private String ruta = directorio + archivo;
	private Vector<LibroModel> libros; 
	
	public BibliotecaModel() {
		cargarLibros();
	}
	
	public void agregarLibro(LibroModel libro){
		/*
		 * agrego un libro a mi libreria
		 */
		libros.add(libro);
		generarLog("[ALTA] se agrego un nuevo libro con ISBN: " + libro.getISBN());
	}
	
	public void eliminarLibro(LibroModel libro){
		/*
		 * elimino un libro de mi biblioteca
		 */
		libros.remove(libro);
		generarLog("[BAJA] Se elimino el libro con ISBN: " + libro.getISBN());
	}
	
	public boolean estaVacia(){
		/*
		 * Verifico si la biblioteca esta vacia
		 */
		return libros.isEmpty(); 
	}
	
	public void ordenarLibros(){
		/*
		 * ordeno el vector de libros de acuerdo al 
		 * ISBN
		 */
		Collections.sort(libros);
		generarLog("[ORDENAMIENTO] se ordeno la lista de libros.");
	}
	
	public boolean existeLibro(LibroModel libro){	
		/*
		 * verifica si un libro existe en la biblioteca
		 */
		return libros.indexOf(libro) != -1;
	}
	
	public void actualizarLibro(LibroModel libro){
		/*
		 * primero verifico que el libro existe en el 
		 * vector, si lo encuentra obtengo el indice
		 * y piso el libro viejo con una nueva vesion
		 */
		int index = libros.indexOf(libro);
		if(index != -1){
			libros.set(index, libro);
			generarLog("[ACTUALIZACION] se actualizo libro con ISBN: " + libro.getISBN());
		}
	}

	public void actualizarBaseDeDatos(){
		try {		    
	        PrintStream salida = new PrintStream(ruta);
	        
	        for (int i=0; i < libros.size(); i++)
	            imprimirEnArchivo(libros.get(i), salida);
	        
	        salida.close();
	        generarLog("[ACTUALIZACION] Se actualizo la base de datos. " + libros.size() + " libros cargados.");
	    } catch (FileNotFoundException e) {}		
	}
	
	public int getCantidadLibros() {
		/*
		 * retorno la cantidad de libros que tengo actualmente
		 * en el vector
		 */
		return libros.size();
	}
	
	public Vector<LibroModel> getLibros() {
		/*
		 * devuelvo una referencia de la variable libros
		 */
		return libros;
	}
	
	public LibroModel getLibro(LibroModel libro){
		/*
		 * libro contiene solo el ISBN, busco el libro en 
		 * mi biblioteca, si lo encuentro devuelvo toda la 
		 * informacion del libro
		 */
		int index = libros.indexOf(libro);
		if(index != -1)
			return libros.get(index); 
		return null;
	}
	
	
	private void cargarLibros(){
		/*
		 * Primero verifico si el archivo con los libros existe
		 * si no existe creo el archivo dentro de un directorio
		 * en caso de una excepcion se muestra un mensaje en un pop-up 
		 */
		try {
			File archivo = new File(ruta);
		    
		    if (!archivo.exists()){
		    	new File(directorio).mkdirs();
		    	archivo.createNewFile();
		    }
		}
	    catch (IOException e){
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }	    
	    
		libros = new Vector<LibroModel>();
        LibroModel libro;
        String[] campos;
		try {
            Scanner entrada = new Scanner(new FileReader(ruta));
            while (entrada.hasNextLine()) {
                campos = entrada.nextLine().split("\t");
                libro = new LibroModel();
                libro.setISBN(campos[0]);
                libro.setTitulo(campos[1]);
                libro.setAutor(campos[2]);
                libro.setEditorial(campos[3]);
                libro.setEdicion(Integer.parseInt(campos[4]));
                libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
                libros.add(libro);
            }
            entrada.close();
            generarLog("[CARGA DE LIBROS]");
        } catch (FileNotFoundException e) {}
	}
	
	private void imprimirEnArchivo(LibroModel libro, PrintStream archivo) {
			/*
			 * Graba los datos de un libro en un archivo
			 * esta funcion es utilizada en un for para 
			 * grabar todos los libros de uno.
			 */
		
            archivo.print(libro.getISBN() + "\t");
            archivo.print(libro.getTitulo() + "\t");
            archivo.print(libro.getAutor() + "\t");
            archivo.print(libro.getEditorial() + "\t");
            archivo.print(libro.getEdicion() + "\t");
            archivo.print(libro.getAnno_de_publicacion() + "\n");
    }
	
	public void generarLog(String msg){
		/*
		 * Funcion que graba un mensaje en un archivo especial llamado
		 * gestor.log, esta funcion es utilizada en cada funcion que
		 * necesite guardar un log.
		 */
		String rutaLog = "./log/gestor.log";
		try {
			File archivo = new File("./log/gestor.log");
		    
		    if (!archivo.exists()){
		    	new File("./log").mkdirs();
		    	archivo.createNewFile();
		    }
		    
		    PrintStream salida = new PrintStream(new FileOutputStream(rutaLog, true));
		    java.util.Date fecha = new java.util.Date();
		    salida.append(fecha + "\t" + msg + "\n");
		    salida.close();
		}
	    catch (IOException e){
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}

}
