package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class BibliotecaModel {

	private String ruta = "database/libros.tsv";
	private Vector<LibroModel> libros; 
	
	public BibliotecaModel() {
		cargarLibros();
	}
	
	public void agregarLibro(LibroModel libro){
		libros.add(libro);
	}
	
	public void eliminarLibro(LibroModel libro){
		libros.remove(libro);
	}
	
	public boolean estaVacia(){
		return libros.isEmpty(); 
	}
	
	public void ordenarLibros(){
		Collections.sort(libros);
	}
	
	public boolean existeLibro(LibroModel libro){		
		return libros.indexOf(libro) != -1;
	}
	
	public void actualizarLibro(LibroModel libro){
		int index = libros.indexOf(libro);
		if(index != -1)
			libros.set(index, libro);
	}

	public void actualizarBaseDeDatos(){
		try {
	        PrintStream salida = new PrintStream(ruta);
	        
	        for (int i=0; i < libros.size(); i++)
	            imprimirEnArchivo(libros.get(i), salida);
	        
	        salida.close();
	    } catch (FileNotFoundException e) {}
	}
	
	public int getCantidadLibros() {
		return libros.size();
	}
	
	public Vector<LibroModel> getLibros() {
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
        } catch (FileNotFoundException e) {}
	}
	
	private void imprimirEnArchivo(LibroModel libro, PrintStream archivo) {
            archivo.print(libro.getISBN() + "\t");
            archivo.print(libro.getTitulo() + "\t");
            archivo.print(libro.getAutor() + "\t");
            archivo.print(libro.getEditorial() + "\t");
            archivo.print(libro.getEdicion() + "\t");
            archivo.print(libro.getAnno_de_publicacion() + "\n");
    }

}
