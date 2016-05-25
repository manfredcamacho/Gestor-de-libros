package controller;

import model.BibliotecaModel;
import view.View;

public class Gestor {
	
	private BibliotecaModel bibliotecaModel;
	private View view;
    
	public BibliotecaModel getBibliotecaModel() {
		return bibliotecaModel;
	}

	public void setBibliotecaModel(BibliotecaModel bibliotecaModel) {
		this.bibliotecaModel = bibliotecaModel;
	}

	public View getConsolaView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
    
}

