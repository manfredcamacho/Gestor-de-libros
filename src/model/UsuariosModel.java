package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class UsuariosModel {

	private String ruta = "database/usuarios.tsv";
	private Vector<UsuarioModel> usuarios; 
	
	public UsuariosModel() {
		cargarUsuarios();
	}
	
	public void agregarUsuario(UsuarioModel usuario){
		usuarios.add(usuario);
	}
	
	public boolean existeUsuario(UsuarioModel usuario){		
		return usuarios.indexOf(usuario) != -1;
	}
	
	public void actualizarUsuario(UsuarioModel usuario){
		int index = usuarios.indexOf(usuario);
		if(index != -1)
			usuarios.set(index, usuario);
	}

	public void actualizarBaseDeDatos(){
		try {
	        PrintStream salida = new PrintStream(ruta);
	        
	        for (int i=0; i < usuarios.size(); i++)
	            imprimirEnArchivo(usuarios.get(i), salida);
	        
	        salida.close();
	    } catch (FileNotFoundException e) {}
	}
	
	public Vector<UsuarioModel> getUsuarios() {
		return usuarios;
	}
	
	public UsuarioModel getUsuario(UsuarioModel usuario){
		int index = usuarios.indexOf(usuario);
		if(index != -1)
			return usuarios.get(index); 
		return null;
	}
	
	
	private void cargarUsuarios(){
		usuarios = new Vector<UsuarioModel>();
        UsuarioModel usuario;
        String[] campos;
		try {
            Scanner entrada = new Scanner(new FileReader(ruta));
            while (entrada.hasNextLine()) {
                campos = entrada.nextLine().split("\t");
                usuario = new UsuarioModel();
                usuario.setNombre(campos[0]);
                usuario.setClave(campos[1]);
                usuario.setPregunta(campos[2]);
                usuario.setRespuesta(campos[3]);
                this.usuarios.add(usuario);
            }
            entrada.close();
        } catch (FileNotFoundException e) {}
	}
	
	private void imprimirEnArchivo(UsuarioModel usuario, PrintStream archivo) {
            archivo.print(usuario.getNombre() + "\t");
            archivo.print(usuario.getClave() + "\t");
            archivo.print(usuario.getPregunta() + "\t");
            archivo.print(usuario.getRespuesta() + "\t\n");
    }

}
