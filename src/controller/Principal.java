package controller;

import model.BibliotecaModel;
import view.consola.ConsolaView;

public class Principal {
	private controller.Gestor gestor;
	private view.consola.ConsolaView view;
	private model.BibliotecaModel bibliotecaModel;
	
	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.iniciar();		
	}
	
	private void iniciar(){
		view = new ConsolaView();
		bibliotecaModel = new BibliotecaModel();
		gestor = new Gestor();
		
		view.setGestor(gestor);
		
		gestor.setView(view);
		gestor.setBibliotecaModel(bibliotecaModel);
		
		view.mostrarView();
	}
}
