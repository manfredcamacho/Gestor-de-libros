package controller;

import model.BibliotecaModel;
import model.UsuariosModel;
import view.View;

public class Gestor {
	
	private BibliotecaModel bibliotecaModel;
	private View view;
	private UsuariosModel usuariosModel;
    
	public BibliotecaModel getBibliotecaModel() {
		return bibliotecaModel;
	}

	public void setBibliotecaModel(BibliotecaModel bibliotecaModel) {
		this.bibliotecaModel = bibliotecaModel;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public UsuariosModel getUsuariosModel() {
		return usuariosModel;
	}

	public void setUsuariosModel(UsuariosModel usuariosModel) {
		this.usuariosModel = usuariosModel;
	}
    
}

