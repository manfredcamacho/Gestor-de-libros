package model;

public class LibroModel implements Comparable<LibroModel> {

	public static final Class<?>[] CLASES_PROPIEDADES = new Class[] { 
			String.class,
			String.class, 
			String.class, 
			String.class, 
			Integer.class, 
			Integer.class 
		};

    private String ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private Integer edicion;
    private Integer anno_de_publicacion;

    @Override
    public boolean equals(Object libro) {
    	/*
    	 * para decidir si un libro es igual a otro me baso
    	 * principalmente en el ISBN del libro
    	 */
        return this==libro || (libro!=null && libro instanceof LibroModel && ISBN.equals(((LibroModel)libro).ISBN));
    }

    @Override
    public int compareTo(LibroModel libro) {
    	/*
    	 * Usado en la funcion indexOf
    	 */
        return ISBN.compareTo(libro.ISBN);
    }
    
    @Override
    public String toString() {
    	/*
    	 * Realizo un formateo de un libro para poder mnostrarlo
    	 * como una cadena.
    	 */
        return
            "ISBN               : " + ISBN + "\n" +
            "titulo             : " + titulo + "\n" +
            "autor              : " + autor + "\n" +
            "editorial          : " + editorial + "\n" +
            "edicion            : " + edicion + "\n" +
            "anno de publicacion: " + anno_de_publicacion + "\n";
    }

    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {   	
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }
    
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getEdicion() {
        return edicion;
    }
    
    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    public Integer getAnno_de_publicacion() {
        return anno_de_publicacion;
    }
    
    public void setAnno_de_publicacion(Integer anno_de_publicacion) {
        this.anno_de_publicacion = anno_de_publicacion;
    }
}