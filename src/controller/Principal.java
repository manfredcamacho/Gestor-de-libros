package controller;

import model.BibliotecaModel;
import view.View;
import view.GUI.GUIMainView;

public class Principal {
	private Gestor gestor;
	private View view;
	private BibliotecaModel bibliotecaModel;
	
	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.iniciar();		
	}
	
	private void iniciar(){
		view = new GUIMainView();
		bibliotecaModel = new BibliotecaModel();
		gestor = new Gestor();
		
		view.setGestor(gestor);
		
		gestor.setView(view);
		gestor.setBibliotecaModel(bibliotecaModel);
		
		view.mostrarView();
	}
}
