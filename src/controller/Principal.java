package controller;

import model.BibliotecaModel;
import model.UsuariosModel;
import view.View;
import view.GUI.GUILoginView;

public class Principal {
	private Gestor gestor;
	private View view;
	private BibliotecaModel bibliotecaModel;
	private UsuariosModel usuariosModel;
	
	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.iniciar();		
	}
	
	private void iniciar(){
		view = new GUILoginView();
		bibliotecaModel = new BibliotecaModel();
		usuariosModel = new UsuariosModel();
		gestor = new Gestor();
		
		view.setGestor(gestor);
		
		gestor.setView(view);
		gestor.setBibliotecaModel(bibliotecaModel);
		gestor.setUsuariosModel(usuariosModel);
		
		view.mostrarView();
	}
}
