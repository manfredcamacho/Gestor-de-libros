package view.consola;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import controller.Gestor;
import model.LibroModel;
import view.View;

public class ConsolaView implements View {

	private Gestor gestor;
	private Scanner teclado = new Scanner(System.in);
	private PrintStream out = System.out;
	
	public ConsolaView(){
		/*
		 * Esto es para verificar si estamos en un sistema linux
		 * u otro
		 */
		if(!System.getProperties().get("os.name").equals("Linux") && System.console()!=null)
            try {
                out = new PrintStream(System.out, true, "CP850");
                teclado = new Scanner(System.in, "CP850");
            } catch (UnsupportedEncodingException e) {}
	}
	
    @Override
	public void mostrarView() {
    	/*
    	 * metodo principal para mostrar la vista
    	 */
		mostrarPantallaPrincipal();		
	}
    
	@Override
    public void setGestor(Gestor gestor){
		/*
		 * fundamental para la ocmunicacion entre 
		 * clases respetando el modelo MVC
		 */
    	this.gestor = gestor;
    }
	
	private void mostrarPantallaPrincipal(){
		/*
		 * Muestro la pantalla principal de la aplicacion
		 * es este caso es un vista por consola
		 */
		int opcion;
		do {			
            opcion = mostrarMenuView();
            procesarOpcion(opcion);
        } while (opcion!=7);
	}
	
	private int mostrarMenuView(){
		/*
		 * Muestro el menu de opciones principal
		 * y ademas valido que solo se ingrese una 
		 * opcion valida.
		 */
		int opcion;
		
		out.println("MEN\u00DA");
        out.println("1.- Altas");
        out.println("2.- Consultas");
        out.println("3.- Actualizaciones");
        out.println("4.- Bajas");
        out.println("5.- Ordenar registros");
        out.println("6.- Listar registros");
        out.println("7.- Salir");
        do {
            opcion = leer_entero ("Seleccione una opci\u00F3n");
            if(opcion<1 || opcion>7)
                out.println("Opci\u00F3nn no v\u00E1lida.");
        } while (opcion<1 || opcion>7);
        out.println();
        return opcion;
	}

	private void mostrarAltaView(){
		/*
		 * Vista para la alta de libros
		 */
		LibroModel libro = new LibroModel();
		libro.setISBN(leer_cadena ("Ingrese el ISBN del libro"));
		/*
		 * verifico que el libro exista en la biblioteca
		 */
		if (gestor.getBibliotecaModel().existeLibro(libro))
			out.println("El registro ya existe.");
		else{
			libro.setTitulo(leer_cadena ("Ingrese el titulo"));
			libro.setAutor(leer_cadena ("Ingrese el autor"));
			libro.setEditorial(leer_cadena ("Ingrese el editorial"));
			libro.setEdicion(leer_entero ("Ingrese el edicion"));
			libro.setAnno_de_publicacion(leer_entero ("Ingrese el anno de publicacion"));
			
			gestor.getBibliotecaModel().agregarLibro(libro);
			out.println("\nRegistro agregado correctamente.");
		}
	}
	
	private void mostrarConsultaView(){
		/*
		 * Vista para la consulta de un libro por su 
		 * ISBN
		 */
		LibroModel libro = new LibroModel();
		libro.setISBN(leer_cadena ("Ingrese el ISBN del libro"));
		
		/*
		 * verifico que el libro existe en la biblioteca
		 */
		if (!gestor.getBibliotecaModel().existeLibro(libro))
			out.println("\nRegistro no encontrado.");
		else{
			libro = gestor.getBibliotecaModel().getLibro(libro);
			mostrarLibro(libro);
		}
		out.println();
	}
	
	private void mostrarActualizacionView(){
		/*
		 * vista para la actualizacion de un libro
		 */
		LibroModel libro = new LibroModel();
		libro.setISBN(leer_cadena ("Ingrese el ISBN del libro"));
		
		/*
		 * Verifico que el libro existe en mi biblioteca
		 */
		if (!gestor.getBibliotecaModel().existeLibro(libro))
			out.println("\nRegistro no encontrado.");
		else{
			/*
			 * Si existe muestro el menu de opciones de actualizacion
			 */
			int opcion = mostrarMenuActualizacion();
			switch (opcion) {
				case 1:
					libro.setTitulo(leer_cadena ("Ingrese el nuevo titulo"));
					break;
				case 2:
					libro.setAutor(leer_cadena ("Ingrese el nuevo autor"));
					break;
				case 3:
					libro.setEditorial(leer_cadena ("Ingrese el nuevo editorial"));
					break;
				case 4:
					libro.setEdicion(leer_entero ("Ingrese el nuevo edicion"));
					break;
				case 5:
					libro.setAnno_de_publicacion(leer_entero ("Ingrese el nuevo anno de publicacion"));
					break;
				default:
					break;
			}
			gestor.getBibliotecaModel().actualizarLibro(libro);
			out.println("\nRegistro actualizado correctamente.");
		}
	}
	
	private void mostrarBajaView(){
		/*
		 * Vista de la baja de un libro
		 */
		LibroModel libro = new LibroModel();
		libro.setISBN(leer_cadena ("Ingrese el ISBN del libro"));
		
		/*
		 * Veifico que el libro exista en mi biblioteca
		 */
		if (!gestor.getBibliotecaModel().existeLibro(libro))
			out.println("\nRegistro no encontrado.");
		else{
			gestor.getBibliotecaModel().eliminarLibro(libro);
			out.println("Registro borrado correctamente.");
		}
	}
	
	private void mostrarOrdenarView(){
		/*
		 * Vista para ordenar registros, en este
		 * caso es innecesario mostrar una pantalla 
		 * completa, asi que se decidio mostrar solo
		 * un mensaje de confirmacion
		 */
		gestor.getBibliotecaModel().ordenarLibros();
        out.println("Registros ordenados correctamente.");
	}
	
	private void mostrarListarView(){
		/*
		 * Vista que muestra todos los libros de mi biblioteca
		 */
        for (LibroModel libro : gestor.getBibliotecaModel().getLibros()) 
			mostrarLibro(libro);
        
        out.println("Total de registros: " + gestor.getBibliotecaModel().getCantidadLibros() + ".");
	}
	
	private void mostrarSalirView(){
		/*
		 * Vista que simplemente guarda los datos del vector en un
		 * archivo.
		 */
		gestor.getBibliotecaModel().actualizarBaseDeDatos();
	}
	
	private void procesarOpcion(int opcion){

		switch (opcion) {
        case 1:
            mostrarAltaView();
            break;
        case 2:
            mostrarConsultaView();
            break;
        case 3:
        	mostrarActualizacionView();        	
        	break;
        case 4:
        	mostrarBajaView();
            break;
        case 5:
        	mostrarOrdenarView();
        	break;
        case 6:
            mostrarListarView();
            break;
        case 7:
        	mostrarSalirView();
            break;
        }
        pausar("");
	}
	
	private int mostrarMenuActualizacion(){
		/*
		 * Vista de la opcion de actualizacion de libro
		 */
		int opcion;
		
		out.println("Men\u00FA de modificaci\u00F3n de campos");
        out.println("1.- titulo");
        out.println("2.- autor");
        out.println("3.- editorial");
        out.println("4.- edicion");
        out.println("5.- anno de publicacion");
        
        do {
        	opcion = leer_entero ("Seleccione un n\u00FAmero de campo a modificar");
            if (opcion<1 || opcion>5)
                out.println("Opci\u00F3n no v\u00E1lida.");
        } while (opcion<1 || opcion>5);
        
        return opcion;
	}
    
	private String leer_cadena(String mensaje) {
    	/*
    	 * Muestra un mensaje y espera a que el usuario ingrese una
    	 * cadena por consola.
    	 */
		out.println(mensaje + ": ");
        return teclado.nextLine();
    }
	
	private int leer_entero(String mensaje) {
    	/*
    	 * Intenta leer un numero entero y si no tiene exito
    	 * muestra mensaje de error y vuelve a intentar. 
    	 */
        try {
            return Integer.parseInt(leer_cadena(mensaje));
        } catch (NumberFormatException e) {
            this.out.println("N\u00FAmero incorrecto.");
            return leer_entero(mensaje);
        }
    }
	
	private void pausar(String mensage) {
    	/*
    	 * Muesto mensaje y espero a que el usuario presione ENTER para
    	 * seguir con la ejecucion del programa por consola
    	 */
        out.print(mensage + "\nPresione <ENTER> para continuar . . . ");
        teclado.nextLine();
        out.println();
    }
    
    private void mostrarLibro(LibroModel libro) {
        out.println(libro);
    }
	
}
